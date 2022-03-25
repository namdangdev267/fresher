package com.misa.fresher.ui.sale

import android.content.Context
import com.misa.fresher.common.RandomSingleton
import com.misa.fresher.core.BasePresenter
import com.misa.fresher.data.entity.*
import com.misa.fresher.data.model.FilterProductModel
import com.misa.fresher.util.enum.ProductSortType

/**
 * Presenter cho màn hình bán hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/23/2022
 *
 * @version 2
 * @updated 3/23/2022: Tạo class
 * @updated 3/25/2022: override các hàm mới theo [SaleContract.Presenter]
 */
class SalePresenter(
    view: SaleContract.View,
    context: Context
) : BasePresenter<SaleContract.View>(view, context), SaleContract.Presenter {

    private var selectedItems = mutableListOf<ProductItemBill>()
    private val filter = FilterProductModel()
    private val category = mutableListOf<Category>()

    /**
     * @version 2
     * @updated 3/23/2022: Override lần đầu
     * @updated 3/25/2022: Chuyển thành lấy dữ liệu từ database
     */
    override fun filterByKeyword(keyword: String) {
        filter.keyword = keyword
        dataManager.getAllProduct()
            .onSuccess {
                val filterItems = filter.filter(it)
                view.updateProductList(filterItems.toMutableList())
            }
            .call()
    }

    /**
     * @version 2
     * @updated 3/23/2022: Override lần đầu
     * @updated 3/25/2022: Chuyển thành lấy dữ liệu từ database
     */
    override fun filterByAttr(
        isQuantityMoreThanZero: Boolean,
        categoryPosition: Int,
        sortBy: ProductSortType
    ) {
        filter.isQuantityMoreThanZero = isQuantityMoreThanZero
        filter.selectedCategory = if (categoryPosition >= 0) category[categoryPosition] else null
        filter.sortBy = sortBy
        dataManager.getAllProduct()
            .onSuccess {
                val filterItems = filter.filter(it)
                view.updateProductList(filterItems.toMutableList())
            }
            .call()
    }

    /**
     * @version 1
     * @updated 3/23/2022: Override lần đầu
     */
    override fun clearSelectedItem() {
        selectedItems.clear()
        view.updateSelectedItems(selectedItems)
    }

    /**
     * @version 1
     * @updated 3/23/2022: Override lần đầu
     */
    override fun getSelectedItem(): MutableList<ProductItemBill> = selectedItems

    /**
     * @version 1
     * @updated 3/23/2022: Override lần đầu
     */
    override fun addSelectedItem(
        product: Product,
        quantity: Int,
        color: ProductColor,
        size: ProductSize,
        unit: ProductUnit
    ) {
        val item = product.items.find {
            it.color == color && it.size == size && it.unit == unit
        }
        item?.let {
            selectedItems.find { it.item == item }?.let {
                it.quantity += quantity
            } ?: run {
                selectedItems.add(ProductItemBill(1L, item, quantity))
            }
            view.updateSelectedItems(selectedItems)
        }
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getAllCategory() {
        dataManager.getAllCategory()
            .onSuccess {
                category.addAll(it)
                view.getAllCategorySuccess(it)
            }
            .onFailure { view.getAllCategorySuccess(listOf()) }
            .call()
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun randomCustomer() {
        dataManager.getAllCustomer()
            .onSuccess {
                view.randomCustomerSuccess(it[RandomSingleton.getInstance().nextInt(it.size)])
            }
            .onFailure {
                view.randomCustomerFailure()
            }
            .call()
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun updateSelectedItems() {
        view.updateSelectedItems(selectedItems)
    }
}