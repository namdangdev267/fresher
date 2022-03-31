package com.misa.fresher.data.entity

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.misa.fresher.util.CanContentValues
import kotlinx.parcelize.Parcelize

/**
 * Lớp dữ liệu chứa loại sản phẩm. VD: áo, quần, giày,...
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 4
 * @updated 3/9/2022: Tạo class
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 * @updated 3/17/2022: Thay serialize bằng parcelable
 * @updated 3/25/2022: Thêm constructor và impl [CanContentValues] phục vụ trao đổi dữ liệu với local database
 */
@Parcelize
data class Category(
    val id: Long,
    val name: String,
) : Parcelable, CanContentValues {

    constructor(cursor: Cursor) : this(
        cursor.getLong(cursor.getColumnIndexOrThrow(ID)),
        cursor.getString(cursor.getColumnIndexOrThrow(NAME))
    )

    constructor(id: Long): this(id, "")

    override fun getContentValues(): ContentValues =
        ContentValues().apply {
            put(NAME, name)
        }

    override fun getContentValuesWithId(): ContentValues =
        ContentValues().apply {
            put(ID, id)
            put(NAME, name)
        }

    companion object {
        const val TABLE_NAME = "category"
        const val ID = "id"
        const val NAME = "name"
    }
}
