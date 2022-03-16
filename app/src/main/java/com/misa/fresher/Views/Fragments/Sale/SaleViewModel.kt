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

    data class Filter(
        var category: Category?,
        var color: Color?,
        var available: Boolean,
        var sortBy: SortBy?
    )

    fun initData() {
        fakeData()
        _listItemShow.postValue(listItemProduct)
    }

    private fun fakeData() {

        for (i in 1..20) {
            listItemProduct.add(
                ItemProduct(
                    i.toString() + "trouser" + i, (i * 1.01).toFloat(), i.toString() + "AA",
                    Color.red, Category.Trouser, i, "5/10/2011"
                )
            )
        }

        for (i in 1..20) {
            listItemProduct.add(
                ItemProduct(
                    i.toString() + "shirt" + i, (i * 1.01).toFloat(), i.toString() + "AA",
                    Color.yellow, Category.Shirt, i, "11/11/2011"
                )
            )
        }

        listItemProduct = listItemProduct.sortedWith { p1, p2 ->
            Collator.getInstance().compare(p1.name, p2.name)
        } as MutableList<ItemProduct>


    }

    /**
     * Filter
     */

    fun clearFilter() {
        filter.sortBy = SortBy.name_item
        filter.category = null
        filter.color = null
        filter.available = false
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
            val filterCategory = (filter.category != null && filter.category != i.category)
            val filterColor = (filter.color != null && filter.color != i.color)
            val filterAvailable = (filter.available == true && i.availableQuantity <= 0)

            if(!filterAvailable && !filterColor && !filterCategory)
            {
                res.add(i)
            }
        }

        Log.e(this.javaClass.simpleName, res.toString())

        if (res.size != 0) {
            res = when (filter.sortBy) {
                SortBy.name_item -> res.sortedWith { p1, p2 ->
                    Collator.getInstance().compare(p1.name, p2.name)
                } as MutableList<ItemProduct>
                SortBy.new_arrival -> res.sortedWith { p1, p2 ->
                    Collator.getInstance().compare(p1.dateArrival, p2.dateArrival)
                } as MutableList<ItemProduct>
                else -> res.sortedByDescending {
                    it.availableQuantity
                } as MutableList<ItemProduct>
            }
        }
        Log.e(this.javaClass.simpleName + "after sort: ", res.toString())


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