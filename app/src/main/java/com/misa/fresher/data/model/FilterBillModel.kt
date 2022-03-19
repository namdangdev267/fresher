package com.misa.fresher.data.model

import com.misa.fresher.data.entity.Bill
import com.misa.fresher.util.enum.TimeFilterType
import com.misa.fresher.util.toCalendar
import java.util.*

/**
 * Lớp model chứa thông tin về các loại lọc trong màn hình danh sách đơn hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/16/2022
 *
 * @version 1
 * @updated 3/16/2022: Tạo class
 */
class FilterBillModel {
    var keyword: String = ""
    var time: TimeFilterType = TimeFilterType.TODAY

    /**
     * Hàm lọc ra các đơn hàng dựa vào thông số ở trên
     *
     * @author Nguyễn Công Chính
     * @since 3/16/2022
     *
     * @version 2
     * @updated 3/16/2022: Tạo function
     * @updated 3/18/2022: Vì lớp thực thể có trường đổi từ Calendar -> Date, phần xử lí cần cập nhật theo
     */
    fun filter(input: List<Bill>): List<Bill> {
        var result = input

        // Lọc theo tên khách hàng, số điện thoại hoặc mã hóa đơn
        result = result.filter {
            it.id.toString().contains(keyword, true)
                    || it.customer.name.contains(keyword, true)
                    || it.customer.tel.contains(keyword, true)
        }

        // Lọc theo thời gian
        result = result.filter { bill ->
            bill.createdAt.toCalendar().let {
                when (time) {
                    TimeFilterType.TODAY -> {
                        val now = Calendar.getInstance()
                        now.get(Calendar.DAY_OF_YEAR) == it.get(Calendar.DAY_OF_YEAR)
                                && now.get(Calendar.YEAR) == it.get(Calendar.YEAR)
                    }
                    TimeFilterType.YESTERDAY -> {
                        val yesterday = Calendar.getInstance()
                        yesterday.add(Calendar.DATE, -1)
                        yesterday.get(Calendar.DAY_OF_YEAR) == it.get(Calendar.DAY_OF_YEAR)
                                && yesterday.get(Calendar.YEAR) == it.get(Calendar.YEAR)
                    }
                    TimeFilterType.BEFORE_YESTERDAY -> {
                        val beforeYesterday = Calendar.getInstance()
                        beforeYesterday.add(Calendar.DATE, -2)
                        beforeYesterday.get(Calendar.DAY_OF_YEAR) == it.get(Calendar.DAY_OF_YEAR)
                                && beforeYesterday.get(Calendar.YEAR) == it.get(Calendar.YEAR)
                    }
                    TimeFilterType.THIS_WEEK -> {
                        val now = Calendar.getInstance()
                        now.get(Calendar.WEEK_OF_YEAR) == it.get(Calendar.WEEK_OF_YEAR)
                                && now.get(Calendar.YEAR) == it.get(Calendar.YEAR)
                    }
                    TimeFilterType.OTHER -> {
                        true
                    }
                }
            }
        }

        return result
    }
}