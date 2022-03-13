package com.misa.fresher.Views.Fragments.Sale

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.Enum.Category
import com.misa.fresher.Models.Enum.Color
import com.misa.fresher.Models.Enum.SortBy
import com.misa.fresher.Models.ItemBillDetail
import com.misa.fresher.Models.ItemSale
import java.text.Collator
import java.util.*


class SaleViewModel: ViewModel() {
    var init=false
    var search: String=""
    var filter:Filter = Filter(null,null,false,SortBy.name_item)

    var listItemSale:MutableList<ItemSale> = mutableListOf()

    private val _listItemShow = MutableLiveData<MutableList<ItemSale>>()
    val listItemShow:LiveData<MutableList<ItemSale>>
        get() = _listItemShow

    private val _itemSelected = MutableLiveData<ItemBillDetail>()
    val itemSelected:LiveData<ItemBillDetail>
        get() = _itemSelected

    private val _listItemSelected = MutableLiveData<MutableList<ItemBillDetail>>()
    val listItemSelected:LiveData<MutableList<ItemBillDetail>>
        get() = _listItemSelected

    data class Filter(var category: Category?, var color: Color?, var available:Boolean, var sortBy: SortBy?)

    fun initData()
    {
        fakeData()
        _listItemShow.postValue(listItemSale)
        _itemSelected.postValue(ItemBillDetail(ItemSale("",0f,"", Color.red,Category.Shirt,10),"","",1))
        _listItemSelected.postValue(mutableListOf())
    }

    private fun fakeData() {
        for(i in 1..20)
        {
            listItemSale.add(ItemSale(i.toString()+"shirt"+i,(i*1.01).toFloat(),i.toString()+"AA",
                Color.yellow,Category.Shirt,10))
        }
        for(i in 1..20)
        {
            listItemSale.add(ItemSale(i.toString()+"trouser"+i,(i*1.01).toFloat(),i.toString()+"AA",
                Color.red,Category.Trouser,20))
        }

    }

    fun updateItemSelected(itemSale: ItemSale)
    {
        var itemSelected: ItemBillDetail = ItemBillDetail(itemSale,itemSale.name,itemSale.id,1)
        _listItemSelected.value?.let {
            for(i in it)
            {
                if(i.itemSale.equals(itemSale))
                {
                    itemSelected = i
                }
            }
        }

        _itemSelected.postValue(itemSelected)
    }

    fun updateItemSelectedAmount(num:Int)
    {
        var itemSelected = _itemSelected.value?.let {
            ItemBillDetail(
                it?.itemSale,"","",it.amount+num)
        }

        _itemSelected.postValue(itemSelected!!)
    }

    fun updateListItemSelected()
    {
        _itemSelected.value?.let { _listItemSelected.value?.apply {  add(it)} }
        _listItemSelected.postValue(_listItemSelected.value)
    }

    fun clearListItemSelected()
    {
        _listItemSelected.value?.clear()
        _listItemSelected.postValue(_listItemSelected.value)
    }

    fun updateListItemShow(searchString: String)
    {
        search = searchString
        _listItemShow.postValue(listItemSale)
        var showList = mutableListOf<ItemSale>()
        for(i in listItemSale)
        {
            if(i.name.contains(searchString)){
                showList.add(i)
            }
        }
        _listItemShow.postValue(showList)
    }

    fun filterListItemShow()
    {
        Log.e("filter",search)
        var showList = mutableListOf<ItemSale>()
        for(i in listItemSale)
        {
            if(i.name.contains(search)){
                showList.add(i)
            }
        }
        var res = mutableListOf<ItemSale>()
        for(i in showList)
        {
            Log.e(this.javaClass.simpleName,"listItemShow--"+filter.color?.name+"--"+i.color.name)
            if(filter.category!=null && filter.category != i.category)
            {

            }
            else if(filter.color!=null && filter.color != i.color)
            {

            }
            else if(filter.available==true && i.quantity<=0)
            {

            }
            else
            {
                res.add(i)
            }
        }

        res = when (filter.sortBy) {
            SortBy.name_item -> res.sortedWith{ p1, p2 ->
                Collator.getInstance().compare(p1.name, p2.name)
            } as MutableList<ItemSale>
            else -> res.sortedByDescending{it.quantity
            } as MutableList<ItemSale>
        }


        Log.e(this.javaClass.simpleName,showList.size.toString()+"--"+listItemSale.size)
        _listItemShow.postValue(res)

    }


    fun getTotalPrice():Float
    {
        var totalPrice=0f
        _listItemSelected.value?.let {
            for(i in _listItemSelected.value!!)
            {
                totalPrice+= i.itemSale.price* i.amount
            }
        }

        return totalPrice

    }

    fun getColorOf(itemSale: ItemSale): List<Color> {
        var mutableList:MutableList<Color> = mutableListOf()
        for(i in listItemSale)
        {
            if(i.name.equals(itemSale.name))
            {
                mutableList.add(itemSale.color)
            }
        }
        return mutableList
    }
    



}