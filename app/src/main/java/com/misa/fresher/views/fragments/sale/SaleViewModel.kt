package com.misa.fresher.views.fragments.sale

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.data.dao.itembill.ItemBillDao
import com.misa.fresher.data.dao.itemproduct.ItemProductDao
import com.misa.fresher.data.database.AppDatabase
import com.misa.fresher.models.ItemBill
import com.misa.fresher.models.enums.Category
import com.misa.fresher.models.enums.Color
import com.misa.fresher.models.enums.SortBy
import com.misa.fresher.models.ItemProduct
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.text.Collator
import java.util.*


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

    fun fakeData(context: Context) {
        _listItemShow.postValue(listItemProduct)
        CoroutineScope(IO).launch {
            val itemProductDao: ItemProductDao
            val itemBillDao:ItemBillDao
            withContext(Dispatchers.Default)
            {
                itemProductDao = ItemProductDao(AppDatabase.getInstance(context))
            }
            withContext(Dispatchers.Default)
            {
                if (itemProductDao.getAllProducts().size == 0) {

                    for (i in 1..20) {
                        listItemProduct.add(
                            ItemProduct(
                                i.toString() + "trouser" + i,
                                (i * 10).toFloat(),
                                i.toString() + "AA",
                                Color.RED.name,
                                Category.TROUSER.name,
                                i,
                                "5/10/2011"
                            )
                        )
                    }

                    for (i in 1..20) {
                        listItemProduct.add(
                            ItemProduct(
                                i.toString() + "shirt" + i, (i * 10).toFloat(), i.toString() + "AA",
                                Color.YELLOW.name, Category.SHIRT.name, i, "11/11/2011"
                            )
                        )
                    }

                    for (item in listItemProduct) {
                        itemProductDao.addProduct(item)
                    }

                    withContext(Dispatchers.Default)
                    {
                        listItemProduct = itemProductDao.getAllProducts()
                    }
                    withContext(Dispatchers.Default)
                    {
                        listItemProduct = listItemProduct.sortedWith { p1, p2 ->
                            Collator.getInstance().compare(p1.name, p2.name)
                        } as MutableList<ItemProduct>
                        _listItemShow.postValue(listItemProduct)
                    }
                } else {
                    withContext(Dispatchers.Default)
                    {
                        listItemProduct = itemProductDao.getAllProducts()
                    }
                    withContext(Dispatchers.Default)
                    {
                        listItemProduct = listItemProduct.sortedWith { p1, p2 ->
                            Collator.getInstance().compare(p1.name, p2.name)
                        } as MutableList<ItemProduct>
                        _listItemShow.postValue(listItemProduct)
                    }
                    withContext(Dispatchers.Default)
                    {
                        itemBillDao = ItemBillDao(AppDatabase.getInstance(context))
                    }
                }
            }
        }
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
        showList = listItemProduct.filter {
            it.name.contains(
                searchString,
                true
            )
        } as MutableList<ItemProduct>

        _listItemShow.postValue(showList)
    }

    fun filterListItemShow() {
        Log.e("filter", filter.toString())
        var showList = mutableListOf<ItemProduct>()
        showList =
            listItemProduct.filter {
                it.name.contains(
                    search,
                    true
                )
            } as MutableList<ItemProduct>
        var res = mutableListOf<ItemProduct>()
        for (i in showList) {
            val filterCategory =
                (filter.category != null && filter.category.toString() != i.category)
            val filterColor = (filter.color != null && filter.color.toString() != i.color)
            val filterAvailable = (filter.available && i.availableQuantity <= 0)

            if (!filterAvailable && !filterColor && !filterCategory) {
                res.add(i)
            }

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
        Calendar.getInstance()


        Log.e(this.javaClass.simpleName, showList.size.toString() + "--" + listItemProduct.size)
        _listItemShow.postValue(res)
    }


    fun getColorOf(itemProduct: ItemProduct): List<String> {
        var mutableList: MutableList<String> = mutableListOf()
        for (i in listItemProduct) {
            if (i.name == itemProduct.name) {
                mutableList.add(itemProduct.color)
            }
        }
        return mutableList
    }


}
