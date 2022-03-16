package com.misa.fresher.Views.Fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.Enum.BillStatus
import com.misa.fresher.Models.Enum.Category
import com.misa.fresher.Models.Enum.Color
import com.misa.fresher.Models.InforShip
import com.misa.fresher.Models.ItemBill
import com.misa.fresher.Models.ItemBillDetail
import com.misa.fresher.Models.ItemProduct
import java.lang.Math.random
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
                ItemProduct("", 0f, "", Color.red, Category.Shirt, 10, "11/4/2011"),
                "",
                "",
                1
            )
        )
        _listItemSelected.postValue(mutableListOf())
        _billHandling.postValue(
            ItemBill(
                (1000000..2000000).random().toString(),
                mutableListOf(),
                null,
                BillStatus.Handling,
                Calendar.getInstance().time
            )
        )
        _listBill.postValue(mutableListOf())
    }

    /**
     * item selected
     */

    fun updateItemSelected(itemProduct: ItemProduct) {
        var itemSelected: ItemBillDetail =
            ItemBillDetail(itemProduct, itemProduct.name, itemProduct.id, 1)
        _listItemSelected.value?.let {
            for (i in it) {
                if (i.itemProduct.equals(itemProduct)) {
                    itemSelected = i
                }
            }
        }
        _itemSelected.postValue(itemSelected)
    }

    fun updateItemSelectedQuantity(num: Int) {
        var itemSelected = _itemSelected.value?.let {
            ItemBillDetail(
                it?.itemProduct, "", "", it.quantity + num
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
            for (i in listSelected) {
                if (i.itemProduct == _itemSelected.value?.itemProduct) {
                    i.quantity = _itemSelected.value!!.quantity
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
//        _listItemSelected.value?.clear()
        _listItemSelected.postValue(mutableListOf())
        _billHandling.postValue(
            ItemBill(
                (1000000..2000000).random().toString(),
                mutableListOf(),
                null,
                BillStatus.Handling,
                Calendar.getInstance().time
            )
        )
    }

    fun getTotalPrice(): Float {
        var totalPrice = 0f
        _listItemSelected.value?.let {
            for (i in _listItemSelected.value!!) {
                totalPrice += i.itemProduct.price * i.quantity
            }
        }
        return totalPrice
    }

    fun updateQuantityOfItemBillDetail(itemBillDetail: ItemBillDetail) {
        if (itemBillDetail.quantity == 0) {
            var selectedList = mutableListOf<ItemBillDetail>()
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
                for (i in selectedList) {
                    if (i.itemProduct == itemBillDetail.itemProduct) {
                        i.quantity = itemBillDetail.quantity
                        _listItemSelected.postValue(selectedList!!)
                        break
                    }
                }
            }
        }
    }

    /**
     * shipping information
     */


    /**
     * bill
     */

    fun addBillToListBill() {
        _billHandling.value?.listItemBillDetail = _listItemSelected.value!!
        _billHandling.postValue(_billHandling.value)

        _billHandling.value?.let { _listBill.value?.add(it) }

        _listBill.postValue(_listBill.value)
        clearListItemSelected()
        var i = 0
    }

    fun getTotalPriceListBill():Float
    {
        var res=0f
        _listBill.value?.let {
            for(i in _listBill.value!!)
            {
                res += i.getPrice()
            }
        }
        return res

    }
}