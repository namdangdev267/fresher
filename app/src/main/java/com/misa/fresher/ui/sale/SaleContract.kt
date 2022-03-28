package com.misa.fresher.ui.sale

import com.misa.fresher.core.BaseContract
import com.misa.fresher.data.entity.*
import com.misa.fresher.util.enum.ProductSortType

/**
 * Contract cho màn hình bán hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 2
 * @updated 3/21/2022: Tạo class
 * @updated 3/25/2022: Bổ sung hàm để lấy các tt danh mục hàng hóa, chọn khách hàng từ database
 */
interface SaleContract {

    interface View: BaseContract.View {

        /**
         * Hàm cập nhật danh sách sản phẩm lên giao diện
         *
         * @author Nguyễn Công Chính
         * @since 3/21/2022
         *
         * @version 1
         * @updated 3/21/2022: Tạo function
         */
        fun updateProductList(list: MutableList<Product>)

        /**
         * Hàm cập nhật danh sách sản phẩm đã chọn lên giao diện
         *
         * @author Nguyễn Công Chính
         * @since 3/21/2022
         *
         * @version 1
         * @updated 3/21/2022: Tạo function
         */
        fun updateSelectedItems(list: MutableList<ProductItemBill>)

        fun getAllCategorySuccess(list: List<Category>)
        fun randomCustomerSuccess(customer: Customer)
        fun randomCustomerFailure()
    }

    interface Presenter: BaseContract.Presenter {

        /**
         * Hàm lọc sản phẩm theo tên, mã sản phẩm
         *
         * @author Nguyễn Công Chính
         * @since 3/21/2022
         *
         * @version 1
         * @updated 3/21/2022: Tạo function
         */
        fun filterByKeyword(keyword: String)

        /**
         * Hàm lọc sản phẩm theo các thuộc tính: số hàng còn lại > 0, danh mục sản phẩm, kiểu sắp xếp
         *
         * @author Nguyễn Công Chính
         * @since 3/21/2022
         *
         * @version 1
         * @updated 3/21/2022: Tạo function
         */
        fun filterByAttr(
            isQuantityMoreThanZero: Boolean,
            categoryPosition: Int,
            sortBy: ProductSortType
        )

        /**
         * Hàm xóa toàn bộ sản phẩm đã chọn
         *
         * @author Nguyễn Công Chính
         * @since 3/21/2022
         *
         * @version 1
         * @updated 3/21/2022: Tạo function
         */
        fun clearSelectedItem()

        /**
         * Hàm lấy danh sách sản phẩm đã chọn để chuyển qua màn khác
         *
         * @author Nguyễn Công Chính
         * @since 3/21/2022
         *
         * @version 1
         * @updated 3/21/2022: Tạo function
         */
        fun getSelectedItem(): MutableList<ProductItemBill>

        /**
         * Hàm thêm item vào giỏ hàng
         *
         * @author Nguyễn Công Chính
         * @since 3/21/2022
         *
         * @version 1
         * @updated 3/21/2022: Tạo function
         */
        fun addSelectedItem(
            product: Product,
            quantity: Int,
            color: ProductColor,
            size: ProductSize,
            unit: ProductUnit
        )

        /**
         * Hàm lấy toàn bộ danh sách loại sản phẩm
         *
         * @author Nguyễn Công Chính
         * @since 3/24/2022
         *
         * @version 1
         * @updated 3/24/2022: Tạo function
         */
        fun getAllCategory()

        /**
         * Hàm chọn 1 khách hàng ngẫu nhiên từ danh sách khách hàng có sẵn
         *
         * @author Nguyễn Công Chính
         * @since 3/24/2022
         *
         * @version 1
         * @updated 3/24/2022: Tạo function
         */
        fun randomCustomer()

        /**
         * Hàm cập nhật danh sách sản phẩm đã chọn lên giao diện
         *
         * @author Nguyễn Công Chính
         * @since 3/21/2022
         *
         * @version 1
         * @updated 3/21/2022: Tạo function
         */
        fun updateSelectedItems()

    }
}