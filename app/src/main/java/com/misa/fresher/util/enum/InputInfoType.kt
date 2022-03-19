package com.misa.fresher.util.enum

/**
 * Lớp enum chứa các loại item dùng để nhập liệu
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
enum class InputInfoType {
    TAP_ACTION,
    TAP_INSERT,
    SPINNER,
    SINGLE_CHECK_BOX,
    TWO_COLUMN,

    // specical type
    DELIVERY_TYPE,
    PACKAGE_SIZE,
}