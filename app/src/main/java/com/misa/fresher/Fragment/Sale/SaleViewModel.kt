package com.misa.fresher.Fragment.Sale

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.Product
import com.misa.fresher.R
import java.text.Collator

class SaleViewModel : ViewModel() {
    var search: String = ""

    private var listItemProduct: MutableList<Product> = mutableListOf()

    private val _listItemShow = MutableLiveData<MutableList<Product>>()
    val listItemShow: LiveData<MutableList<Product>>
        get() = _listItemShow

    fun initData() {
        fakeData()
        _listItemShow.postValue(listItemProduct)
    }

    private fun fakeData() {

        for (i in 1..20) {
            listItemProduct.add(
                Product(
                    R.drawable.ic_launcher_foreground,
                    i.toString() + "pants" + i, i.toString() + "PA",
                    100 * i, i,
                )
            )
        }

        for (i in 1..20) {
            listItemProduct.add(
                Product(
                    R.drawable.ic_launcher_foreground,
                    i.toString() + "t-shirt" + i, i.toString() + "TS",
                    100 * i, i,
                )
            )
        }

        listItemProduct = listItemProduct.sortedWith { p1, p2 ->
            Collator.getInstance().compare(p1.nameProduct, p2.nameProduct)
        } as MutableList<Product>


    }

    fun updateListItemShow(searchString: String) {
        search = searchString
        _listItemShow.postValue(listItemProduct)
        val showList = listItemProduct.filter { it.nameProduct.contains(searchString, true) }
        _listItemShow.postValue(showList as MutableList<Product>?)
    }
}