package com.misa.fresher.data

import com.misa.fresher.data.source.remote.response.SignInResponse
import com.misa.fresher.data.source.remote.response.SignUpResponse
import com.misa.fresher.util.ResponseObject

/**
 * Interface chung để trao đổi dữ liệu với api, local database,...
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 1
 * @updated 3/21/2022: Tạo class
 */
interface DataManager {

    fun signIn(email: String, password: String): ResponseObject<SignInResponse>
    fun signUp(email: String, password: String): ResponseObject<SignUpResponse>
    
    companion object {
        private val dataManager by lazy { DataManagerImpl() }

        fun getInstance(): DataManager = dataManager
    }
}