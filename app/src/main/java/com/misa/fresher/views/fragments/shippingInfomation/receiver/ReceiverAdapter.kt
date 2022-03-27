package com.misa.fresher.views.fragments.shippingInfomation.receiver

import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.InfoShip
import com.misa.fresher.models.ItemRecyclerView
import com.misa.fresher.databinding.ItemShipCalculatorBinding
import com.misa.fresher.databinding.ItemShipCheckBinding
import com.misa.fresher.databinding.ItemShipMulticontentBinding
import com.misa.fresher.databinding.ItemShipTouchBinding

class ReceiverAdapter(private val adapterData: MutableList<ItemRecyclerView>,val changeEditText: (infoShip:InfoShip) -> Unit) :
    RecyclerView.Adapter<ReceiverAdapter.ReceiverAdapterViewHolder>() {

    companion object {
        private const val ITEM_TOUCH = 0
        private const val ITEM_CALCULATOR = 1
        private const val ITEM_MULTICONTENT = 2
        private const val ITEM_CHECK = 3
    }

    abstract class ReceiverAdapterViewHolder(itemView: View,val changeEditText: (infoShip:InfoShip) -> Unit ) : RecyclerView.ViewHolder(itemView) {
        open fun bindData(item: ItemRecyclerView)
        {

        }

        fun bindItemTouch(item: ItemRecyclerView.ItemTouch, binding: ItemShipTouchBinding) {

            val tvTitle = binding.textviewTouchTitle
            val editText = binding.edittextTouchHintContent
            binding.textviewTouchRequire.text = item.require
            item.imageResourcce?.let {
                binding.imageviewTouch.setImageResource(it)
            }

            tvTitle.text = item.title
            editText.hint = item.hintContent

            if(item.title == "Tel")
            {
                editText.inputType = InputType.TYPE_CLASS_NUMBER
            }

            var inforShip = InfoShip(null,null,null,null)

            editText.doAfterTextChanged {
                if(item.title == "Receiver")
                {
                    inforShip.receiver = it.toString()
                    changeEditText(inforShip)
                }
                else if (item.title == "Tel")
                {
                    inforShip.tel = it.toString()
                    changeEditText(inforShip)
                }
                else if(item.title == "Address")
                {
                    inforShip.address = it.toString()
                    changeEditText(inforShip)
                }
            }
        }

        fun bindItemCalculator(item: ItemRecyclerView.ItemCalculator,binding: ItemShipCalculatorBinding) {
            binding.textviewCalculatorTitle.text = item.title
            binding.textviewCalculatorContent.text = item.content
        }

        fun bindItemMulticontent(item: ItemRecyclerView.ItemMultiContent, binding: ItemShipMulticontentBinding) {
            binding.textviewMulticontentTitle1.text = item.title1
            binding.textviewMulticontentTitle1.text = item.title2
            binding.textviewMulticontentContent1.text = item.content1
            binding.textviewMulticontentContent2.text = item.content2
            binding.imageviewMulticontent.setImageResource(item.imageResource)
        }

        fun bindItemcheck(item: ItemRecyclerView.ItemCheck, binding: ItemShipCheckBinding) {
            binding.textviewCheckTitle.text = item.title
        }

    }

    class ItemTouchViewHolder(
        val binding: ItemShipTouchBinding,
        changeEditText: (infoShip: InfoShip) -> Unit
    ):
        ReceiverAdapterViewHolder(binding.root,changeEditText)
    {
        override fun bindData(item: ItemRecyclerView) {
            bindItemTouch(item as ItemRecyclerView.ItemTouch,binding)
        }
    }

    class ItemCalculatorViewHolder(val binding: ItemShipCalculatorBinding,changeEditText: (infoShip: InfoShip) -> Unit):
        ReceiverAdapterViewHolder(binding.root,changeEditText)
    {
        override fun bindData(item: ItemRecyclerView) {
            bindItemCalculator(item as ItemRecyclerView.ItemCalculator,binding)
        }
    }

    class ItemMulticontentViewHolder(val binding:ItemShipMulticontentBinding,changeEditText: (infoShip: InfoShip) -> Unit):ReceiverAdapterViewHolder(binding.root,changeEditText)
    {
        override fun bindData(item: ItemRecyclerView) {
            bindItemMulticontent(item as ItemRecyclerView.ItemMultiContent,binding)
        }
    }

    class ItemCheckViewHolder(val binding:ItemShipCheckBinding,changeEditText: (infoShip: InfoShip) -> Unit):ReceiverAdapterViewHolder(binding.root,changeEditText)
    {
        override fun bindData(item: ItemRecyclerView) {
            bindItemcheck(item as ItemRecyclerView.ItemCheck,binding)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiverAdapterViewHolder {
        return  when (viewType) {
            ITEM_TOUCH -> ItemTouchViewHolder(ItemShipTouchBinding.inflate(LayoutInflater.from(parent.context), parent,false),changeEditText)
            ITEM_CALCULATOR -> ItemCalculatorViewHolder(ItemShipCalculatorBinding.inflate(LayoutInflater.from(parent.context),parent,false),changeEditText)
            ITEM_MULTICONTENT -> ItemMulticontentViewHolder(ItemShipMulticontentBinding.inflate(LayoutInflater.from(parent.context),parent,false),changeEditText)
            ITEM_CHECK -> ItemCheckViewHolder(ItemShipCheckBinding.inflate(LayoutInflater.from(parent.context),parent,false),changeEditText)
            else -> ItemTouchViewHolder(
                ItemShipTouchBinding.inflate(LayoutInflater.from(parent.context), parent,false),
                changeEditText
            )
        }
    }

    override fun onBindViewHolder(holder: ReceiverAdapterViewHolder, position: Int) {
        holder.bindData(adapterData[position])
    }

    override fun getItemCount() = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is ItemRecyclerView.ItemTouch -> ITEM_TOUCH
            is ItemRecyclerView.ItemCalculator -> ITEM_CALCULATOR
            is ItemRecyclerView.ItemMultiContent -> ITEM_MULTICONTENT
            is ItemRecyclerView.ItemCheck -> ITEM_CHECK
            else -> 0
        }
    }
}