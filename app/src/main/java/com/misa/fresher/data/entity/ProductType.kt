package com.misa.fresher.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Lớp thực thể chứa thông tin chung giữa các lớp màu sắc, kích thước, đơn vị...
 *
 * @author Nguyễn Công Chính
 * @since 3/11/2022
 *
 * @version 3
 * @updated 3/11/2022: Tạo class
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 * @updated 3/17/2022: Thay serialize bằng parcelable
 */
@Parcelize
open class ProductType(
    open val id: Long,
    open val name: String,
) : Parcelable