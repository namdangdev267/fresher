package com.misa.fresher.data.dao.infoship

import com.misa.fresher.models.InfoShip
import com.misa.fresher.models.ItemProduct

interface IInfoShipDao {
    suspend fun getAllInfoShips():MutableList<InfoShip>
    suspend fun addInfoShip(itemProduct: InfoShip) : Long
    suspend fun getInfoShipWithID(id:Int) : InfoShip?
}