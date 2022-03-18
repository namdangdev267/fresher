package com.misa.fresher.ui.sale.bill.deliveryinfo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.misa.fresher.core.BaseRecyclerAdapter
import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.model.InputInfoModel
import com.misa.fresher.databinding.*
import com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.viewholder.*
import com.misa.fresher.util.enum.InputInfoType

/**
 * Adapter cho danh sách các trường nhập liệu tại màn thông tin giao hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 2
 * @updated 3/15/2022: Tạo class
 * @updated 3/18/2022: Override hàm [areContentsTheSame], [areItemsTheSame] tương ứng với lớp cha, thêm hàm để cập nhật và lấy dữ liệu tại item xác định
 */
class DeliveryInputAdapter(
    val inputItems: List<InputInfoModel>,
    private val context: Context
) : BaseRecyclerAdapter<InputInfoModel, BaseViewHolder<InputInfoModel>>(inputItems.toMutableList()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<InputInfoModel> = when (viewType) {
        InputInfoType.TAP_ACTION.ordinal -> TapActionViewHolder(
            ItemTapActionInputBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            context
        )
        InputInfoType.TAP_INSERT.ordinal -> TapInsertViewHolder(
            ItemTapInsertInputBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        InputInfoType.SPINNER.ordinal -> SpinnerViewHolder(
            ItemSpinnerInputBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            context
        )
        InputInfoType.TWO_COLUMN.ordinal -> TwoColumnViewHolder(
            ItemTwoColumnInputBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            this,
            parent
        )
        InputInfoType.SINGLE_CHECK_BOX.ordinal -> SingleCheckBoxViewHolder(
            ItemSingleCheckboxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        InputInfoType.DELIVERY_TYPE.ordinal -> DeliveryTypeViewHolder(
            ItemDeliveryTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        InputInfoType.PACKAGE_SIZE.ordinal -> PackageSizeViewHolder(
            ItemPackageSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        else -> TapActionViewHolder(
            ItemTapActionInputBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            context
        )
    }

    override fun getItemViewType(position: Int): Int = inputItems[position].type.ordinal

    override fun areContentsTheSame(oldItem: InputInfoModel, newItem: InputInfoModel): Boolean = false

    override fun areItemsTheSame(oldItem: InputInfoModel, newItem: InputInfoModel): Boolean = false

    /**
     * Hàm cập nhật [input] cho item tại vị trí [index]
     *
     * @author Nguyễn Công Chính
     * @since 3/18/2022
     *
     * @version 1
     * @updated 3/18/2022: Tạo function
     */
    fun updateData(index: Int, input: Any) {
        if (index >= 0 && index < items.size) {
            items[index].input = input
            notifyItemChanged(index)
        }
    }

    /**
     * Hàm thu thập dữ liệu từ item thứ [index], trả về kiểu [Any] cho tự đi mà cast, generic rối lắm
     *
     * @author Nguyễn Công Chính
     * @since 3/18/2022
     *
     * @version 1
     * @updated 3/18/2022: Tạo function
     */
    fun collectData(index: Int): Any? = items[index].input
}