package com.misa.fresher.util

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.misa.fresher.data.model.ErrorMessageModel
import retrofit2.Response

/**
 * Lớp chứa các action xảy ra khi trao đổi dữ liệu thất bại hoặc thành công
 *
 * @author Nguyễn Công Chính
 * @since 3/22/2022
 *
 * @version 2
 * @updated 3/22/2022: Tạo class
 * @updated 3/25/2022: Bổ sung 1 [onResponse] nữa phục vụ cho trao đổi dữ liệu với local database
 */
class LoadedAction<T>(
    private val onSuccess: (T) -> Unit,
    private val onFailure: (ErrorMessageModel) -> Unit
) {

    /**
     * Khi trao đổi dữ liệu qua mạng thành công, dữ liệu trả về có thể là kết quả mong muốn, hoặc một message lỗi
     *
     * @author Nguyễn Công Chính
     * @since 3/22/2022
     *
     * @version 1
     * @updated 3/22/2022: Tạo function
     */
    fun onResponse(response: Response<T>) {
        if (response.isSuccessful) {
            response.body()?.let(onSuccess)
        } else {
            response.errorBody()?.let {
                val errorObj = JsonParser().parse(it.string())
                    .asJsonObject
                    .getAsJsonObject("error")
                val error = Gson().fromJson(errorObj, ErrorMessageModel::class.java)
                onFailure(error)
            }
        }
    }

    /**
     * Khi trao đổi dữ liệu với local database thành công, dữ liệu trả về có thể là kết quả mong muốn, hoặc một message lỗi
     *
     * @author Nguyễn Công Chính
     * @since 3/24/2022
     *
     * @version 1
     * @updated 3/24/2022: Tạo function
     */
    fun onResponse(response: T?) {
        response?.let(onSuccess)
            ?: run {
                onFailure(ErrorMessageModel(ErrorMessageModel.LOCAL_ERROR, "NULL_RESPONSE"))
            }
    }

    /**
     * Khi trao đổi dữ liệu thất bại, lỗi xảy ra do bản thân thiết bị
     *
     * @author Nguyễn Công Chính
     * @since 3/22/2022
     *
     * @version 1
     * @updated 3/22/2022: Tạo function
     */
    fun onException(ex: Throwable) {
        val error = ErrorMessageModel(ErrorMessageModel.LOCAL_ERROR, ex.localizedMessage ?: "UNKNOWN_MESSAGE")
        onFailure(error)
    }
}