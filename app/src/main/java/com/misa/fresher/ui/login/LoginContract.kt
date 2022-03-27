package com.misa.fresher.ui.login

import com.misa.fresher.core.BaseContract

/**
 * Contract cho màn hình đăng nhập
 *
 * @author Nguyễn Công Chính
 * @since 3/23/2022
 *
 * @version 1
 * @updated 3/23/2022: Tạo class
 */
interface LoginContract {

    interface View: BaseContract.View {

        /**
         * Hàm chuyển sang trạng thái đăng nhập
         *
         * @author Nguyễn Công Chính
         * @since 3/23/2022
         *
         * @version 1
         * @updated 3/23/2022: Tạo function
         */
        fun toSignInState()

        /**
         * Hàm chuyển sang trạng thái đăng kí
         *
         * @author Nguyễn Công Chính
         * @since 3/23/2022
         *
         * @version 1
         * @updated 3/23/2022: Tạo function
         */
        fun toSignUpState()

        fun signInSuccess()
        fun signInFailure(error: String)
        fun signUpSuccess()
        fun signUpFailure(error: String)
    }

    interface Presenter: BaseContract.Presenter {

        /**
         * Hàm thay đổi trạng thái đăng kí <-> đăng nhập của màn hình
         *
         * @author Nguyễn Công Chính
         * @since 3/23/2022
         *
         * @version 1
         * @updated 3/23/2022: Tạo function
         */
        fun changeState(state: Int)

        /**
         * Nút chung cho sự kiện đăng kí, đăng nhập tùy vào trạng thái hiện tại của màn hình
         *
         * @author Nguyễn Công Chính
         * @since 3/23/2022
         *
         * @version 1
         * @updated 3/23/2022: Tạo function
         */
        fun action(email: String, password: String, confirmPassword: String)
    }
}