package com.misa.fresher.core

/**
 * Base interface của các interface dao, chứa các hàm crud cơ bản
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface BaseDAO<T> {

    /**
     * Insert danh sách row vào trong bảng
     *
     * @author Nguyễn Công Chính
     * @since 3/24/2022
     *
     * @version 1
     * @updated 3/24/2022: Tạo function
     */
    fun create(list: List<T>): Boolean

    /**
     * Insert 1 row vào trong bảng
     *
     * @author Nguyễn Công Chính
     * @since 3/24/2022
     *
     * @version 1
     * @updated 3/24/2022: Tạo function
     */
    fun create(t: T): Boolean

    /**
     * Lấy toàn bộ row của bảng
     *
     * @author Nguyễn Công Chính
     * @since 3/24/2022
     *
     * @version 1
     * @updated 3/24/2022: Tạo function
     */
    fun getAll(): List<T>

    /**
     * Lấy 1 row có id xác định
     *
     * @author Nguyễn Công Chính
     * @since 3/24/2022
     *
     * @version 1
     * @updated 3/24/2022: Tạo function
     */
    fun getById(id: Long): T?

    /**
     * Cập nhật 1 row
     *
     * @author Nguyễn Công Chính
     * @since 3/24/2022
     *
     * @version 1
     * @updated 3/24/2022: Tạo function
     */
    fun update(t: T): Boolean

    /**
     * Xóa 1 row trong bảng
     *
     * @author Nguyễn Công Chính
     * @since 3/24/2022
     *
     * @version 1
     * @updated 3/24/2022: Tạo function
     */
    fun delete(t: T): Boolean
}