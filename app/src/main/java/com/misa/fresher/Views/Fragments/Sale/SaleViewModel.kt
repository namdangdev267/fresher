package com.misa.fresher.Views.Fragments.Sale

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.ItemSale


class SaleViewModel: ViewModel() {
    var listItemSale:MutableList<ItemSale> = mutableListOf()

    private val _listItemShow = MutableLiveData<MutableList<ItemSale>>()
    val listItemShow:LiveData<MutableList<ItemSale>>
        get() = _listItemShow

    init {
        for(i in 1..20)
        {
            listItemSale.add(ItemSale(i.toString()+"abcde"+i,(i*1.01).toFloat(),i.toString()+"AA"))
        }
        _listItemShow.postValue(listItemSale)
    }

//    private fun filterList(): MutableList<ItemSale> {
//        var showList = mutableListOf<ItemSale>()
//        for(i in _listItemShow.value!!)
//        {
//            if(i.name.contains("a"))
//            {
//                showList.add(i)
//            }
//        }
//
//        return _listItemShow.value!!
//    }

    fun updateListItemShow(searchString: String)
    {
        _listItemShow.postValue(listItemSale)
        var showList = mutableListOf<ItemSale>()
        for(i in listItemSale)
        {
            if(i.name.contains(searchString))
            {
                showList.add(i)
            }
        }
        Log.e(this.javaClass.simpleName,showList.size.toString())
        _listItemShow.postValue(showList)
    }




}