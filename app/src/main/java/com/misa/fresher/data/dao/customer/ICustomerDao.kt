package com.misa.fresher.data.dao.customer

import com.misa.fresher.data.model.Customer

interface ICustomerDao {
    fun addCustomer(customer: Customer) : Long
    fun getCustomer() : MutableList<Customer>
    suspend fun getCustomerById(id : Int):Customer
}