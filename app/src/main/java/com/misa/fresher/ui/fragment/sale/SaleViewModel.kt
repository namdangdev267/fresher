package com.misa.fresher.fragment.sale

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.R
import com.misa.fresher.data.models.Product
import com.misa.fresher.data.models.enum.Category
import com.misa.fresher.data.models.enum.Color
import com.misa.fresher.data.models.enum.SortBy
import com.misa.fresher.data.source.AppDatabaseHelper
import com.misa.fresher.data.source.local.dao.ProductDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.Collator
import java.util.*

@Suppress("CAST_NEVER_SUCCEEDS")
class SaleViewModel : ViewModel() {
    private var search: String = ""

    var filter: Filter = Filter(null, null, false, SortBy.NAME)

    var listProduct: MutableList<Product> = mutableListOf()

    private val _listProductShow = MutableLiveData<MutableList<Product>>()
    val listProductShow: LiveData<MutableList<Product>>
        get() = _listProductShow
    var searchList = MutableLiveData<MutableList<Product>>(mutableListOf())
    var filterList = MutableLiveData<MutableList<Product>>(mutableListOf())

    data class Filter(
        var category: Category?,
        var color: Color?,
        var available: Boolean,
        var sortBy: SortBy?
    )

    fun createData(context: Context) {
        _listProductShow.postValue(listProduct)
        CoroutineScope(IO).launch {
            val productDao = ProductDao(AppDatabaseHelper.getInstance(context))
            var listAllProduct =
                withContext(Dispatchers.Default) {
                    productDao.getAllProducts()
                }

            if (listAllProduct.size == 0) {
                for (i in 1..6) {
                    listProduct.add(
                        Product(
                            R.drawable.shirt,
                            i.toString() + "shirt" + i.toString(),
                            "SH$i",
                            Color.BLUE.name,
                            Category.SHIRT.name,
                            1000 * i,
                            i,
                            "23/02/2020"
                        )
                    )
                }
                for (i in 1..5) {
                    listProduct.add(
                        Product(
                            R.drawable.shorts,
                            i.toString() + "trouser" + i.toString(),
                            "TS$i",
                            Color.RED.name,
                            Category.TROUSER.name,
                            1000 * i,
                            i,
                            "14/06/2021"
                        )
                    )
                }
                for (i in 1..4) {
                    listProduct.add(
                        Product(
                            R.drawable.phone,
                            i.toString() + "electronic" + i.toString(),
                            "ET$i",
                            Color.YELLOW.name,
                            Category.ELECTRONIC.name,
                            1000 * i,
                            i,
                            "05/03/2022"
                        )
                    )
                }

                for (item in listProduct) {
                    productDao.addProduct(item)
                }
                listAllProduct =
                    withContext(Dispatchers.Default) {
                        productDao.getAllProducts()
                    }
                _listProductShow.postValue(listProduct)

            } else {
                withContext(Main) {
                    Log.d("test:", listAllProduct[0].toString())
                }
                _listProductShow.postValue(listAllProduct)
            }
        }

    }

    fun updateListItemShow(searchString: String) {
        searchList.value = _listProductShow.value?.filter {
            it.nameProduct.contains(
                searchString,
                true
            )
        } as MutableList<Product>
        searchList.postValue(searchList.value)
    }

    /**
     * Mục địch sử dụng: Xóa filter
     * Sử dụng khi: người dùng click button Clear Filter
     * @Author Truong Trung Kien
     * @date: 3/23/2022 8:35 PM
     **/
    fun clearFilter() {
        filter.sortBy = SortBy.NAME
        filter.category = null
        filter.color = null
        filter.available = false
    }

    /**
     * Mục địch sử dụng: Filter sản phẩm theo yêu cầu
     * Sử dụng khi: người dùng click btn Filter
     * @Author Truong Trung Kien
     * @date: 3/23/2022 8:36 PM
     **/
    fun filterShow() {
        filterList.value =
            _listProductShow.value?.filter {
                it.nameProduct.contains(
                    search,
                    true
                )
            } as MutableList<Product>
        var res = mutableListOf<Product>()
        for (i in filterList.value!!) {
            val filterCategory =
                (filter.category != null && filter.category.toString() != i.category)
            val filterColor = (filter.color != null && filter.color.toString() != i.color)
            val filterAvailable = (filter.available && i.quantity <= 0)
            if (!filterAvailable && !filterColor && !filterCategory) {
                res.add(i)
            }

        }

        Log.e(this.javaClass.simpleName, res.toString())

        if (res.size != 0) {
            res = when (filter.sortBy) {
                SortBy.NAME -> res.sortedWith { p1, p2 ->
                    Collator.getInstance().compare(p1.nameProduct, p2.nameProduct)
                } as MutableList<Product>
                SortBy.NEW_ARRIVAL -> res.sortedWith { p1, p2 ->
                    Collator.getInstance().compare(p1.dateArrival, p2.dateArrival)
                } as MutableList<Product>
                else -> res.sortedByDescending {
                    it.quantity
                } as MutableList<Product>
            }
        }
        Log.e(this.javaClass.simpleName + "after sort: ", res.toString())
        Calendar.getInstance()

//        Log.e(this.javaClass.simpleName, showListProduct.size.toString() + "--" + listProduct.size)
        filterList.postValue(res)
    }

    fun getColor(product: Product): List<String> {
        val mutableList: MutableList<String> = mutableListOf()
        for (i in listProduct) {
            if (i.nameProduct == product.nameProduct) {
                mutableList.add(product.color)
            }
        }
        return mutableList
    }
}