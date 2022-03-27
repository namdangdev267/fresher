package com.misa.fresher.views.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.misa.fresher.data.repositories.BillRepository
import com.misa.fresher.data.repositories.InfoShipRepository
import com.misa.fresher.models.enums.BillStatus
import com.misa.fresher.models.enums.Category
import com.misa.fresher.models.enums.Color
import com.misa.fresher.models.InfoShip
import com.misa.fresher.models.ItemBill
import com.misa.fresher.models.ItemBillDetail
import com.misa.fresher.models.ItemProduct
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.util.*

class SharedViewModel(
    private val billRepository: BillRepository,
    private val infoShipRepository: InfoShipRepository
) : ViewModel() {
    var queueBill: LinkedList<ItemBill> = LinkedList()
    var queueInfoShip: LinkedList<InfoShip> = LinkedList()

    private val _listBill = MutableLiveData<MutableList<ItemBill>>()
    val listBill: LiveData<MutableList<ItemBill>>
        get() = _listBill

    private val _billHandling = MutableLiveData<ItemBill>()
    val billHandling: LiveData<ItemBill>
        get() = _billHandling

    private val _infoShip = MutableLiveData<InfoShip>()
    val infoShip: LiveData<InfoShip>
        get() = _infoShip

    private val _itemBillDetail = MutableLiveData<ItemBillDetail>()
    val itemBillDetail: LiveData<ItemBillDetail>
        get() = _itemBillDetail

    private val _listItemBillDetail = MutableLiveData<MutableList<ItemBillDetail>>()
    val listItemBillDetail: LiveData<MutableList<ItemBillDetail>>
        get() = _listItemBillDetail


    /**
     * get data
     */

    init {
        _billHandling.postValue(
            ItemBill(
                mutableListOf(),
                InfoShip(),
                BillStatus.HANDLING.name,
                Calendar.getInstance().time.toString()
            )
        )

        _itemBillDetail.postValue(
            ItemBillDetail(
                ItemProduct(
                    "",
                    0f,
                    "",
                    Color.RED.toString(),
                    Category.SHIRT.toString(),
                    10,
                    "11/4/2011"
                ),
                "0",
                1
            )
        )
        _listItemBillDetail.postValue(mutableListOf())

        _listBill.postValue(mutableListOf())

        _infoShip.postValue(InfoShip())


        viewModelScope.launch {
            _listBill.postValue(billRepository.getBills())
            var i=0
        }
    }


    /**
     * item selected
     */

    fun updateItemSelected(itemProduct: ItemProduct) {

        Log.e("billl", _billHandling.value?.id.toString())
        val itemSelected = _listItemBillDetail.value?.find {
            it.itemProduct == itemProduct
        } ?: _billHandling.value?.id?.let { ItemBillDetail(itemProduct, it, 1) }

        _itemBillDetail.postValue(itemSelected!!)
    }

    fun updateItemSelectedQuantity(num: Int) {
        _itemBillDetail.value?.updateQuantity(num)
        _itemBillDetail.postValue(_itemBillDetail.value)
    }


    /**
     * List item selected
     */

    fun updateListItemSelected() {
        val listSelected = _listItemBillDetail.value
        var check = false

        listSelected?.let {
            for (item in listSelected) {
                if (item.itemProduct == _itemBillDetail.value?.itemProduct) {
                    item.quantity = _itemBillDetail.value!!.quantity
                    check = true
                }
            }
        }
        if (!check) {
            _itemBillDetail.value?.let { listSelected?.add(it) }
        }
        _listItemBillDetail.postValue(listSelected!!)
    }

    fun clearListItemSelected() {
        _listItemBillDetail.postValue(mutableListOf())

        CoroutineScope(IO).launch {
            _billHandling.postValue(
                ItemBill(
                    mutableListOf(),
                    null,
                    BillStatus.HANDLING.name,
                    Calendar.getInstance().time.toString()
                )
            )
            _infoShip.postValue(InfoShip(null, null, null, null))
        }
    }

    fun getTotalPrice(): Float {
        var totalPrice = 0f
        _listItemBillDetail.value?.let {
            for (i in it) {
                totalPrice += i.itemProduct?.price!! * i.quantity
            }
        }
        return totalPrice
    }

    fun updateQuantityOfItemBillDetail(itemBillDetail: ItemBillDetail) {
        _listItemBillDetail.value?.let {
            for (item in it) {
                if (item.itemProduct == itemBillDetail.itemProduct) {
                    item.quantity = itemBillDetail.quantity
                    _listItemBillDetail.postValue(it)
                    break
                }
            }
        }
    }

    /**
     * shipping information
     */

    fun updateInforShip(infoShip: InfoShip) {
        _infoShip.postValue(infoShip)
    }


    /**
     * bill
     */

    fun addBillToListBill() {
        _billHandling.value?.listItemBillDetail = _listItemBillDetail.value!!
        _billHandling.value?.infoShip = _infoShip.value!!
        _billHandling.postValue(_billHandling.value)
        _billHandling.value?.let {
            _listBill.value?.add(it)
            it.setBillPrice()
        }
        _billHandling.value?.let {
            queueBill.add(it)
        }

        _infoShip.value?.let {
            queueInfoShip.add(it)
        }


        viewModelScope.launch {
            while (queueBill.size != 0) {
                billRepository.addBill(queueBill.peek())
                queueBill.pop()
            }
        }

        viewModelScope.launch {
            while (queueInfoShip.size != 0) {
                infoShipRepository.addInfoShip(queueInfoShip.peek())
                queueInfoShip.pop()
            }
        }

        _listBill.postValue(_listBill.value)
        clearListItemSelected()

    }

    fun getTotalPriceListBill(): Float {
        var res = 0f
        _listBill.value?.let {
            for (i in it) {
                res += i.billPrice
            }
        }
        return res
    }


}