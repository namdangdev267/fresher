package com.misa.fresher.ui.sale

import com.misa.fresher.data.model.product.Product
import com.misa.fresher.global.FakeData
import com.misa.fresher.utils.Enums
import com.misa.fresher.utils.toArrayList


class SalePresenter : SaleContract.Presenter {
    private var view: SaleContract.View? = null

    private val totalItems = FakeData.products
    private var filteredItems = totalItems
    private var displayedItems = filteredItems
    private var selectedItems = arrayListOf<Product>()


    override fun attach(view: SaleContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun getDisplayProducts() {
        view?.updateProductRecViewUI(displayedItems)
    }

    /**
     * - method's purpose: calculate total amount and price of selected items
     *
     * @author HTLong
     * @edit_at 2022-03-18
     */
    override fun getSelectedProductStatistic() {
        val totalAmount = selectedItems.sumOf { it.amount }
        val totalPrice = selectedItems.sumOf { it.price }

        view?.updateProductSelectedUI(totalAmount, totalPrice)
    }

    override fun clearSelectedProducts() {
        selectedItems.clear()
        getSelectedProductStatistic()
    }


    /**
     * - method's purpose: check size of selected items before navigate to bill fragment
     *
     * @author HTLong
     * @edit_at 2022-03-18
     */
    override fun checkSelectedProducts() {
        if (selectedItems.isNotEmpty()) view?.navToBillFragment(selectedItems)
    }


    /**
     * - method's purpose: add an product to selected list
     * - note: make sure list items of product have only 1 item
     *
     * @param isSplit: split added product to a new row or increase amount of existed product
     *
     * @author HTLong
     * @edit_at 2022-03-18
     */
    override fun selectProduct(isSplit: Boolean, product: Product) {
        if (product.items.size != 1) return
        var isExist = false
        if (!isSplit) {
            selectedItems.find {
                it.name == product.name && it.code == product.code && it.items == product.items
            }?.also {
                it.amount += product.amount
                isExist = true
            }
        }
        if (!isExist) selectedItems.add(product)

        getSelectedProductStatistic()
    }

    /**
     * - method's purpose: get all options which were used for filter
     *
     * @author HTLong
     * @edit_at 2022-03-18
     */
    override fun getFilterOptions() {
        val categories = arrayListOf("all")
        val colors = arrayListOf("all")
        val sizes = arrayListOf("all")
        totalItems.forEach { p ->
            categories.add(p.category)
            p.items.forEach { item ->
                colors.add(item.color)
                sizes.add(item.size)
            }
        }

        view?.updateSaleFilterDrawerUI(colors.distinct(), sizes.distinct(), categories.distinct())
    }

    /**
     * - method's purpose: filter product
     *
     * @param isCheckQTY: available quantity product
     * @param viewMode: display product list as model or item
     *
     * @author HTLong
     * @edit_at 2022-03-18
     */
    override fun filterProducts(
        isCheckQTY: Boolean,
        category: String,
        viewMode: Enums.Product,
        sortBy: String,
        color: String,
        size: String,
        search: String
    ) {
        if (viewMode == Enums.Product.MODEL) {
            filteredItems = totalItems

            if (category != "all") filteredItems = filteredItems.filter {
                it.category == category
            }.toArrayList()

            if (color != "all") filteredItems = filteredItems.filter { p -> p.items.any { it.color == color } }.toArrayList()
            if (size != "all") filteredItems = filteredItems.filter { p -> p.items.any { it.size == size } }.toArrayList()
            if (isCheckQTY) filteredItems = filteredItems.filter { it.quantity > 0 }.toArrayList()

        } else if (viewMode == Enums.Product.ITEM) {
            filteredItems = arrayListOf()
            for (product in totalItems) {
                if (category != "all" && product.category != category) continue
                for (item in product.items) {
                    if(color != "all" && item.color != color) continue
                    if(size != "all" && item.size != size) continue
                    if(isCheckQTY && item.quantity <= 0) continue

                    filteredItems.add(product.copy(items = arrayListOf(item)))
                }
            }
        }

        if (sortBy == "name") filteredItems.sortBy { it.name }
        else if (sortBy == "date") filteredItems.sortBy { it.date }
        searchProducts(search)
    }


    override fun searchProducts(search: String) {
        displayedItems = if (search.isNotEmpty()) {
            val lcSearch = search.lowercase()
            filteredItems.filter {
                it.name.lowercase().contains(lcSearch)
            }.toArrayList()
        } else filteredItems

        view?.updateProductRecViewUI(displayedItems)
    }
}