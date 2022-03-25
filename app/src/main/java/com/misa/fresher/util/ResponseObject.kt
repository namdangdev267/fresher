package com.misa.fresher.util

import android.util.Log
import com.misa.fresher.data.model.ErrorMessageModel

/**
 * Lớp builder các hành động thành công, thất bại và thực hiện hành động trao đổi dữ liệu
 *
 * @author Nguyễn Công Chính
 * @since 3/23/2022
 *
 * @version 2
 * @updated 3/23/2022: Tạo class
 * @updated 3/25/2022: Bổ sung sự kiện mặc định khi trao đổi dữ liệu thất bại, sẽ sử dụng khi người dùng không gọi hàm [onFailure]
 */
class ResponseObject<T>(
    private val function: (LoadedAction<T>) -> Unit
) {
    private var success: (T) -> Unit = {}
    private var failure: (ErrorMessageModel) -> Unit = {
        Log.e("Unoverride response err", it.message)
    }

    /**
     * Sự kiện khi trao đổi dữ liệu thành công, kết quả trả về đúng như kì vọng
     *
     * @author Nguyễn Công Chính
     * @since 3/22/2022
     *
     * @version 1
     * @updated 3/22/2022: Tạo function
     */
    fun onSuccess(success: (T) -> Unit): ResponseObject<T> {
        this.success = success
        return this
    }

    /**
     * Sự kiện khi trao đổi thất bại, kết quả trả về không đúng như kì vọng hoặc lỗi không thể trao đổi được
     *
     * @author Nguyễn Công Chính
     * @since 3/22/2022
     *
     * @version 1
     * @updated 3/22/2022: Tạo function
     */
    fun onFailure(failure: (ErrorMessageModel) -> Unit): ResponseObject<T> {
        this.failure = failure
        return this
    }

    /**
     * Thực hiện trao đổi dữ liệu
     *
     * @author Nguyễn Công Chính
     * @since 3/23/2022
     *
     * @version 1
     * @updated 3/23/2022: Tạo function
     */
    fun call() {
        function(LoadedAction(success, failure))
    }
}