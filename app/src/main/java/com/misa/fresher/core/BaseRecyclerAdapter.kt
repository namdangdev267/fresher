package com.misa.fresher.core

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Base class cho các Recycler Adapter
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 3
 * @updated 3/10/2022: Tạo class
 * @updated 3/15/2022: Truyền thêm index qua hàm [BaseViewHolder.bindData] cho nó thêm phần long trọng
 * @updated 3/18/2022: Thêm chức năng tự so sánh và cập nhật view dùng [androidx.recyclerview.widget.DiffUtil]
 */
abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder<T>>(
    protected val items: MutableList<T>
) : RecyclerView.Adapter<VH>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bindData(items[position], position)

    /**
     * Hàm định nghĩa 2 item là 1 nhưng có nội dung thay đổi
     *
     * @author Nguyễn Công Chính
     * @since 3/18/2022
     *
     * @version 1
     * @updated 3/18/2022: Tạo function
     */
    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean

    /**
     * Hàm định nghĩa 2 item khác nhau hoàn toàn
     *
     * @author Nguyễn Công Chính
     * @since 3/18/2022
     *
     * @version 1
     * @updated 3/18/2022: Tạo function
     */
    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    /**
     * Cập nhật recycler view với list truyền vào là kết quả sau khi update
     *
     * @author Nguyễn Công Chính
     * @since 3/18/2022
     *
     * @version 1
     * @updated 3/18/2022: Tạo function
     */
    fun updateData(newItems: MutableList<T>) {
        val diffCallback = BaseDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    /**
     * Lớp đóng vai trò so sánh, tính toán item thêm, sửa, xóa giữa 2 list
     *
     * @author Nguyễn Công Chính
     * @since 3/18/2022
     *
     * @version 1
     * @updated 3/18/2022: Tạo class
     */
    private inner class BaseDiffCallback(
        private val oldItems: MutableList<T>,
        private val newItems: MutableList<T>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            areItemsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            areContentsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])
    }
}