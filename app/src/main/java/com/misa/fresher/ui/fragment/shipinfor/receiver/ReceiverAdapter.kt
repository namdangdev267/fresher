package com.misa.fresher.ui.fragment.shipinfor.receiver

import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.data.models.InforShip
import com.misa.fresher.data.models.ItemShipInfor
import com.misa.fresher.databinding.ItemShipCalculatorBinding
import com.misa.fresher.databinding.ItemShipCheckBinding
import com.misa.fresher.databinding.ItemShipMulticontentBinding
import com.misa.fresher.databinding.ItemShipTouchBinding

class ReceiverAdapter(
    private val adapterData: MutableList<ItemShipInfor>,
    val changeEditText: (inforShip: InforShip) -> Unit
) :
    RecyclerView.Adapter<ReceiverAdapter.ReceiverAdapterViewHolder>() {

    companion object {
        private const val ITEM_TOUCH = 0
        private const val ITEM_CALCULATOR = 1
        private const val ITEM_MULTICONTENT = 2
        private const val ITEM_CHECK = 3
    }

    abstract class ReceiverAdapterViewHolder(
        itemView: View,
        val changeEditText: (inforShip: InforShip) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        open fun bindingData(itemShip: ItemShipInfor) {
        }

        fun bindingItemTouch(item: ItemShipInfor.ItemTouch, binding: ItemShipTouchBinding) {

            val tvTitle = binding.tvTouchTitle
            val editText = binding.edtTouchHintContent

            binding.tvTouchRequire.text = item.require
            item.imageResourcce?.let { binding.imgTouch.setImageResource(it) }
            tvTitle.text = item.title
            editText.hint = item.hintContent

            if (item.title == "Tel") {
                editText.inputType = InputType.TYPE_CLASS_NUMBER
            }

            val inforShip = InforShip(null, null, null, null)

            editText.doAfterTextChanged {
                if (item.title == "Receiver") {
                    inforShip.receiver = it.toString()
                    changeEditText(inforShip)
                } else if (item.title == "Tel") {
                    inforShip.tel = it.toString()
                    changeEditText(inforShip)
                } else if (item.title == "Address") {
                    inforShip.address = it.toString()
                    changeEditText(inforShip)
                }
            }
        }

        fun bindingItemCalculator(
            item: ItemShipInfor.ItemCalculator,
            binding: ItemShipCalculatorBinding
        ) {
            binding.run {
                tvCalculatorTitle.text = item.title
                tvCalculatorContent.text = item.content
            }
        }

        fun bindingItemMulticontent(
            item: ItemShipInfor.ItemMultiContent,
            binding: ItemShipMulticontentBinding
        ) {
            binding.run {
                tvMulticontentTitle1.text = item.title1
                tvMulticontentTitle2.text = item.title2
                tvMulticontentTitle1.text = item.content1
                tvMulticontentTitle2.text = item.content2
                imgMulticontent.setImageResource(item.imageResource)
            }
        }

        fun bindingItemcheck(
            item: ItemShipInfor.ItemCheck,
            binding: ItemShipCheckBinding
        ) {
            binding.run {
                tvCheckTitle.text = item.title
            }
        }
    }

    class ItemTouchViewholder(
        val binding: ItemShipTouchBinding,
        changeEditText: (inforShip: InforShip) -> Unit
    ) : ReceiverAdapter.ReceiverAdapterViewHolder(binding.root, changeEditText) {
        override fun bindingData(itemShip: ItemShipInfor) {
            bindingItemTouch(itemShip as ItemShipInfor.ItemTouch, binding)
        }
    }

    class ItemMulticontentViewholder(
        val binding: ItemShipMulticontentBinding,
        changeEditText: (inforShip: InforShip) -> Unit
    ) : ReceiverAdapter.ReceiverAdapterViewHolder(binding.root, changeEditText) {
        override fun bindingData(itemShip: ItemShipInfor) {
            bindingItemMulticontent(itemShip as ItemShipInfor.ItemMultiContent, binding)
        }
    }

    class ItemCalculatorViewholder(
        val binding: ItemShipCalculatorBinding,
        changeEditText: (inforShip: InforShip) -> Unit
    ) : ReceiverAdapter.ReceiverAdapterViewHolder(binding.root, changeEditText) {
        override fun bindingData(itemShip: ItemShipInfor) {
            bindingItemCalculator(itemShip as ItemShipInfor.ItemCalculator, binding)
        }
    }

    class ItemCheckViewholder(
        val binding: ItemShipCheckBinding,
        changeEditText: (inforShip: InforShip) -> Unit
    ) : ReceiverAdapter.ReceiverAdapterViewHolder(binding.root, changeEditText) {
        override fun bindingData(itemShip: ItemShipInfor) {
            bindingItemcheck(itemShip as ItemShipInfor.ItemCheck, binding)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReceiverAdapter.ReceiverAdapterViewHolder {
        return when (viewType) {
            ITEM_TOUCH -> ItemTouchViewholder(
                ItemShipTouchBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                ), changeEditText
            )
            ITEM_CALCULATOR -> ItemCalculatorViewholder(
                ItemShipCalculatorBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), changeEditText
            )
            ITEM_MULTICONTENT -> ItemMulticontentViewholder(
                ItemShipMulticontentBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), changeEditText
            )
            ITEM_CHECK -> ItemCheckViewholder(
                ItemShipCheckBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                ), changeEditText
            )
            else -> ItemTouchViewholder(
                ItemShipTouchBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), changeEditText
            )
        }
    }

    override fun onBindViewHolder(
        holder: ReceiverAdapter.ReceiverAdapterViewHolder,
        position: Int
    ) =
        holder.bindingData(adapterData[position])

    override fun getItemCount() = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is ItemShipInfor.ItemTouch -> ITEM_TOUCH
            is ItemShipInfor.ItemCalculator -> ITEM_CALCULATOR
            is ItemShipInfor.ItemMultiContent -> ITEM_MULTICONTENT
            is ItemShipInfor.ItemCheck -> ITEM_CHECK
            else -> 0
        }
    }
}