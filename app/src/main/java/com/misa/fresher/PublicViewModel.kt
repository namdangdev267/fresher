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


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PublicViewModel : ViewModel() {
    private var isInit = false
    private var queueBillList: LinkedList<ItemBill> = LinkedList()

    private val mListBill = MutableLiveData<MutableList<ItemBill>>()
    val listBill: LiveData<MutableList<ItemBill>>
        get() = mListBill

    private val mBillHandling = MutableLiveData<ItemBill>()
    val billHandling: LiveData<ItemBill>
        get() = mBillHandling

    private val mInforShip = MutableLiveData<InforShip>()
    val inforShip: LiveData<InforShip>
        get() = mInforShip

    private val mItemPackageProduct = MutableLiveData<PackageProduct>()
    val itemPackageProduct: LiveData<PackageProduct>
        get() = mItemPackageProduct

    private val mListItemSelected = MutableLiveData<MutableList<PackageProduct>>()
    val listItemSelected: LiveData<MutableList<PackageProduct>>
        get() = mListItemSelected

    fun fakeData(context: Context) {
        if (!isInit) {
            isInit = true
            mBillHandling.postValue(
                ItemBill(
                    mutableListOf(),
                    null,
                    BillStatus.HANDLING.name,
                    Calendar.getInstance().time.toString()
                )
            )

            mItemPackageProduct.postValue(
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
            mListItemSelected.postValue(mutableListOf())

            mListBill.postValue(mutableListOf())

            mInforShip.postValue(InforShip(null, null, null, null))


            CoroutineScope(IO).launch {
                val itemBillDao = ItemBillDao(AppDatabaseHelper.getInstance(context))
                val x = itemBillDao.getAllListBill()
                mListBill.postValue(x)
                Log.e(
                    "listBill",
                    itemBillDao.getAllListBill().size.toString() + "" + mListBill.value?.size.toString()
                )
            }
        }
    }

    fun updateItemSelected(itemProduct: Product) {

        Log.e("billl", mBillHandling.value?.id.toString())
        val itemSelected = mListItemSelected.value?.find {
            it.product == itemProduct
        } ?: mBillHandling.value?.id?.let { PackageProduct(itemProduct, it, 1) }

        mItemPackageProduct.postValue(itemSelected!!)
    }

    fun updateItemSelectedQuantity(num: Int) {
        mItemPackageProduct.value?.updateCount(num)
        mItemPackageProduct.postValue(mItemPackageProduct.value)
    }

    fun updateListItemSelected() {
        val listSelected = mListItemSelected.value
        var check = false

        listSelected?.let {
            for (item in listSelected) {
                if (item.product == mItemPackageProduct.value?.product) {
                    item.countPackage = mItemPackageProduct.value!!.countPackage
                    check = true
                }
            }
        }
        if (!check) {
            mItemPackageProduct.value?.let { listSelected?.add(it) }
        }
        mListItemSelected.postValue(listSelected!!)
    }

    fun clearListItemSelected() {
        mListItemSelected.postValue(mutableListOf())

        CoroutineScope(IO).launch {
            mBillHandling.postValue(
                ItemBill(
                    mutableListOf(),
                    null,
                    BillStatus.HANDLING.name,
                    Calendar.getInstance().time.toString()
                )
            )
            mInforShip.postValue(InforShip(null, null, null, null))
        }
    }

    fun getTotalPrice() =
        mListItemSelected.value?.sumOf { it.product!!.priceProduct * it.countPackage }

    fun getCount() = mListItemSelected.value?.sumOf { it.countPackage }

    fun updateQuantityOfPackageProduct(packageProduct: PackageProduct) {
        mListItemSelected.value?.let {
            for (item in it) {
                if (item.product == packageProduct.product) {
                    item.countPackage = packageProduct.countPackage
                    mListItemSelected.postValue(it)
                    break
                }
            }
        }
    }

    fun updateInforShip(infoShip: InforShip) {
        mInforShip.postValue(infoShip)
    }

    fun addBillToListBill(context: Context) {
        mBillHandling.value?.listItemBillDetail = mListItemSelected.value!!
        mBillHandling.value?.inforShip = mInforShip.value!!
        mBillHandling.postValue(mBillHandling.value)
        mBillHandling.value?.let {
            mListBill.value?.add(it)
            it.setBillPrice()
        }
        Log.e("listItem", mBillHandling.value?.listItemBillDetail!!.size.toString())

        mBillHandling.value?.let { queueBillList.add(it) }

        CoroutineScope(IO).launch {
            val itemBillDao = ItemBillDao(AppDatabaseHelper.getInstance(context))
            while (queueBillList.size != 0) {
                itemBillDao.addListBill(queueBillList.peek())
                queueBillList.pop()
            }

        }

        mListBill.postValue(mListBill.value)
        var j = 0
        clearListItemSelected()

    }

    fun getTotalPriceListBill(): Float {
        var res = 0f
        mListBill.value?.let {
            for (i in it) {
                var k = 0
                res += i.billPrice
            }
        }
        return res
    }


}