package com.misa.fresher.Views.Fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.Enum.Category
import com.misa.fresher.Models.Enum.Color
import com.misa.fresher.Models.ItemBillDetail
import com.misa.fresher.Models.ItemProduct

class SharedViewModel:ViewModel() {


    private val _itemSelected = MutableLiveData<ItemBillDetail>()
    val itemSelected: LiveData<ItemBillDetail>
        get() = _itemSelected

    private val _listItemSelected = MutableLiveData<MutableList<ItemBillDetail>>()
    val listItemSelected: LiveData<MutableList<ItemBillDetail>>
        get() = _listItemSelected

    init {
        _itemSelected.postValue(
            ItemBillDetail(
                ItemProduct("", 0f, "", Color.red, Category.Shirt, 10),
                "",
                "",
                1
            )
        )
        _listItemSelected.postValue(mutableListOf())
    }

    fun updateItemSelected(itemProduct: ItemProduct) {
        var itemSelected: ItemBillDetail = ItemBillDetail(itemProduct, itemProduct.name, itemProduct.id, 1)
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

    fun updateListItemSelected() {
        _itemSelected.value?.let { _listItemSelected.value?.apply { add(it) } }
        _listItemSelected.postValue(_listItemSelected.value)
    }

    fun clearListItemSelected() {
        _listItemSelected.value?.clear()
        _listItemSelected.postValue(_listItemSelected.value)
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
        if(itemBillDetail.quantity == 0)
        {
            var selectedList = mutableListOf<ItemBillDetail>()
            for(i in selectedList)
            {
                if(i.itemProduct==itemBillDetail.itemProduct )
                {

                }
                else
                {
                    selectedList.add(i)
                }
            }
            _listItemSelected.postValue(selectedList)
        }
        else
        {
            var selectedList = _listItemSelected.value
            selectedList?.let {
                for(i in selectedList)
                {
                    if(i.itemProduct==itemBillDetail.itemProduct )
                    {
                        i.quantity = itemBillDetail.quantity
                        _listItemSelected.postValue(selectedList!!)
                        break
                    }
                }
            }
        }





    }
}