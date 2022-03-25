package com.misa.fresher.ui.sale.bill

import com.misa.fresher.core.BaseContract
import com.misa.fresher.data.entity.Bill
import com.misa.fresher.data.entity.Customer

/**
 * Contract cho màn hình thanh toán
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 1
 * @updated 3/21/2022: Tạo class
 * @updated 3/25/2022: Thêm các hàm để lấy id hóa đơn kế tiếp, chọn khách hàng, lưu hóa đơn
 */
interface BillContract {

    interface View: BaseContract.View {

        fun getNextIdSuccess(id: Long)
        fun randomCustomerSuccess(customer: Customer)
        fun randomCustomerFailure()
        fun saveBillSuccess()
        fun saveBillFailure()
    }

    interface Presenter: BaseContract.Presenter {

        /**
         * Tìm kiếm id tiếp theo của hóa đơn và hiển thị chúng lên thanh toolbar
         *
         * @author Nguyễn Công Chính
         * @since 3/25/2022
         *
         * @version 1
         * @updated 3/25/2022: Tạo function
         */
        fun getNextId()

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
         * Lưu hóa đơn mới
         *
         * @author Nguyễn Công Chính
         * @since 3/25/2022
         *
         * @version 1
         * @updated 3/25/2022: Tạo function
         */
        fun saveBill(bill: Bill)
    }
}