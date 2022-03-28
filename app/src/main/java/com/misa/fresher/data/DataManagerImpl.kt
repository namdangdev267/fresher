package com.misa.fresher.data

import android.content.Context
import com.misa.fresher.data.entity.*
import com.misa.fresher.data.repository.*
import com.misa.fresher.data.source.remote.response.SignInResponse
import com.misa.fresher.data.source.remote.response.SignUpResponse
import com.misa.fresher.util.ResponseObject

/**
 * @version 3
 * @updated 3/21/2022: Override lần đầu
 * @updated 3/25/2022: Cập nhật hàm mới theo [DataManager]
 * @updated 3/28/2022: Cập nhật hàm mới theo [DataManager]
 */
class DataManagerImpl(
    context: Context
): DataManager {

    private val userRepo: UserRepository = UserRepositoryImpl(context)
    private val billRepo: BillRepository = BillRepositoryImpl(context)
    private val categoryRepo: CategoryRepository = CategoryRepositoryImpl(context)
    private val customerRepo: CustomerRepository = CustomerRepositoryImpl(context)
    private val productRepo: ProductRepository = ProductRepositoryImpl(context)

    override fun signIn(email: String, password: String): ResponseObject<SignInResponse> {
        return ResponseObject { userRepo.signIn(email, password, it) }
    }

    override fun signUp(email: String, password: String): ResponseObject<SignUpResponse> {
        return ResponseObject { userRepo.signUp(email, password, it) }
    }

    override fun createBill(bill: Bill): ResponseObject<Boolean> {
        return ResponseObject { billRepo.create(bill, it) }
    }

    override fun getAllBill(): ResponseObject<List<Bill>> {
        return ResponseObject { billRepo.getAll(it) }
    }

    override fun getMaxIdBill(): ResponseObject<Long> {
        return ResponseObject { billRepo.getMaxId(it) }
    }

    override fun getAllCategory(): ResponseObject<List<Category>> {
        return ResponseObject { categoryRepo.getAll(it) }
    }

    override fun getAllCustomer(): ResponseObject<List<Customer>> {
        return ResponseObject { customerRepo.getAll(it) }
    }

    override fun getAllProduct(): ResponseObject<List<Product>> {
        return ResponseObject { productRepo.getAll(it) }
    }
}