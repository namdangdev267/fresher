package com.misa.fresher.data.entity

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.misa.fresher.core.CanContentValues
import kotlinx.parcelize.Parcelize

/**
 * Lớp thực thể chứa thông tin khách hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/14/2022
 *
 * @version 3
 * @updated 3/14/2022: Tạo class
 * @updated 3/17/2022: Thay serialize bằng parcelable
 * @updated 3/25/2022: Thêm constructor và impl [CanContentValues] phục vụ trao đổi dữ liệu với local database
 */
@Parcelize
data class Customer(
    val id: Long,
    val name: String,
    val tel: String,
    val address: String
) : Parcelable, CanContentValues {

    constructor(cursor: Cursor) : this(
        cursor.getLong(cursor.getColumnIndexOrThrow(ID)),
        cursor.getString(cursor.getColumnIndexOrThrow(NAME)),
        cursor.getString(cursor.getColumnIndexOrThrow(TEL)),
        cursor.getString(cursor.getColumnIndexOrThrow(ADDRESS)),
    )

    constructor(id: Long): this(id, "", "", "")

    override fun toString(): String = "$name ($tel)"

    override fun getContentValues(): ContentValues =
        ContentValues().apply {
            put(NAME, name)
            put(TEL, tel)
            put(ADDRESS, address)
        }

    override fun getContentValuesWithId(): ContentValues =
        ContentValues().apply {
            put(ID, id)
            put(NAME, name)
            put(TEL, tel)
            put(ADDRESS, address)
        }

    companion object {
        const val TABLE_NAME = "customer"
        const val ID = "id"
        const val NAME = "name"
        const val TEL = "tel"
        const val ADDRESS = "address"
    }
}
