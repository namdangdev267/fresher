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
 * @version 1
 * @updated 3/22/2022: Tạo class
 */
class LoadedAction<T>(
    private val onSuccess: (T) -> Unit,
    private val onFailure: (ErrorMessageModel) -> Unit
) {

    /**
     * Khi trao đổi dữ liệu thành công, dữ liệu trả về có thể là kết quả mong muốn, hoặc một message lỗi
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
     * Khi trao đổi dữ liệu thất bại, lỗi xảy ra do bản thân thiết bị
     *
     * @author Nguyễn Công Chính
     * @since 3/22/2022
     *
     * @version 1
     * @updated 3/22/2022: Tạo function
     */
    fun onException(ex: Throwable) {
        val error = ErrorMessageModel(ErrorMessageModel.LOCAL_ERROR, ex.localizedMessage)
        onFailure(error)
    }
}