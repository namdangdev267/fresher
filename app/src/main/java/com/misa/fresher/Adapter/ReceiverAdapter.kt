package com.misa.fresher.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.InputShip.InputReceiver
import com.misa.fresher.InputShip.InputType
import com.misa.fresher.R
import com.misa.fresher.databinding.*

class ReceiverAdapter(private val inputArr: Array<InputReceiver>,
                      private val context: Context
) : RecyclerView.Adapter<BaseViewHolder<InputReceiver>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<InputReceiver> {
        return when (viewType) {
            InputType.TAP_ACTION.ordinal -> TapActionViewHolder(
                ItemTabActionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            InputType.TAP_INSERT.ordinal -> TapInsertViewHolder(
            ItemTabInsertBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            InputType.SPINNER.ordinal -> SpinnerViewHolder(
                ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                context
            )
            InputType.CHECK_BOX.ordinal -> CheckBoxViewHolder(
                ItemCheckBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            InputType.DELIVERY_TYPE.ordinal -> DeliveryTypeViewHolder(
                ItemDeliveryTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            InputType.PACKAGE_SIZE.ordinal -> PackageSizeViewHolder(
                ItemPackageSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> TwoColViewHolder(
                ItemTwoColumnBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                this
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<InputReceiver>, position: Int) =
        holder.bindData(inputArr[position])

    override fun getItemCount(): Int = inputArr.size

    override fun getItemViewType(position: Int): Int = inputArr[position].type.ordinal

    fun onCreateViewHolder(viewType: Int): BaseViewHolder<InputReceiver> {
        return when (viewType) {
            InputType.TAP_ACTION.ordinal -> TapActionViewHolder(
                ItemTabActionBinding.inflate(LayoutInflater.from(context))
            )
            InputType.TAP_INSERT.ordinal -> TapInsertViewHolder(
                ItemTabInsertBinding.inflate(LayoutInflater.from(context))
            )
            InputType.SPINNER.ordinal -> SpinnerViewHolder(
                ItemSpinnerBinding.inflate(LayoutInflater.from(context)),
                context
            )
            InputType.CHECK_BOX.ordinal -> CheckBoxViewHolder(
                ItemCheckBoxBinding.inflate(LayoutInflater.from(context))
            )
            InputType.DELIVERY_TYPE.ordinal -> DeliveryTypeViewHolder(
                ItemDeliveryTypeBinding.inflate(LayoutInflater.from(context))
            )
            InputType.PACKAGE_SIZE.ordinal -> PackageSizeViewHolder(
                ItemPackageSizeBinding.inflate(LayoutInflater.from(context))
            )
            else -> TwoColViewHolder(
                ItemTwoColumnBinding.inflate(LayoutInflater.from(context)),
                this
            )
        }
    }

    class TapActionViewHolder(
        private val itemBinding: ItemTabActionBinding,
    ) : BaseViewHolder<InputReceiver>(itemBinding.root) {

        override fun bindData(data: InputReceiver) {
            itemBinding.root.setOnClickListener {
                data.onClickListener()
            }

            itemBinding.tvHeader.text = data.title
            itemBinding.tvRequired.text = if (data.isRequired) "*" else ""
            data.icon?.let {
                itemBinding.ivIcon.setImageDrawable(it)
            }
        }
    }

    class TapInsertViewHolder(
        private val itemBinding: ItemTabInsertBinding,
    ) : BaseViewHolder<InputReceiver>(itemBinding.root) {

        override fun bindData(data: InputReceiver) {
            itemBinding.tvHeader.text = data.title
            itemBinding.tvRequired.text = if (data.isRequired) "*" else ""
            data.icon?.let {
                itemBinding.ivIcon.setImageDrawable(it)
            }
        }
    }

    class SpinnerViewHolder(
        private val itemBinding: ItemSpinnerBinding,
        private val context: Context
    ) : BaseViewHolder<InputReceiver>(itemBinding.root) {

        override fun bindData(data: InputReceiver) {
            itemBinding.tvHeader.text = data.title
            itemBinding.tvRequired.text = if (data.isRequired) "*" else ""
            itemBinding.spnInput.adapter =
                ArrayAdapter(context, R.layout.item_text_spinner, data.arr)
        }
    }

    class CheckBoxViewHolder(
        private val itemBinding: ItemCheckBoxBinding,
    ) : BaseViewHolder<InputReceiver>(itemBinding.root) {

        override fun bindData(data: InputReceiver) {
            itemBinding.cb.text = data.title
        }
    }

    class TwoColViewHolder(
        private val itemBinding: ItemTwoColumnBinding,
        private val adapter: ReceiverAdapter
    ) : BaseViewHolder<InputReceiver>(itemBinding.root) {

        override fun bindData(data: InputReceiver) {
            if (data.cols.size == 2) {
                val col0Holder = adapter.onCreateViewHolder(data.cols[0].type.ordinal)
                col0Holder.bindData(data.cols[0])
                itemBinding.flCol0.addView(col0Holder.itemView)

                val col1Holder = adapter.onCreateViewHolder(data.cols[1].type.ordinal)
                col1Holder.bindData(data.cols[1])
                itemBinding.flCol1.addView(col1Holder.itemView)
            }
        }
    }

    class DeliveryTypeViewHolder(
        private val itemBinding: ItemDeliveryTypeBinding,
    ) : BaseViewHolder<InputReceiver>(itemBinding.root) {

        override fun bindData(data: InputReceiver) {
            itemBinding.rbGroup.isChecked = true
        }
    }

    class PackageSizeViewHolder(
        private val itemBinding: ItemPackageSizeBinding,
    ) : BaseViewHolder<InputReceiver>(itemBinding.root) {

        override fun bindData(data: InputReceiver) {
            itemBinding.tvHeader.text = data.title
            itemBinding.tvRequired.text = if (data.isRequired) "*" else ""
        }
    }
}