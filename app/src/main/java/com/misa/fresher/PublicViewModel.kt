package com.misa.fresher

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.data.models.InforShip
import com.misa.fresher.data.models.ItemBill
import com.misa.fresher.data.models.PackageProduct
import com.misa.fresher.data.models.Product
import com.misa.fresher.data.models.enum.BillStatus
import com.misa.fresher.data.models.enum.Category
import com.misa.fresher.data.models.enum.Color
import com.misa.fresher.data.source.AppDatabaseHelper
import com.misa.fresher.data.source.local.dao.ItemBillDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*


class PublicViewModel : ViewModel() {
    var isInit = false
    var queueBill: LinkedList<ItemBill> = LinkedList()

    private val _listBill = MutableLiveData<MutableList<ItemBill>>()
    val listBill: LiveData<MutableList<ItemBill>>
        get() = _listBill

    private val _billHandling = MutableLiveData<ItemBill>()
    val billHandling: LiveData<ItemBill>
        get() = _billHandling

    private val _inforShip = MutableLiveData<InforShip>()
    val inforShip: LiveData<InforShip>
        get() = _inforShip

    private val _itemSelected = MutableLiveData<PackageProduct>()
    val itemSelected: LiveData<PackageProduct>
        get() = _itemSelected

    private val _listItemSelected = MutableLiveData<MutableList<PackageProduct>>()
    val listItemSelected: LiveData<MutableList<PackageProduct>>
        get() = _listItemSelected

    /**
     * get data
     */

    fun fakeData(context: Context) {

        if (!isInit) {
            isInit = true
            _billHandling.postValue(
                ItemBill(
                    mutableListOf(),
                    null,
                    BillStatus.HANDLING.name,
                    Calendar.getInstance().time.toString()
                )
            )

            _itemSelected.postValue(
                PackageProduct(
                    Product(
                        0,
                        "",
                        "",
                        Color.BLUE.toString(),
                        Category.ELECTRONIC.toString(),
                        0,
                        10,
                        "23/02/2021"
                    ), "0", 1
                )
            )
            _listItemSelected.postValue(mutableListOf())

            _listBill.postValue(mutableListOf())

            _inforShip.postValue(InforShip(null, null, null, null))


            CoroutineScope(IO).launch {

                val itemBillDao = ItemBillDao(AppDatabaseHelper.getInstance(context))
                val x = itemBillDao.getAllListBill()
                _listBill.postValue(x)
                Log.e(
                    "listBill",
                    itemBillDao.getAllListBill().size.toString() + "" + _listBill.value?.size.toString()
                )


            }


        }


    }

    /**
     * item selected
     */

    fun updateItemSelected(itemProduct: Product) {

        Log.e("billl", _billHandling.value?.id.toString())
        val itemSelected = _listItemSelected.value?.find {
            it.product == itemProduct
        } ?: _billHandling.value?.id?.let { PackageProduct(itemProduct, it, 1) }

        _itemSelected.postValue(itemSelected!!)
    }

    fun updateItemSelectedQuantity(num: Int) {
        _itemSelected.value?.updateCount(num)
        _itemSelected.postValue(_itemSelected.value)
    }

    /**
     * List item selected
     */

    fun updateListItemSelected() {
        val listSelected = _listItemSelected.value
        var check = false

        listSelected?.let {
            for (item in listSelected) {
                if (item.product == _itemSelected.value?.product) {
                    item.countPackage = _itemSelected.value!!.countPackage
                    check = true
                }
            }
        }
        if (!check) {
            _itemSelected.value?.let { listSelected?.add(it) }
        }
        _listItemSelected.postValue(listSelected!!)
    }

    fun clearListItemSelected() {
        _listItemSelected.postValue(mutableListOf())

        CoroutineScope(IO).launch {
            _billHandling.postValue(
                ItemBill(
                    mutableListOf(),
                    null,
                    BillStatus.HANDLING.name,
                    Calendar.getInstance().time.toString()
                )
            )
            _inforShip.postValue(InforShip(null, null, null, null))
        }
    }

    fun getTotalPrice() =
        _listItemSelected.value?.sumOf { it.product!!.priceProduct * it.countPackage }

    fun getCount() = _listItemSelected.value?.sumOf { it.countPackage }

    fun updateQuantityOfItemBillDetail(itemBillDetail: PackageProduct) {
        _listItemSelected.value?.let {
            for (item in it) {
                if (item.product == itemBillDetail.product) {
                    item.countPackage = itemBillDetail.countPackage
                    _listItemSelected.postValue(it)
                    break
                }
            }
        }
    }

    /**
     * shipping information
     */

    fun updateInforShip(infoShip: InforShip) {
        _inforShip.postValue(infoShip)
    }

    /**
     * bill
     */

    fun addBillToListBill(context: Context) {
        _billHandling.value?.listItemBillDetail = _listItemSelected.value!!
        _billHandling.value?.inforShip = _inforShip.value!!
        _billHandling.postValue(_billHandling.value)
        _billHandling.value?.let {
            _listBill.value?.add(it)
            it.setBillPrice()
        }
        Log.e("listItem", _billHandling.value?.listItemBillDetail!!.size.toString())

        _billHandling.value?.let { queueBill.add(it) }

        CoroutineScope(IO).launch {
            val itemBillDao = ItemBillDao(AppDatabaseHelper.getInstance(context))
            while (queueBill.size != 0) {
                itemBillDao.addListBill(queueBill.peek())
                queueBill.pop()
            }

        }

        _listBill.postValue(_listBill.value)
        var j = 0
        clearListItemSelected()

    }

    fun getTotalPriceListBill(): Float {
        var res = 0f
        _listBill.value?.let {
            for (i in it) {
                var k = 0
                res += i.billPrice
            }
        }
        return res
    }


}