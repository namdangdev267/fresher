package com.misa.fresher.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Lớp dữ liệu chứa loại sản phẩm. VD: áo, quần, giày,...
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 3
 * @updated 3/9/2022: Tạo class
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 * @updated 3/17/2022: Thay serialize bằng parcelable
 */
@Parcelize
data class Category(
    val id: Long,
    val name: String,
) : Parcelable
