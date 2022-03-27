package com.misa.fresher.data

import com.misa.fresher.data.repository.UserRepository
import com.misa.fresher.data.repository.UserRepositoryImpl
import com.misa.fresher.data.source.remote.response.SignInResponse
import com.misa.fresher.data.source.remote.response.SignUpResponse
import com.misa.fresher.util.ResponseObject

class DataManagerImpl: DataManager {

    private val userRepo: UserRepository = UserRepositoryImpl()

    override fun signIn(email: String, password: String): ResponseObject<SignInResponse> {
        return ResponseObject { userRepo.signIn(email, password, it) }
    }

    override fun signUp(email: String, password: String): ResponseObject<SignUpResponse> {
        return ResponseObject { userRepo.signUp(email, password, it) }
    }
}