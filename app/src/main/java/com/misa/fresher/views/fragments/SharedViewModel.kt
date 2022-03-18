package com.misa.fresher.views.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.models.enum.BillStatus
import com.misa.fresher.models.enum.Category
import com.misa.fresher.models.enum.Color
import com.misa.fresher.models.InforShip
import com.misa.fresher.models.ItemBill
import com.misa.fresher.models.ItemBillDetail
import com.misa.fresher.models.ItemProduct
import java.util.*

class SharedViewModel : ViewModel() {

    private val _listBill = MutableLiveData<MutableList<ItemBill>>()
    val listBill: LiveData<MutableList<ItemBill>>
        get() = _listBill

    private val _billHandling = MutableLiveData<ItemBill>()
    val billHandling: LiveData<ItemBill>
        get() = _billHandling

    private val _inforShip = MutableLiveData<InforShip>()
    val inforShip: LiveData<InforShip>
        get() = _inforShip

    private val _itemSelected = MutableLiveData<ItemBillDetail>()
    val itemSelected: LiveData<ItemBillDetail>
        get() = _itemSelected

    private val _listItemSelected = MutableLiveData<MutableList<ItemBillDetail>>()
    val listItemSelected: LiveData<MutableList<ItemBillDetail>>
        get() = _listItemSelected

    init {
        _itemSelected.postValue(
            ItemBillDetail(
                ItemProduct("", 0f, "", Color.RED, Category.SHIRT, 10, "11/4/2011"),
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

        _inforShip.postValue(  InforShip(null,null,null,null,null,null,null,null,null,false))
    }

    /**
     * item selected
     */

    fun updateItemSelected(itemProduct: ItemProduct) {
        val itemSelected = _listItemSelected.value?.find {
            it.itemProduct == itemProduct
        }?:ItemBillDetail(itemProduct, 1)


        _itemSelected.postValue(itemSelected)
    }

    fun updateItemSelectedQuantity(num: Int) {
        val itemSelected = _itemSelected.value?.let {
            ItemBillDetail(
                it.itemProduct, it.quantity + num
            )
        }
        _itemSelected.postValue(itemSelected!!)
    }

    /**
     * List item selected
     */

    fun updateListItemSelected() {
        val listSelected = _listItemSelected.value
        var check = false

        listSelected?.let {
            for (item in listSelected) {
                if (item.itemProduct == _itemSelected.value?.itemProduct) {
                    item.quantity = _itemSelected.value!!.quantity
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

    fun getTotalPrice(): Float {
        var totalPrice = 0f
        _listItemSelected.value?.let {
            for (i in it) {
                totalPrice += i.itemProduct.price * i.quantity
            }
        }
        return totalPrice
    }

    fun updateQuantityOfItemBillDetail(itemBillDetail: ItemBillDetail) {
        if (itemBillDetail.quantity == 0) {
            val selectedList = mutableListOf<ItemBillDetail>()
            for (i in selectedList) {
                if (i.itemProduct == itemBillDetail.itemProduct) {

                } else {
                    selectedList.add(i)
                }
            }
            _listItemSelected.postValue(selectedList)
        } else {
            var selectedList = _listItemSelected.value
            selectedList?.let {
                for (i in it) {
                    if (i.itemProduct == itemBillDetail.itemProduct) {
                        i.quantity = itemBillDetail.quantity
                        _listItemSelected.postValue(it)
                        break
                    }
                }
            }
        }
    }

    /**
     * shipping information
     */

    fun updateInforShip(inforShip: InforShip) {
        _inforShip.postValue(inforShip)
    }


    /**
     * bill
     */

    fun addBillToListBill() {
        _billHandling.value?.listItemBillDetail = _listItemSelected.value!!
        _billHandling.value?.inforShip = _inforShip.value!!
        _billHandling.postValue(_billHandling.value)

        _billHandling.value?.let { _listBill.value?.add(it) }

        _listBill.postValue(_listBill.value)
        clearListItemSelected()
        var i = 0
    }

    fun getTotalPriceListBill(): Float {
        var res = 0f
        _listBill.value?.let {
            for (i in _listBill.value!!) {
                res += i.getPrice()
            }
        }
        return res

    }


}