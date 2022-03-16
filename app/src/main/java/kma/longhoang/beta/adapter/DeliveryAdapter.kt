package kma.longhoang.beta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.R
import kma.longhoang.beta.model.DeliveryModel

class DeliveryAdapter(private val listItem: MutableList<DeliveryModel>) :
    RecyclerView.Adapter<DeliveryAdapter.DeliveryHolder>() {
    inner class DeliveryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun enterEditText(item: DeliveryModel.EnterEditText) {
            itemView.findViewById<TextView>(R.id.text_title).text = item.title
            if (item.star != null) {
                itemView.findViewById<TextView>(R.id.text_star).text = item.star
            } else {
                itemView.findViewById<TextView>(R.id.text_star).isVisible = false
            }
            itemView.findViewById<TextView>(R.id.edit_content).hint = item.content
            if (item.moreAction != null) {
                itemView.findViewById<ImageButton>(R.id.button_more_action)
                    .setImageResource(item.moreAction)
            } else {
                itemView.findViewById<ImageButton>(R.id.button_more_action).isVisible = false
            }
        }

        private fun enter2Column(item: DeliveryModel.Enter2Column) {
            itemView.findViewById<TextView>(R.id.text_title_1).text = item.title1
            itemView.findViewById<TextView>(R.id.text_title_2).text = item.title2
            itemView.findViewById<TextView>(R.id.text_content_1).text = item.content1
            itemView.findViewById<TextView>(R.id.text_content_2).text = item.content2
            if (item.moreAction != null) {
                itemView.findViewById<ImageButton>(R.id.button_more_action)
                    .setImageResource(item.moreAction)
            } else {
                itemView.findViewById<ImageButton>(R.id.button_more_action).isVisible = false
            }
        }

        private fun enterTextView(item: DeliveryModel.EnterTextView) {
            itemView.findViewById<TextView>(R.id.text_title).text = item.title
            if (item.star != null) {
                itemView.findViewById<TextView>(R.id.text_star).text = item.star
            } else {
                itemView.findViewById<TextView>(R.id.text_star).isVisible = false
            }
            itemView.findViewById<TextView>(R.id.text_content).text = item.content
            if (item.moreAction != null) {
                itemView.findViewById<ImageButton>(R.id.button_more_action)
                    .setImageResource(item.moreAction)
            } else {
                itemView.findViewById<ImageButton>(R.id.button_more_action).isVisible = false
            }
        }

        private fun checkbox(item: DeliveryModel.Checkbox) {
            itemView.findViewById<CheckBox>(R.id.checkbox_delivery).text = item.title
        }

        private fun packageSize(item: DeliveryModel.PackageSize) {
            itemView.findViewById<TextView>(R.id.text_title).text = item.title
            itemView.findViewById<EditText>(R.id.edit_width).setText(item.width.toString())
            itemView.findViewById<EditText>(R.id.edit_depth).setText(item.depth.toString())
            itemView.findViewById<EditText>(R.id.edit_height).setText(item.height.toString())
        }

        private fun radioButtonView(item: DeliveryModel.RadioModel) {
            if (item.title != null) {
                itemView.findViewById<TextView>(R.id.text_title).text = item.title
            } else {
                itemView.findViewById<TextView>(R.id.text_title).isVisible = false
            }
            itemView.findViewById<RadioButton>(R.id.radio_delivery_1).text = item.radText1
            itemView.findViewById<RadioButton>(R.id.radio_delivery_2).text = item.radText2
        }

        fun bind(dataModel: DeliveryModel) {
            when (dataModel) {
                is DeliveryModel.EnterEditText -> enterEditText(dataModel)
                is DeliveryModel.Enter2Column -> enter2Column(dataModel)
                is DeliveryModel.EnterTextView -> enterTextView(dataModel)
                is DeliveryModel.Checkbox -> checkbox(dataModel)
                is DeliveryModel.PackageSize -> packageSize(dataModel)
                is DeliveryModel.RadioModel -> radioButtonView(dataModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryHolder {
        val layout = when (viewType) {
            0 -> R.layout.delivery_layout_edittext
            1 -> R.layout.delivery_layout_2_column
            2 -> R.layout.delivery_layout_text
            3 -> R.layout.delivery_layout_checkbox
            4 -> R.layout.delivery_layout_package_size
            else -> R.layout.delivery_layout_radio_button
        }
        return DeliveryHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: DeliveryHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (listItem[position]) {
            is DeliveryModel.EnterEditText -> 0
            is DeliveryModel.Enter2Column -> 1
            is DeliveryModel.EnterTextView -> 2
            is DeliveryModel.Checkbox -> 3
            is DeliveryModel.PackageSize -> 4
            is DeliveryModel.RadioModel -> 5
        }
    }
}