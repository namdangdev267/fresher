package com.misa.fresher.ui.sale

import android.content.Context
import com.misa.fresher.data.dao.product.ProductDao
import com.misa.fresher.data.database.AppDbHelper
import com.misa.fresher.data.model.FilterProducts
import com.misa.fresher.data.model.Products
import com.misa.fresher.data.model.SelectedProducts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SalePresenter(context: Context) : SaleContract.Presenter {
    private var ListProductFromdb = mutableListOf<Products>()
    private var searchProducts = mutableListOf<Products>()
    private var selectedItems = arrayListOf<SelectedProducts>()
    private var view: SaleContract.View? = null
    val productDao = ProductDao.getInstance(AppDbHelper.getInstance(context))
    override fun getListProductFromDb() {
        CoroutineScope(Dispatchers.IO).launch {
            ListProductFromdb = productDao.getAllProducts()
            searchProducts = ListProductFromdb
            withContext(Dispatchers.Main) {
                view?.updateRecyclerViewProduct(ListProductFromdb)
            }
        }
    }

    override fun selectProduct(products: Products, amount: Int) {
        selectedItems.find { it.product == products }?.let {
            it.amonut += amount
        } ?: run {
            selectedItems.add(SelectedProducts(amount, products))
        }
        getSelectedProduct()

    }

    override fun getSelectedProduct() {
        val totalAmount = selectedItems.sumOf { it.amonut }
        val totalPrice = selectedItems.sumOf { it.product.price * it.amonut }
        view?.updateSelectedProduct(totalAmount, totalPrice)
    }

    override fun clearSelected() {
        selectedItems.clear()
        getSelectedProduct()
    }

    override fun searchEvent(text: String) {
        if (text != "") {
            searchProducts = ListProductFromdb.filter {
                it.name.uppercase().contains(text.uppercase()) || it.code.uppercase()
                    .contains(text.uppercase())
            } as MutableList
            view?.updateRecyclerViewProduct(searchProducts)
        }
    }

    override fun setCustomer() {
        TODO()
    }

    override fun getListSelectedProductToBillDetail() {
        if (selectedItems.size > 0) {
            view?.navigation(selectedItems)
        }
    }

    override fun filterItems(filter: FilterProducts) {
        var sortList = mutableListOf<Products>()
        if (filter.sortBy == "Tên") {
            sortList =
                ListProductFromdb.sortedWith(compareBy(Products::name)) as MutableList<Products>
        } else if (filter.sortBy == "Giá") {
            sortList =
                ListProductFromdb.sortedWith(compareBy(Products::price)) as MutableList<Products>
        }
        var sortListWithColor = mutableListOf<Products>()
        var sortListWithSize = mutableListOf<Products>()
        if (filter.coler != "cham de chon") {
            // nếu trùng với phần tử đầu tiên của arrayColor thì không xử lý
            sortListWithColor = sortList.filter {
                it.color == filter.coler
            } as MutableList<Products>
            if (filter.size != "cham de chon") {
                sortListWithSize = sortListWithColor.filter {
                    it.size == filter.size
                } as MutableList<Products>
            } else {
                sortListWithSize = sortListWithColor
            }
        } else {
            sortListWithColor = sortList
            if (filter.size != "cham de chon") {
                // nếu trùng với phần tử đầu tiên của arraySize thì không xử lý
                sortListWithSize = sortListWithColor.filter {
                    it.size == filter.size
                } as MutableList<Products>
            } else {
                sortListWithSize = sortListWithColor
            }
        }
        view?.updateRecyclerViewProduct(sortListWithSize)
    }

    override fun attach(view: SaleContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

}