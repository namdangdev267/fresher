package com.misa.fresher.ui.login

import android.content.Context
import android.util.Log
import com.misa.fresher.common.FakeData
import com.misa.fresher.core.BasePresenter
import com.misa.fresher.data.entity.*
import com.misa.fresher.data.source.remote.response.SignUpResponse
import com.misa.fresher.util.ResponseObject

/**
 * Presenter cho màn hình đăng kí/đăng nhập
 *
 * @author Nguyễn Công Chính
 * @since 3/23/2022
 *
 * @version 1
 * @updated 3/23/2022: Tạo class
 */
class LoginPresenter(
    view: LoginContract.View,
    context: Context
) : BasePresenter<LoginContract.View>(view, context), LoginContract.Presenter {

    private var currentState = STATE_SIGN_IN

    /**
     * @version 1
     * @updated 3/23/2022: Override lần đầu
     */
    override fun changeState(state: Int) {
        currentState = state
        when (currentState) {
            STATE_SIGN_IN -> view.toSignInState()
            else -> view.toSignUpState()
        }
    }

    /**
     * @version 1
     * @updated 3/23/2022: Override lần đầu
     */
    override fun action(email: String, password: String, confirmPassword: String) {
        when (currentState) {
            STATE_SIGN_IN -> {
                dataManager.signIn(email, password)
                    .onSuccess { view.signInSuccess() }
                    .onFailure { view.signInFailure(it.message) }
                    .call()
            }
            else -> {
                if (password.isEmpty() || password != confirmPassword) {
                    view.signUpFailure(SignUpResponse.CONFIRM_PASSWORD_FAILED)
                    return
                }
                dataManager.signUp(email, password)
                    .onSuccess { view.signUpSuccess() }
                    .onFailure { view.signUpFailure(it.message) }
                    .call()
            }
        }
    }

    /**
     * @version 1
     * @updated 3/25/2022: Nhiệm vụ là insert các dữ liệu ban đầu vào database
     */
    override fun developTest() {
//        initData(Category::class.java, dataManager.createCategory(FakeData.category))
        dataManager.createCategory(FakeData.category).onSuccess {
            dataManager.createCustomer(FakeData.customers).onSuccess {
                dataManager.createProductColor(FakeData.colors).onSuccess {
                    dataManager.createProductSize(FakeData.sizes).onSuccess {
                        dataManager.createProductUnit(FakeData.units).onSuccess {
                            dataManager.createProduct(FakeData.products).onSuccess {
                                Log.d("last result", "done")
                                dataManager.getAllCustomer()
                                    .call()
                            }.call()
                        }.call()
                    }.call()
                }.call()
            }.call()
        }.call()
//        view.developTestSuccess()
    }

    /**
     * Đệ quy khởi tạo dữ liệu ban đầu, cơ bản để tránh các câu lệnh lồng nhau chứ cũng không dễ hiểu cho lắm, hiện đang lỗi không sử dụng
     *
     * @author Nguyễn Công Chính
     * @since 3/24/2022
     *
     * @version 1
     * @updated 3/24/2022: Tạo function
     */
    private fun <T> initData(clazz: Class<T>, request: ResponseObject<Boolean>) {
        when (clazz) {
            Category::class.java -> {
                request.onSuccess { initData(Customer::class.java, dataManager.createCustomer(FakeData.customers)) }.call()
            }
            Customer::class.java -> {
                request.onSuccess { initData(ProductColor::class.java, dataManager.createProductColor(FakeData.colors)) }.call()
            }
            ProductColor::class.java -> {
                request.onSuccess { initData(ProductSize::class.java, dataManager.createProductSize(FakeData.sizes)) }.call()
            }
            ProductSize::class.java -> {
                request.onSuccess { initData(ProductUnit::class.java, dataManager.createProductUnit(FakeData.units)) }.call()
            }
            ProductUnit::class.java -> {
                request.onSuccess { initData(Product::class.java, dataManager.createProduct(FakeData.products)) }.call()
            }
            Product::class.java -> {
                Log.d("last result", "done")
                dataManager.getAllCustomer()
                    .call()
            }
        }
    }

    companion object {
        const val STATE_SIGN_IN = 1
        const val STATE_SIGN_UP = 2
    }
}