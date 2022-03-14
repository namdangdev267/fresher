package com.misa.fresher.Views.Fragments.Sale

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.Enum.Category
import com.misa.fresher.Models.Enum.Color
import com.misa.fresher.Models.Enum.SortBy
import com.misa.fresher.Models.ItemBillDetail
import com.misa.fresher.Models.ItemProduct
import java.text.Collator


class SaleViewModel : ViewModel() {
    var init = false
    var search: String = ""
    var filter: Filter = Filter(null, null, false, SortBy.name_item)

    var listItemProduct: MutableList<ItemProduct> = mutableListOf()

    private val _listItemShow = MutableLiveData<MutableList<ItemProduct>>()
    val listItemShow: LiveData<MutableList<ItemProduct>>
        get() = _listItemShow

    private val _itemSelected = MutableLiveData<ItemBillDetail>()
    val itemSelected: LiveData<ItemBillDetail>
        get() = _itemSelected

    private val _listItemSelected = MutableLiveData<MutableList<ItemBillDetail>>()
    val listItemSelected: LiveData<MutableList<ItemBillDetail>>
        get() = _listItemSelected

    data class Filter(
        var category: Category?,
        var color: Color?,
        var available: Boolean,
        var sortBy: SortBy?
    )

    fun initData() {
        fakeData()
        _listItemShow.postValue(listItemProduct)
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

    private fun fakeData() {

        for (i in 1..20) {
            listItemProduct.add(
                ItemProduct(
                    i.toString() + "shirt" + i, (i * 1.01).toFloat(), i.toString() + "AA",
                    Color.yellow, Category.Shirt, 10
                )
            )
        }
        for (i in 1..20) {
            listItemProduct.add(
                ItemProduct(
                    i.toString() + "trouser" + i, (i * 1.01).toFloat(), i.toString() + "AA",
                    Color.red, Category.Trouser, 20
                )
            )
        }

    }


    /**
     * List selected item
     */

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


    /**
     * List item show
     */

    fun updateListItemShow(searchString: String) {
        search = searchString
        _listItemShow.postValue(listItemProduct)
        var showList = mutableListOf<ItemProduct>()
        for (i in listItemProduct) {
            if (i.name.contains(searchString)) {
                showList.add(i)
            }
        }
        _listItemShow.postValue(showList)
    }

    fun filterListItemShow() {
        Log.e("filter", filter.toString())
        var showList = mutableListOf<ItemProduct>()
        for (i in listItemProduct) {
            if (i.name.contains(search)) {
                showList.add(i)
            }
        }
        var res = mutableListOf<ItemProduct>()
        for (i in showList) {
            Log.e(
                this.javaClass.simpleName,
                "listItemShow--" + filter.color?.name + "--" + i.color.name
            )
            if (filter.category != null && filter.category != i.category) {

            } else if (filter.color != null && filter.color != i.color) {

            } else if (filter.available == true && i.quantity <= 0) {

            } else {
                res.add(i)
            }
        }

        if(res.size!=0)
        {
            when (filter.sortBy) {
                SortBy.name_item -> res.sortedWith { p1, p2 ->
                    Collator.getInstance().compare(p1.name, p2.name)
                }
                else -> res.sortedByDescending {
                    it.quantity
                }
            }
        }


        Log.e(this.javaClass.simpleName, showList.size.toString() + "--" + listItemProduct.size)
        _listItemShow.postValue(res)

    }




    fun getColorOf(itemProduct: ItemProduct): List<Color> {
        var mutableList: MutableList<Color> = mutableListOf()
        for (i in listItemProduct) {
            if (i.name.equals(itemProduct.name)) {
                mutableList.add(itemProduct.color)
            }
        }
        return mutableList
    }


}