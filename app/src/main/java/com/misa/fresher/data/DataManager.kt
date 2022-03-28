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
 * @version 3
 * @updated 3/21/2022: Tạo class
 * @updated 3/25/2022: Thêm các hàm để trao đổi dữ liệu với database
 * @updated 3/28/2022: Loại bỏ các hàm để thêm dữ liệu giả do đã thêm ngay lúc khởi tạo csdl
 */
interface DataManager {

    fun signIn(email: String, password: String): ResponseObject<SignInResponse>
    fun signUp(email: String, password: String): ResponseObject<SignUpResponse>

    fun createBill(bill: Bill): ResponseObject<Boolean>
    fun getAllBill(): ResponseObject<List<Bill>>
    fun getMaxIdBill(): ResponseObject<Long>

    fun getAllCategory(): ResponseObject<List<Category>>

    fun getAllCustomer(): ResponseObject<List<Customer>>

    fun getAllProduct(): ResponseObject<List<Product>>
    
    companion object {
        private var dataManager: DataManager? = null

        fun getInstance(context: Context): DataManager =
            dataManager ?: DataManagerImpl(context).also { dataManager = it }
    }
}