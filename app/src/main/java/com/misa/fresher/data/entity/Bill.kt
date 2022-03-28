package com.misa.fresher.data.entity

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.misa.fresher.core.CanContentValues
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Lớp thực thể chứa thông tin đơn hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 5
 * @updated 3/15/2022: Tạo class
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 * @updated 3/17/2022: Thay serialize bằng parcelable
 * @updated 3/18/2022: Thay trường [createdAt] từ Calendar -> Date
 * @updated 3/25/2022: Thêm constructor và impl [CanContentValues] phục vụ trao đổi dữ liệu với local database
 */
@Parcelize
data class Bill(
    val id: Long,
    val customer: Customer,
    val items: List<ProductItemBill>,
    val createdAt: Date
) : Parcelable, CanContentValues {

    constructor(cursor: Cursor): this(
        cursor.getLong(cursor.getColumnIndexOrThrow(ID)),
        Customer(cursor.getLong(cursor.getColumnIndexOrThrow(CUSTOMER_ID))),
        listOf(),
        Date(cursor.getLong(cursor.getColumnIndexOrThrow(CREATE_AT))),
    )

    override fun getContentValues(): ContentValues =
        ContentValues().apply {
            put(CUSTOMER_ID, customer.id)
            put(CREATE_AT, createdAt.time)
        }

    override fun getContentValuesWithId(): ContentValues =
        ContentValues().apply {
            put(ID, id)
            put(CUSTOMER_ID, customer.id)
            put(CREATE_AT, createdAt.time)
        }

    companion object {
        const val TABLE_NAME = "bill"
        const val ID = "id"
        const val CUSTOMER_ID = "customer_id"
        const val CREATE_AT = "create_at"
    }
}
