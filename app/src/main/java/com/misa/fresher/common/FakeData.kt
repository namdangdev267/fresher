package com.misa.fresher.common

import com.misa.fresher.data.entity.*

/**
 * Lớp chứa dữ liệu giả cho ứng dụng
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 6
 * @updated 3/10/2022: Tạo class
 * @updated 3/12/2022: Bổ sung một số sản phẩm giả
 * @updated 3/12/2022: Đổi từ mutablelist -> list do không có nhu cầu sửa chữa
 * @updated 3/15/2022: Đổi từ public -> private do không có nhu cầu gọi từ bên ngoài, đổi list [customers] từ list string -> list [Customer]
 * @updated 3/16/2022: thêm trường [bills]
 * @updated 3/18/2022: Thêm hàm [getId] để lấy Id mà không lo bị trùng
 */
object FakeData {
    private val colors = listOf(
        ProductColor(1L, "Xanh", "X"),
        ProductColor(2L, "Đỏ", "DO"),
        ProductColor(3L, "Tím", "T"),
        ProductColor(4L, "Vàng", "V"),
        ProductColor(5L, "Lục", "LU"),
        ProductColor(6L, "Lam", "LA"),
        ProductColor(7L, "Chàm", "CH"),
        ProductColor(8L, "Cam", "CA"),
        ProductColor(9L, "Đen", "DE"),
        ProductColor(10L, "Trắng", "T"),
    )

    private val sizes = listOf(
        ProductSize(1L, "S", "S"),
        ProductSize(2L, "M", "M"),
        ProductSize(3L, "L", "L"),
        ProductSize(4L, "XL", "XL"),
        ProductSize(5L, "Nhỏ", "N"),
        ProductSize(6L, "Lớn", "LO"),
    )

    private val units = listOf(
        ProductUnit(1L, "Chiếc"),
        ProductUnit(2L, "Cái"),
        ProductUnit(3L, "Chục"),
        ProductUnit(4L, "Thùng"),
        ProductUnit(5L, "Xe"),
    )

    val category = listOf(
        Category(1L, "Quần"),
        Category(2L, "Áo"),
        Category(3L, "Dép"),
        Category(4L, "Máy tính"),
        Category(5L, "Điện thoại"),
    )

    val products = listOf(
        Product(1L, "Áo thun nam", "ATN", category[1], listOf(
            ProductItem(1L, colors[0], sizes[0], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(2L, colors[1], sizes[0], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(3L, colors[0], sizes[1], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(4L, colors[1], sizes[1], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(5L, colors[0], sizes[2], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(6L, colors[1], sizes[2], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(7L, colors[0], sizes[3], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(8L, colors[1], sizes[3], units[0], 100000.0, 100, RandomSingleton.generateTime()),
        )),
        Product(2L, "Quần đùi nam", "QDN", category[0], listOf(
            ProductItem(9L, colors[0], sizes[0], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(10L, colors[1], sizes[0], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(11L, colors[0], sizes[1], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(12L, colors[1], sizes[1], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(13L, colors[0], sizes[2], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(14L, colors[1], sizes[2], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(15L, colors[0], sizes[3], units[0], 100000.0, 100, RandomSingleton.generateTime()),
            ProductItem(16L, colors[1], sizes[3], units[0], 100000.0, 100, RandomSingleton.generateTime()),
        )),
        Product(3L, "Máy tính bảng", "MTB", category[3], listOf(
            ProductItem(17L, colors[7], sizes[4], units[0], 10000000.0, 10, RandomSingleton.generateTime()),
        )),
        Product(4L, "Áo liền quần", "ALQ", category[1], listOf(
            ProductItem(18L, colors[8], sizes[2], units[0], 170000.0, 100, RandomSingleton.generateTime()),
            ProductItem(28L, colors[4], sizes[2], units[0], 250000.0, 100, RandomSingleton.generateTime()),
            ProductItem(29L, colors[8], sizes[2], units[2], 250000.0, 100, RandomSingleton.generateTime()),
            ProductItem(19L, colors[4], sizes[2], units[2], 250000.0, 100, RandomSingleton.generateTime()),
        )),
        Product(5L, "Váy 2 ống", "VHO", category[0], listOf(
            ProductItem(20L, colors[2], sizes[2], units[0], 450000.0, 23, RandomSingleton.generateTime()),
            ProductItem(30L, colors[3], sizes[2], units[0], 470000.0, 0, RandomSingleton.generateTime()),
            ProductItem(31L, colors[4], sizes[2], units[0], 215000.0, 47, RandomSingleton.generateTime()),
            ProductItem(21L, colors[3], sizes[2], units[1], 470000.0, 10, RandomSingleton.generateTime()),
            ProductItem(22L, colors[4], sizes[2], units[1], 215000.0, 47, RandomSingleton.generateTime()),
            ProductItem(23L, colors[2], sizes[2], units[1], 570000.0, 11, RandomSingleton.generateTime()),
        )),
        Product(6L, "Nón đeo tay", "NDT", category[1], listOf(
            ProductItem(24L, colors[7], sizes[1], units[0], 960000.0, 120, RandomSingleton.generateTime()),
            ProductItem(32L, colors[3], sizes[1], units[0], 1200000.0, 20, RandomSingleton.generateTime()),
            ProductItem(33L, colors[7], sizes[3], units[0], 1200000.0, 20, RandomSingleton.generateTime()),
            ProductItem(25L, colors[3], sizes[3], units[0], 1200000.0, 20, RandomSingleton.generateTime()),
        )),
        Product(7L, "Điện thoại mạ kim cương", "DTMKC", category[4], listOf(
            ProductItem(26L, colors[7], sizes[1], units[0], 150000000.0, 1, RandomSingleton.generateTime()),
            ProductItem(27L, colors[8], sizes[1], units[0], 156000000.0, 0, RandomSingleton.generateTime()),
        )),
    )

    val customers = listOf(
        Customer(1L, "Nguyễn Công Chính", "0123456789", "Hoài Đức, Hà Nội"),
        Customer(2L, "Hoàng Thanh Long", "015444523", "Nghĩa Hưng, Nam Định"),
        Customer(3L, "Nguyễn Thái Bảo", "0932653132", "Lộc Hà, Hà Tĩnh"),
        Customer(4L, "Lưu Thành Đạt", "03266564", "Hoài Đức, Hà Nội"),
        Customer(5L, "Nguyễn Cảnh Phúc", "09273127632", "Thành phố Thái Bình"),
        Customer(6L, "Trương Trung Kiên", "0812737123", "Nhân Chính, Hà Nội"),
        Customer(7L, "Hoàng Đức Minh", "01217632487263", "Thành phố Ninh Bình"),
        Customer(8L, "Hoàng Gia Long", "08374172364", "Văn Lãng, Lạng Sơn"),
    )

    val bills = mutableListOf<Bill>()

    private var billId = 21210010000L

    fun getId(type: Int): Long = when (type) {
        BILL_ID -> ++billId
        else -> 0
    }

    const val BILL_ID = 1
}