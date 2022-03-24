package com.misa.fresher.fragment.sale

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.R
import com.misa.fresher.models.Product
import com.misa.fresher.models.enum.Category
import com.misa.fresher.models.enum.Color
import com.misa.fresher.models.enum.SortBy
import java.text.Collator
import java.util.*

class SaleViewModel : ViewModel() {
    private var search: String = ""

    var filter: Filter = Filter(null, null, false, SortBy.NAME)

    private var listProduct: MutableList<Product> = mutableListOf()

    private val _listProductShow = MutableLiveData<MutableList<Product>>()
    val listProductShow: LiveData<MutableList<Product>>
        get() = _listProductShow

    data class Filter(
        var category: Category?,
        var color: Color?,
        var available: Boolean,
        var sortBy: SortBy
    )

    fun initData() {
        fakeData()
        _listProductShow.postValue(listProduct)
    }

    private fun fakeData() {

        for (i in 1..6) {
            listProduct.add(
                Product(
                    R.drawable.shirt,
                    i.toString() + "shirt" + i.toString(), "SH$i", Color.BLUE, Category.SHIRT,
                    1000 * i, i, "23/02/2020"
                )
            )
        }

        for (i in 1..5) {
            listProduct.add(
                Product(
                    R.drawable.shorts,
                    i.toString() + "trouser" + i.toString(), "TS$i", Color.RED, Category.TROUSER,
                    1000 * i, i, "14/06/2021"
                )
            )
        }

        for (i in 1..4) {
            listProduct.add(
                Product(
                    R.drawable.phone,
                    i.toString() + "electronic" + i.toString(),
                    "ET$i",
                    Color.YELLOW,
                    Category.ELECTRONIC,
                    1000 * i,
                    i,
                    "05/03/2022"
                )
            )
        }

        listProduct = listProduct.sortedWith { p1, p2 ->
            Collator.getInstance().compare(p1.nameProduct, p2.nameProduct)
        } as MutableList<Product>

    }

    fun updateListItemShow(searchString: String) {
        search = searchString
        _listProductShow.postValue(listProduct)
        val showList = listProduct.filter { it.nameProduct.contains(searchString, true) }
        _listProductShow.postValue(showList as MutableList<Product>?)
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
        var showListFilter = mutableListOf<Product>()
        showListFilter =
            listProduct.filter { it.nameProduct.contains(search, true) } as MutableList<Product>

        var resultFilter = mutableListOf<Product>()

        for (i in showListFilter) {
            val filterCategory = (filter.category != null && filter.category != i.category)
            val filterColor = (filter.color != null && filter.color != i.color)
            val filterAvailable = (filter.available && i.quantity <= 0)

            if (!filterAvailable && !filterColor && !filterCategory) {
                resultFilter.add(i)
            }
        }

        if (resultFilter.size != 0) {
            resultFilter = when (filter.sortBy) {
                SortBy.NAME -> resultFilter.sortedWith { f1, f2 ->
                    Collator.getInstance().compare(f1.nameProduct, f2.nameProduct)
                } as MutableList<Product>
                SortBy.NEW_ARRIVAL -> resultFilter.sortedWith { f1, f2 ->
                    Collator.getInstance().compare(f1.dateArrival, f2.dateArrival)
                } as MutableList<Product>
                else -> resultFilter.sortedByDescending { it.quantity } as MutableList<Product>
            }

            Calendar.getInstance()

            _listProductShow.postValue(resultFilter)
        }
    }

    fun getColor(product: Product): List<Color> {
        val mutableList: MutableList<Color> = mutableListOf()
        for (i in listProduct) {
            if (i.nameProduct == product.nameProduct)
                mutableList.add(product.color)
        }
        return mutableList
    }
}