package com.misa.fresher.data.repositories

import com.misa.fresher.data.dao.infoship.InfoShipDao
import com.misa.fresher.data.dao.itembill.ItemBillDao
import com.misa.fresher.models.InfoShip
import com.misa.fresher.models.ItemBill

class InfoShipRepository (private val infoShipDao: InfoShipDao) {
    suspend fun getInfoShips():MutableList<InfoShip>
    {
        return infoShipDao.getAllInfoShips()
    }

    suspend fun addInfoShip(infoShip: InfoShip) {
        infoShipDao.addInfoShip(infoShip)
    }
}