package com.misa.fresher.data.entity

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.misa.fresher.util.CanContentValues
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Lớp dữ liệu chứa thông tin mỗi loại nhỏ trong 1 sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 7
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Bổ sung 2 thuộc tính tên và mã sản phẩm
 * @updated 3/15/2022: Đổi tên thuộc tính quantity -> [quantityAvailable]
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 * @updated 3/17/2022: Thay serialize bằng parcelable
 * @updated 3/18/2022: Thay trường [createdAt] từ Calendar -> Date
 * @updated 3/25/2022: Thêm constructor và impl [CanContentValues] phục vụ trao đổi dữ liệu với local database
 */
@Parcelize
data class ProductItem(
    val id: Long,
    val color: ProductColor,
    val size: ProductSize,
    val unit: ProductUnit,
    val price: Double,
    val quantityAvailable: Int,
    val createdAt: Date,
    var name: String = "",
    var code: String = ""
) : Parcelable, CanContentValues {

    constructor(cursor: Cursor): this(
        cursor.getLong(cursor.getColumnIndexOrThrow(ID)),
        ProductColor(cursor.getLong(cursor.getColumnIndexOrThrow(COLOR_ID))),
        ProductSize(cursor.getLong(cursor.getColumnIndexOrThrow(SIZE_ID))),
        ProductUnit(cursor.getLong(cursor.getColumnIndexOrThrow(UNIT_ID))),
        cursor.getDouble(cursor.getColumnIndexOrThrow(PRICE)),
        cursor.getInt(cursor.getColumnIndexOrThrow(QUANTITY_AVAILABLE)),
        Date(cursor.getLong(cursor.getColumnIndexOrThrow(CREATE_AT))),
        cursor.getString(cursor.getColumnIndexOrThrow(NAME)),
        cursor.getString(cursor.getColumnIndexOrThrow(CODE))
    )

    constructor(id: Long) : this(
        id,
        ProductColor(0),
        ProductSize(0),
        ProductUnit(0),
        0.0,
        0,
        Date(),
        "",
        ""
    )

    override fun getContentValues(): ContentValues =
        ContentValues().apply {
            put(COLOR_ID, color.id)
            put(SIZE_ID, size.id)
            put(UNIT_ID, unit.id)
            put(PRICE, price)
            put(QUANTITY_AVAILABLE, quantityAvailable)
            put(CREATE_AT, createdAt.time)
            put(NAME, name)
            put(CODE, code)
        }

    override fun getContentValuesWithId(): ContentValues =
        ContentValues().apply {
            put(ID, id)
            put(COLOR_ID, color.id)
            put(SIZE_ID, size.id)
            put(UNIT_ID, unit.id)
            put(PRICE, price)
            put(QUANTITY_AVAILABLE, quantityAvailable)
            put(CREATE_AT, createdAt.time)
            put(NAME, name)
            put(CODE, code)
        }

    companion object {
        const val TABLE_NAME = "product_item"
        const val ID = "id"
        const val COLOR_ID = "color_id"
        const val SIZE_ID = "size_id"
        const val UNIT_ID = "unit_id"
        const val PRICE = "price"
        const val QUANTITY_AVAILABLE = "quantity_available"
        const val CREATE_AT = "create_at"
        const val NAME = "name"
        const val CODE = "code"
        const val PRODUCT_ID = "product_id"
    }
}
