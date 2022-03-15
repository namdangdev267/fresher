package com.misa.fresher.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Base class cho các View holder
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 2
 * @updated 3/10/2022: Tạo class
 * @updated 3/15/2022: Truyền thêm index qua hàm [bindData] cho nó thêm phần long trọng
 */
abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {

    /**
     * Hàm cài đặt dữ liệu cho từng view holder
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 1
     * @updated 3/10/2022: Tạo function
     */
    abstract fun bindData(data: T, index: Int)
}