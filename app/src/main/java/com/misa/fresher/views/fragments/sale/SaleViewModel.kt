package com.misa.fresher.views.fragments.sale

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.models.enum.Category
import com.misa.fresher.models.enum.Color
import com.misa.fresher.models.enum.SortBy
import com.misa.fresher.models.ItemProduct
import java.text.Collator


class SaleViewModel : ViewModel() {
    var init = false
    var search: String = ""
    var filter: Filter = Filter(null, null, false, SortBy.NAME)

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
                    i.toString() + "trouser" + i, (i * 10).toFloat(), i.toString() + "AA",
                    Color.RED, Category.TROUSER, i, "5/10/2011"
                )
            )
        }

        for (i in 1..20) {
            listItemProduct.add(
                ItemProduct(
                    i.toString() + "shirt" + i, (i * 10).toFloat(), i.toString() + "AA",
                    Color.YELLOW, Category.SHIRT, i, "11/11/2011"
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
        filter.sortBy = SortBy.NAME
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
        showList = listItemProduct.filter { it.name.contains(searchString, true) } as MutableList<ItemProduct>

        _listItemShow.postValue(showList)
    }

    fun filterListItemShow() {
        Log.e("filter", filter.toString())
        var showList = mutableListOf<ItemProduct>()
        showList = listItemProduct.filter { it.name.contains(search, true) } as MutableList<ItemProduct>
//        for (i in listItemProduct) {
//            if (i.name.contains(search)) {
//                showList.add(i)
//            }
//        }
        var res = mutableListOf<ItemProduct>()
        for (i in showList) {
            val filterCategory = (filter.category != null && filter.category != i.category)
            val filterColor = (filter.color != null && filter.color != i.color)
            val filterAvailable = (filter.available == true && i.availableQuantity <= 0)

            if(!filterAvailable && !filterColor && !filterCategory)
            {
                res.add(i)
            }

//            val filterCategory = (filter.category == null || filter.category == i.category)
//            val filterColor = (filter.color == null ||  filter.color == i.color)
//            val filterAvailable = (!filter.available || i.availableQuantity > 0)
//
//            if(filterAvailable && filterColor && filterCategory)
//            {
//                res.add(i)
//            }
        }

        Log.e(this.javaClass.simpleName, res.toString())

        if (res.size != 0) {
            res = when (filter.sortBy) {
                SortBy.NAME -> res.sortedWith { p1, p2 ->
                    Collator.getInstance().compare(p1.name, p2.name)
                } as MutableList<ItemProduct>
                SortBy.NEW_ARRIVAL -> res.sortedWith { p1, p2 ->
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