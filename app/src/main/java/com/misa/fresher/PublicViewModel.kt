package com.misa.fresher

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.models.InforShip
import com.misa.fresher.models.ItemBill
import com.misa.fresher.models.PackageProduct
import com.misa.fresher.models.Product
import com.misa.fresher.models.enum.BillStatus
import com.misa.fresher.models.enum.Category
import com.misa.fresher.models.enum.Color
import java.util.*


class PublicViewModel : ViewModel() {

    private val _itemSelected = MutableLiveData<PackageProduct>()
    val itemSelected: LiveData<PackageProduct>
        get() = _itemSelected

    private val _listItemSelected = MutableLiveData<MutableList<PackageProduct>>()
    val listItemSelected: LiveData<MutableList<PackageProduct>>
        get() = _listItemSelected

    private val _listBill = MutableLiveData<MutableList<ItemBill>>()
    val listBill: LiveData<MutableList<ItemBill>>
        get() = _listBill

    private val _billHandling = MutableLiveData<ItemBill>()
    val billHandling: LiveData<ItemBill>
        get() = _billHandling

    private val _inforShip = MutableLiveData<InforShip>()
    val inforShip: LiveData<InforShip>
        get() = _inforShip

    init {
        _itemSelected.postValue(
            PackageProduct(
                Product(
                    R.drawable.ic_launcher_foreground,
                    "",
                    "",
                    Color.BLUE,
                    Category.TROUSER,
                    0,
                    10,
                    "24/03/2022"
                ),
                1
            )
        )

        _listItemSelected.postValue(mutableListOf())

        _billHandling.postValue(
            ItemBill(
                (1000000..2000000).random().toString(),
                mutableListOf(),
                null,
                BillStatus.HANDLING,
                Calendar.getInstance().time
            )
        )
        _listBill.postValue(mutableListOf())
    }

    fun updateItemSelected(itemProduct: Product) {
        var itemSelected: PackageProduct =
            PackageProduct(itemProduct, 1)
        _listItemSelected.value?.let {
            for (i in it) {
                if (i.product == itemProduct) {
                    itemSelected = i
                }
            }
        }
        _itemSelected.postValue(itemSelected)
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun updateItemSelectedQuantity(num: Int) {
        val itemSelected = _itemSelected.value?.let {
            PackageProduct(
                it.product, it.countPackage + num
            )
        }
        _itemSelected.postValue(itemSelected)
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun updateListItemSelected() {
        val listSelected = _listItemSelected.value
        var check = false

        listSelected?.let {
            for (i in listSelected) {
                if (i.product == _itemSelected.value?.product) {
                    i.countPackage = _itemSelected.value!!.countPackage
                    check = true
                }
            }
        }
        if (!check) {
            _itemSelected.value?.let { listSelected?.add(it) }
        }
        _listItemSelected.postValue(listSelected)
    }

    fun clearListItemSelected() {
        _listItemSelected.value?.clear()
        _listItemSelected.postValue(mutableListOf())
        _billHandling.postValue(
            ItemBill(
                (1000000..2000000).random().toString(),
                mutableListOf(),
                null,
                BillStatus.HANDLING,
                Calendar.getInstance().time
            )
        )
    }

    fun getTotalPrice() =
        _listItemSelected.value?.sumOf { it.product.priceProduct * it.countPackage }

    fun getCount() = _listItemSelected.value?.sumOf { it.countPackage }

    fun updateQuantityOfItemBillDetail(itemBillDetail: PackageProduct) {
        if (itemBillDetail.countPackage == 0) {
            val selectedList = mutableListOf<PackageProduct>()
            for (i in selectedList) {
                if (i.product == itemBillDetail.product) {

                } else {
                    selectedList.add(i)
                }
            }
            _listItemSelected.postValue(selectedList)
        } else {
            val selectedList = _listItemSelected.value
            selectedList?.let {
                for (i in it) {
                    if (i.product == itemBillDetail.product) {
                        i.countPackage = itemBillDetail.countPackage
                        _listItemSelected.postValue(it)
                        break
                    }
                }
            }
        }
    }

    fun updateInforShip(inforShip: InforShip) = _inforShip.postValue(inforShip)

    fun addBillToListBill() {
        _billHandling.value?.listItemBillDetail = _listItemSelected.value!!
        _billHandling.value?.inforShip = _inforShip.value
        _billHandling.postValue(_billHandling.value)
        _billHandling.value?.let { _listBill.value?.add(it) }
        _listBill.postValue(_listBill.value)
//        clearListItemSelected()
    }

    fun getTotalPriceListBill() = _listBill.value?.sumOf { it.getPrice() }

}