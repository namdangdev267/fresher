package com.misa.fresher.util

import android.content.ContentValues

/**
 * Lớp chứa phương thức chuyển đổi 1 object -> content values phục vụ công việc lưu trữ dữ liệu vào db
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 2
 * @updated 3/24/2022: Tạo class
 * @updated 3/31/2022: Di chuyển từ package core -> util
 */
interface CanContentValues {

    /**
     * Phương thức chuyển đổi 1 object -> content values phục vụ công việc lưu trữ dữ liệu vào db, khi lưu mới id sẽ tự tăng nên hàm này không bao gồm id đối tượng
     *
     * @author Nguyễn Công Chính
     * @since 3/24/2022
     *
     * @version 1
     * @updated 3/24/2022: Tạo function
     */
    fun getContentValues(): ContentValues

    /**
     * Phương thức chuyển đổi 1 object -> content values phục vụ công việc lưu trữ dữ liệu vào db, khi cập nhật đã có sẵn id nên hàm này bao gồm id trong values
     *
     * @author Nguyễn Công Chính
     * @since 3/24/2022
     *
     * @version 1
     * @updated 3/24/2022: Tạo function
     */
    fun getContentValuesWithId(): ContentValues
}