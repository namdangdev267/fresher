package com.misa.fresher.common

import com.misa.fresher.data.entity.*
import java.util.*

/**
 * Lớp chứa dữ liệu giả cho ứng dụng
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 2
 * @updated 3/10/2022: Tạo class
 * @updated 3/12/2022: Bổ sung một số sản phẩm giả
 */
object FakeData {

    val rd = Random()

    val colors = mutableListOf(
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

    val sizes = mutableListOf(
        ProductSize(1L, "S", "S"),
        ProductSize(2L, "M", "M"),
        ProductSize(3L, "L", "L"),
        ProductSize(4L, "XL", "XL"),
        ProductSize(5L, "Nhỏ", "N"),
        ProductSize(6L, "Lớn", "LO"),
    )

    val units = mutableListOf(
        ProductUnit(1L, "Chiếc"),
        ProductUnit(2L, "Cái"),
        ProductUnit(3L, "Chục"),
        ProductUnit(4L, "Thùng"),
        ProductUnit(5L, "Xe"),
    )

    val category = mutableListOf(
        Category(1L, "Quần"),
        Category(2L, "Áo"),
        Category(3L, "Dép"),
        Category(4L, "Máy tính"),
        Category(5L, "Điện thoại"),
    )

    val products = mutableListOf(
        Product(1L, "Áo thun nam", "ATN", category[1], listOf(
            ProductItem(1L, colors[0], sizes[0], units[0], 100000.0, 100, generateTime()),
            ProductItem(2L, colors[1], sizes[0], units[0], 100000.0, 100, generateTime()),
            ProductItem(3L, colors[0], sizes[1], units[0], 100000.0, 100, generateTime()),
            ProductItem(4L, colors[1], sizes[1], units[0], 100000.0, 100, generateTime()),
            ProductItem(5L, colors[0], sizes[2], units[0], 100000.0, 100, generateTime()),
            ProductItem(6L, colors[1], sizes[2], units[0], 100000.0, 100, generateTime()),
            ProductItem(7L, colors[0], sizes[3], units[0], 100000.0, 100, generateTime()),
            ProductItem(8L, colors[1], sizes[3], units[0], 100000.0, 100, generateTime()),
        )),
        Product(2L, "Quần đùi nam", "QDN", category[0], listOf(
            ProductItem(9L, colors[0], sizes[0], units[0], 100000.0, 100, generateTime()),
            ProductItem(10L, colors[1], sizes[0], units[0], 100000.0, 100, generateTime()),
            ProductItem(11L, colors[0], sizes[1], units[0], 100000.0, 100, generateTime()),
            ProductItem(12L, colors[1], sizes[1], units[0], 100000.0, 100, generateTime()),
            ProductItem(13L, colors[0], sizes[2], units[0], 100000.0, 100, generateTime()),
            ProductItem(14L, colors[1], sizes[2], units[0], 100000.0, 100, generateTime()),
            ProductItem(15L, colors[0], sizes[3], units[0], 100000.0, 100, generateTime()),
            ProductItem(16L, colors[1], sizes[3], units[0], 100000.0, 100, generateTime()),
        )),
        Product(3L, "Máy tính bảng", "MTB", category[0], listOf(
            ProductItem(17L, colors[7], sizes[4], units[0], 10000000.0, 10, generateTime()),
        )),
        Product(4L, "Áo liền quần", "ALQ", category[0], listOf(
            ProductItem(18L, colors[8], sizes[2], units[0], 170000.0, 100, generateTime()),
            ProductItem(28L, colors[4], sizes[2], units[0], 250000.0, 100, generateTime()),
            ProductItem(29L, colors[8], sizes[2], units[2], 250000.0, 100, generateTime()),
            ProductItem(19L, colors[4], sizes[2], units[2], 250000.0, 100, generateTime()),
        )),
        Product(5L, "Váy 2 ống", "VHO", category[0], listOf(
            ProductItem(20L, colors[2], sizes[2], units[0], 450000.0, 23, generateTime()),
            ProductItem(30L, colors[3], sizes[2], units[0], 470000.0, 0, generateTime()),
            ProductItem(31L, colors[4], sizes[2], units[0], 215000.0, 47, generateTime()),
            ProductItem(21L, colors[3], sizes[2], units[1], 470000.0, 0, generateTime()),
            ProductItem(22L, colors[4], sizes[2], units[1], 215000.0, 47, generateTime()),
            ProductItem(23L, colors[2], sizes[2], units[1], 570000.0, 11, generateTime()),
        )),
        Product(6L, "Nón đeo tay", "NDT", category[0], listOf(
            ProductItem(24L, colors[7], sizes[1], units[0], 960000.0, 120, generateTime()),
            ProductItem(32L, colors[3], sizes[1], units[0], 1200000.0, 20, generateTime()),
            ProductItem(33L, colors[7], sizes[3], units[0], 1200000.0, 20, generateTime()),
            ProductItem(25L, colors[3], sizes[3], units[0], 1200000.0, 20, generateTime()),
        )),
        Product(7L, "Điện thoại mạ kim cương", "DTMKC", category[0], listOf(
            ProductItem(26L, colors[7], sizes[1], units[0], 150000000.0, 1, generateTime()),
            ProductItem(27L, colors[8], sizes[1], units[0], 156000000.0, 0, generateTime()),
        )),
    )

    val customers = mutableListOf(
        "Nguyễn Công Chính (0123456789)",
        "Hoàng Thanh Long (015444523)",
        "Nguyễn Thái Bảo (0932653132)",
        "Lưu Thành Đạt (03266564)",
        "Nguyễn Cảnh Phúc (09273127632)",
        "Trương Trung Kiên (0812737123)",
        "Hoàng Đức Minh (01217632487263)",
        "Hoàng Gia Long (08374172364)",
    )

    /**
     * Hàm sinh thời gian ngẫu nhiên từ khoảng 30 ngày trước đến hiện tại
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 1
     * @updated 3/10/2022: Tạo function
     */
    private fun generateTime(): Calendar {
        val now = Calendar.getInstance()
        val randomDay = rd.nextInt(60)
        now.timeInMillis -= randomDay * 86400000L
        return now
    }
}