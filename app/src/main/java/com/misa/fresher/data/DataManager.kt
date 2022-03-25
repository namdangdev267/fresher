package com.misa.fresher.data

import android.content.Context
import com.misa.fresher.data.entity.*
import com.misa.fresher.data.source.remote.response.SignInResponse
import com.misa.fresher.data.source.remote.response.SignUpResponse
import com.misa.fresher.util.ResponseObject

/**
 * Interface chung để trao đổi dữ liệu với api, local database,...
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 2
 * @updated 3/21/2022: Tạo class
 * @updated 3/25/2022: Thêm các hàm để trao đổi dữ liệu với database
 */
interface DataManager {

    fun signIn(email: String, password: String): ResponseObject<SignInResponse>
    fun signUp(email: String, password: String): ResponseObject<SignUpResponse>

    fun createBill(bill: Bill): ResponseObject<Boolean>
    fun getAllBill(): ResponseObject<List<Bill>>
    fun getMaxIdBill(): ResponseObject<Long>

    fun createCategory(list: List<Category>): ResponseObject<Boolean>
    fun getAllCategory(): ResponseObject<List<Category>>

    fun createCustomer(list: List<Customer>): ResponseObject<Boolean>
    fun getAllCustomer(): ResponseObject<List<Customer>>

    fun createProduct(list: List<Product>): ResponseObject<Boolean>
    fun getAllProduct(): ResponseObject<List<Product>>

    fun createProductColor(list: List<ProductColor>): ResponseObject<Boolean>

    fun createProductSize(list: List<ProductSize>): ResponseObject<Boolean>

    fun createProductUnit(list: List<ProductUnit>): ResponseObject<Boolean>
    
    companion object {
        private var dataManager: DataManager? = null

        fun getInstance(context: Context): DataManager =
            dataManager ?: DataManagerImpl(context).also { dataManager = it }
    }
}