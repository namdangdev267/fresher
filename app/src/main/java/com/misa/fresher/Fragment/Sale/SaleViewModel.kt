package com.misa.fresher.Fragment.Sale

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.Enum.Category
import com.misa.fresher.Models.Enum.Color
import com.misa.fresher.Models.Enum.SortBy
import com.misa.fresher.Models.Product
import com.misa.fresher.R
import java.text.Collator

class SaleViewModel : ViewModel() {
    var search: String = ""
    var filter: Filter = Filter(null, null, false, SortBy.name_item)

    var listItemProduct: MutableList<Product> = mutableListOf()

    private val _listItemShow = MutableLiveData<MutableList<Product>>()
    val listItemShow: LiveData<MutableList<Product>>
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
                Product(
                    R.drawable.ic_launcher_foreground,
                    i.toString() + "Pants" + i,
                    i.toString() + "AP",
                    (i * 100).toFloat(),
                    i
                )
            )
        }

        for (i in 1..20) {
            listItemProduct.add(
                Product(
                    R.drawable.ic_launcher_foreground,
                    i.toString() + "Shirts" + i,
                    i.toString() + "AS",
                    (i * 50).toFloat(),
                    i
                )
            )
        }

        listItemProduct = listItemProduct.sortedWith { p1, p2 ->
            Collator.getInstance().compare(p1.nameProduct, p2.nameProduct)
        } as MutableList<Product>


    }

    /**
     * Filter
     */

    fun clearFilter() {
        filter.sortBy = null
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
        var showList = mutableListOf<Product>()
        for (i in listItemProduct) {
            if (i.nameProduct.contains(searchString)) {
                showList.add(i)
            }
        }
        _listItemShow.postValue(showList)
    }

    fun filterListItemShow() {
        Log.e("filter", filter.toString())
        var showList = mutableListOf<Product>()
        for (i in listItemProduct) {
            if (i.nameProduct.contains(search)) {
                showList.add(i)
            }
        }
        var res = mutableListOf<Product>()

//        for (i in showList) {
//            Log.e(
//                this.javaClass.simpleName,
//                "listItemShow--" + filter.color?.name + "--" + i.color.name
//            )
//            if (filter.category != null && filter.category != i.category) {
//
//            } else if (filter.color != null && filter.color != i.color) {
//
//            } else if (filter.available == true && i.availableQuantity <= 0) {
//
//            } else {
//                res.add(i)
//            }
//        }

        Log.e(this.javaClass.simpleName, res.toString())

//        if (res.size != 0) {
//            res = when (filter.sortBy) {
//                SortBy.name_item -> res.sortedWith { p1, p2 ->
//                    Collator.getInstance().compare(p1.name, p2.name)
//                } as MutableList<ItemProduct>
//                SortBy.new_arrival -> res.sortedWith { p1, p2 ->
//                    Collator.getInstance().compare(p1.dateArrival, p2.dateArrival)
//                } as MutableList<ItemProduct>
//                else -> res.sortedByDescending {
//                    it.availableQuantity
//                } as MutableList<ItemProduct>
//            }
//        }
        Log.e(this.javaClass.simpleName + "after sort: ", res.toString())


        Log.e(this.javaClass.simpleName, showList.size.toString() + "--" + listItemProduct.size)
        _listItemShow.postValue(res)

    }


    fun getColorOf(itemProduct: Product): List<Color> {
        var mutableList: MutableList<Color> = mutableListOf()
        for (i in listItemProduct) {
            if (i.nameProduct.equals(itemProduct.nameProduct)) {
//                mutableList.add(itemProduct.c)
            }
        }
        return mutableList
    }


}