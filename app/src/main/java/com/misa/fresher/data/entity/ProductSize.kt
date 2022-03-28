package com.misa.fresher.data.entity

import android.content.ContentValues
import android.database.Cursor
import com.misa.fresher.core.CanContentValues
import kotlinx.parcelize.Parcelize

/**
 * Lớp dữ liệu chứa kích thước sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 6
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Kế thừa từ lớp [com.misa.fresher.data.entity.ProductType]
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 * @updated 3/17/2022: Thay serialize bằng parcelable
 * @updated 3/18/2022: Vì [ProductType] đã impl Parcelable nên [ProductSize] không cần phải làm việc đó nữa
 * @updated 3/25/2022: Thêm constructor và impl [CanContentValues] phục vụ trao đổi dữ liệu với local database
 */
@Parcelize
data class ProductSize(
    override val id: Long,
    override val name: String,
    val code: String,
) : ProductType(id, name), CanContentValues {

    constructor(cursor: Cursor): this(
        cursor.getLong(cursor.getColumnIndexOrThrow(ID)),
        cursor.getString(cursor.getColumnIndexOrThrow(NAME)),
        cursor.getString(cursor.getColumnIndexOrThrow(CODE))
    )

    constructor(id: Long): this(id, "", "")

    override fun getContentValues(): ContentValues =
        ContentValues().apply {
            put(NAME, name)
            put(CODE, code)
        }

    override fun getContentValuesWithId(): ContentValues =
        ContentValues().apply {
            put(ID, id)
            put(NAME, name)
            put(CODE, code)
        }

    companion object {
        const val TABLE_NAME = "product_size"
        const val ID = "id"
        const val NAME = "name"
        const val CODE = "code"
    }
}
