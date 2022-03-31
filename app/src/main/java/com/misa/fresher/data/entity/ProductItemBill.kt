package com.misa.fresher.data.entity

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.misa.fresher.util.CanContentValues
import kotlinx.parcelize.Parcelize

/**
 * Lớp thực thể chứa thông tin 1 sản phẩm trong giỏ hàng, đơn hàng,...
 *
 * @author Nguyễn Công Chính
 * @since 3/12/2022
 *
 * @version 6
 * @updated 3/12/2022: Tạo class
 * @updated 3/12/2022: Đổi tên có suffix là Model
 * @updated 3/15/2022: Đổi tên thành [ProductItemBill], chuyển nhà sang package [com.misa.fresher.data.entity]
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 * @updated 3/17/2022: Thay serialize bằng parcelable
 * @updated 3/25/2022: Thêm constructor và impl [CanContentValues] phục vụ trao đổi dữ liệu với local database
 */
@Parcelize
data class ProductItemBill(
    val id: Long,
    val item: ProductItem,
    var quantity: Int
) : Parcelable, CanContentValues {

    constructor(cursor: Cursor): this(
        cursor.getLong(cursor.getColumnIndexOrThrow(ID)),
        ProductItem(cursor.getLong(cursor.getColumnIndexOrThrow(ITEM_ID))),
        cursor.getInt(cursor.getColumnIndexOrThrow(QUANTITY))
    )

    override fun getContentValues(): ContentValues =
        ContentValues().apply {
            put(ITEM_ID, item.id)
            put(QUANTITY, quantity)
        }

    override fun getContentValuesWithId(): ContentValues =
        ContentValues().apply {
            put(ID, id)
            put(ITEM_ID, item.id)
            put(QUANTITY, quantity)
        }

    companion object {
        const val TABLE_NAME = "product_item_bill"
        const val ID = "id"
        const val ITEM_ID = "item_id"
        const val QUANTITY = "quantity"
        const val BILL_ID = "bill_id"
    }
}
