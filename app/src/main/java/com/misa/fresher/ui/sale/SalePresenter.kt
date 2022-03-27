package com.misa.fresher.ui.sale

import com.misa.fresher.common.FakeData
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
 * @version 1
 * @updated 3/23/2022: Tạo class
 */
class SalePresenter(
    view: SaleContract.View
) : BasePresenter<SaleContract.View>(view), SaleContract.Presenter {

    private var selectedItems = mutableListOf<ProductItemBill>()
    private val filter = FilterProductModel()

    override fun filterByKeyword(keyword: String) {
        filter.keyword = keyword
        val filterItems = filter.filter(FakeData.products)
        view.updateProductList(filterItems.toMutableList())
    }

    override fun filterByAttr(
        isQuantityMoreThanZero: Boolean,
        categoryPosition: Int,
        sortBy: ProductSortType
    ) {
        filter.isQuantityMoreThanZero = isQuantityMoreThanZero
        filter.selectedCategory = FakeData.category[categoryPosition]
        filter.sortBy = sortBy
        val filterItems = filter.filter(FakeData.products)
        view.updateProductList(filterItems.toMutableList())
    }

    override fun clearSelectedItem() {
        selectedItems.clear()
        view.updateSelectedItems(selectedItems)
    }

    override fun getSelectedItem(): MutableList<ProductItemBill> = selectedItems

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
                selectedItems.add(ProductItemBill(item, quantity))
            }
            view.updateSelectedItems(selectedItems)
        }
    }
}