package com.misa.fresher.ui.listbill

import com.misa.fresher.core.BaseContract
import com.misa.fresher.data.entity.Bill
import com.misa.fresher.util.enum.TimeFilterType

/**
 * Contract cho màn hình danh sách hóa đơn
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 1
 * @updated 3/21/2022: Tạo class
 */
interface ListBillContract {

    interface View: BaseContract.View {

        /**
         * Hàm cập nhật danh sách hóa đơn đã lọc lên giao diện
         *
         * @author Nguyễn Công Chính
         * @since 3/23/2022
         *
         * @version 1
         * @updated 3/23/2022: Tạo function
         */
        fun updateBillList(list: MutableList<Bill>)
    }

    interface Presenter: BaseContract.Presenter {

        /**
         * Hàm lọc hóa đơn theo mã đơn hàng, tên và số điện thoại khách hàng
         *
         * @author Nguyễn Công Chính
         * @since 3/23/2022
         *
         * @version 2
         * @updated 3/23/2022: Tạo function
         * @updated 3/31/2022: Param [keyword] giờ là nullable
         */
        fun filterByKeyword(keyword: String? = null)

        /**
         * Hàm lọc hóa đơn theo thời gian tạo hóa đơn
         *
         * @author Nguyễn Công Chính
         * @since 3/23/2022
         *
         * @version 1
         * @updated 3/23/2022: Tạo function
         */
        fun filterByTime(time: TimeFilterType)
    }
}