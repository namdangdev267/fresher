package com.misa.fresher.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.TextView
import com.misa.fresher.models.Country
import org.w3c.dom.Text
import android.R
import android.annotation.SuppressLint

import android.view.LayoutInflater

import androidx.annotation.NonNull
import com.misa.fresher.databinding.ItemCountryBinding


class SimpleImageArrayAdapter(context: Context, resource: Int, objects: MutableList<Country>) :
    ArrayAdapter<Country>(context, resource, objects) {
        private val listCountry:MutableList<Country> = objects

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        binding.countryFlag.setBackgroundResource(listCountry[position].flag)
        binding.countryName.text = listCountry[position].name

        return binding.root
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        binding.countryFlag.setBackgroundResource(listCountry[position].flag)
        binding.countryName.text = listCountry[position].name

        return binding.root
    }


}

