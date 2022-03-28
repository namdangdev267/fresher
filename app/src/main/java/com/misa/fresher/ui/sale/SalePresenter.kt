package com.misa.fresher.ui.sale

import android.content.Context
import com.misa.fresher.data.model.product.ProductModel
import com.misa.fresher.developer.FakeDbData
import com.misa.fresher.di.Injector
import com.misa.fresher.utils.Enums
import com.misa.fresher.utils.toArrayList
import kotlinx.coroutines.*


class SalePresenter : SaleContract.Presenter {
    private var view: SaleContract.View? = null

    private var totalItems = arrayListOf<ProductModel>()
    private var filteredItems = arrayListOf<ProductModel>()
    private var displayedItems = arrayListOf<ProductModel>()
    private var selectedItems = arrayListOf<ProductModel>()

    override fun attach(view: SaleContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    /**
     * - fun's purpose: get all products in local database and display in recView
     *
     * @author HTLong
     * @edit_at 25/03/2022
     */
    override suspend fun getAllProducts(context: Context?) {
        context?.let {
            /**
             * - purpose: insert fake data to database
             *
             * @author HTLong
             * @edit_at 25/03/2022
             */
            FakeDbData.createDB(context)

            val pModelRepo = Injector.getProductModelRepository(it)
            val pItemRepo = Injector.getProductItemRepository(it)
            val pUnitRepo = Injector.getProductUnitRepository(it)

            val colors = arrayListOf("all")
            val sizes = arrayListOf("all")
            val categories = arrayListOf("all")

            val models = pModelRepo.getAll()
            models.forEach { model ->
                model.items = pItemRepo.getByModelId(modelId = model.id)
                model.units = pUnitRepo.getByModelId(modelId = model.id)

                categories.add(model.category)
                model.items.forEach { item ->
                    colors.add(item.color)
                    sizes.add(item.size)
                }
            }

            totalItems = models
            filteredItems = models
            displayedItems = models

            withContext(Dispatchers.Main) {
                view?.updateProductRecViewUI(displayedItems)
                view?.updateSaleFilterDrawerUI(colors.distinct(), sizes.distinct(), categories.distinct())
            }
        }
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
    override fun selectProduct(isSplit: Boolean, productModel: ProductModel) {
        if (productModel.items.size != 1) return
        var isExist = false
        if (!isSplit) {
            selectedItems.find { it.id == productModel.id && it.unit == productModel.unit }?.also {
                it.amount += productModel.amount
                isExist = true
            }
        }
        if (!isExist) selectedItems.add(productModel)

        getSelectedProductStatistic()
    }

    /**
     * - method's purpose: filter product
     *
     * @param isCheckQTY: available quantity product
     * @param viewMode: display product list as model or item
     *
     *
     * @author HTLong
     * @edit_at 2022-03-18
     */
    override suspend fun filterProducts(
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

            if (color != "all") filteredItems =
                filteredItems.filter { p -> p.items.any { it.color == color } }.toArrayList()
            if (size != "all") filteredItems =
                filteredItems.filter { p -> p.items.any { it.size == size } }.toArrayList()
            if (isCheckQTY) filteredItems = filteredItems.filter { it.quantity > 0 }.toArrayList()

        } else if (viewMode == Enums.Product.ITEM) {
            filteredItems = arrayListOf()
            for (product in totalItems) {
                if (category != "all" && product.category != category) continue
                for (item in product.items) {
                    if (color != "all" && item.color != color) continue
                    if (size != "all" && item.size != size) continue
                    if (isCheckQTY && item.quantity <= 0) continue

                    filteredItems.add(product.copy(items = arrayListOf(item)))
                }
            }
        }

        if (sortBy == "name") filteredItems.sortBy { it.name }
        else if (sortBy == "date") filteredItems.sortBy { it.date }

        searchProducts(search)
    }


    override suspend fun searchProducts(search: String) {
        displayedItems = if (search.isNotEmpty()) {
            val lcSearch = search.lowercase()
            filteredItems.filter {
                it.name.lowercase().contains(lcSearch)
            }.toArrayList()
        } else filteredItems

        withContext(Dispatchers.Main) { view?.updateProductRecViewUI(displayedItems) }
    }
}