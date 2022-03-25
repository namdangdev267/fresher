package com.misa.fresher.ui.sale.bill.deliveryinfo.receiverinfo

import com.misa.fresher.core.BaseContract
import com.misa.fresher.data.entity.Customer

/**
 * Contract cho màn nhập thông tin người nhận
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 2
 * @updated 3/21/2022: Tạo class
 * @updated 3/25/2022: Thêm hàm để chọn khách hàng
 */
interface ReceiverInfoContract {

    interface View: BaseContract.View {
        fun randomCustomerSuccess(customer: Customer)
        fun randomCustomerFailure()
    }

    interface Presenter: BaseContract.Presenter {

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
    }
}