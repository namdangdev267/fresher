package com.misa.fresher.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Base class cho các View holder
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 1
 * @updated 3/10/2022: Tạo class
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
    abstract fun bindData(data: T)
}