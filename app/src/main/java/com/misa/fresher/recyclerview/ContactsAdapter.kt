package com.misa.fresher.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R



class ContactsAdapter(private val mContacts: List<Contact>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mContacts.size
    }

    override fun getItemViewType(position: Int): Int {
        if(position % 2 == 0){
            return 100
        } else {
            return 101
        }
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        if(viewType == 100){
            val contactView = inflater.inflate(R.layout.item_contact, parent, false)
            return ViewHolder(contactView);
        }else{
            val contactView2 = inflater.inflate(R.layout.item_contact2, parent, false)
            return ViewHolder2(contactView2);
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position % 2 == 0){
            val viewHolderTemp = holder as ViewHolder
            viewHolderTemp.messageButton.text = "messageButton holder 1"
            viewHolderTemp.nameTextView.text = "nameTextView holder 1"
        }else{
            val viewHolderTemp = holder as ViewHolder2
            viewHolderTemp.tvContent.text = "tvContent holder 2"
        }
    }


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val nameTextView = itemView.findViewById<TextView>(R.id.contact_name)
        val messageButton = itemView.findViewById<Button>(R.id.message_button)
    }

    inner class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val tvContent = itemView.findViewById<TextView>(R.id.tvContent)

    }


}