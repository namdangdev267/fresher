package com.misa.fresher.data

import com.misa.fresher.R
import com.misa.fresher.model.Customer
import com.misa.fresher.model.Products

/**
 * tạo data
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
object DataForTest {
    val listProduct = mutableListOf(
        Products("ABC01-D-S", "Ao dai (Do/S)", 9000.0, R.drawable.ic_shopping_bag, "Do", "S"),
        Products("ABC01-D-L", "Ao dai (Do/L)", 1020.0, R.drawable.ic_shopping_bag, "Do", "L"),
        Products("ABC01-D-M", "Ao dai (Do/M)", 13000.0, R.drawable.ic_shopping_bag, "Do", "M"),
        Products("ABC01-D-XL", "Ao dai (Do/XL)", 1400.0, R.drawable.ic_shopping_bag, "Do", "XL"),
        Products("ABC01-D-S", "Ao dai (Xanh/S)", 1500.0, R.drawable.ic_shopping_bag, "Xanh", "S"),
        Products("ABC01-D-M", "Ao dai (Xanh/M)", 2000.0, R.drawable.ic_shopping_bag, "Xanh", "M"),
        Products("ABC01-D-S", "Ao dai (Xam/S)", 4000.0, R.drawable.ic_shopping_bag, "Xam", "S"),
        Products("ABC01-D-M", "Ao dai (Tim/M)", 5000.0, R.drawable.ic_shopping_bag, "Tim", "M"),
        Products("BBC01-D-S", "Ao Phong (Do/S)", 900.0, R.drawable.ic_shopping_bag, "Do", "S"),
        Products("BBC01-D-L", "Ao Phong (Do/L)", 10200.0, R.drawable.ic_shopping_bag, "Do", "L"),
        Products("BBC01-D-M", "Ao Phong (Do/M)", 1300.0, R.drawable.ic_shopping_bag, "Do", "M"),
        Products("BBC01-D-XL", "Ao Phong (Do/XL)", 1400.0, R.drawable.ic_shopping_bag, "Do", "XL"),
        Products("BBC01-D-S", "Ao Phong (Xanh/S)", 1500.0, R.drawable.ic_shopping_bag, "Xanh", "S"),
        Products("BBC01-D-M", "Ao Phong (Xanh/M)", 2000.0, R.drawable.ic_shopping_bag, "Xanh", "M"),
        Products("BBC01-D-S", "Ao Phong (Xam/S)", 4000.0, R.drawable.ic_shopping_bag, "Xam", "S"),
        Products("BBC01-D-M", "Ao Phong (Tim/M)", 500.0, R.drawable.ic_shopping_bag, "Tim", "M"),
        Products("CC01-D-S", "Quan bo (Do/S)", 900.0, R.drawable.ic_shopping_bag, "Do", "S"),
        Products("CC01-D-L", "Quan bo (Do/L)", 1020.0, R.drawable.ic_shopping_bag, "Do", "L"),
        Products("CC01-D-M", "Quan bo (Do/M)", 13000.0, R.drawable.ic_shopping_bag, "Do", "M"),
        Products("CC01-D-XL", "Quan bo (Do/XL)", 1400.0, R.drawable.ic_shopping_bag, "Do", "XL"),
        Products("CC01-D-S", "Quan bo (Xanh/S)", 15000.0, R.drawable.ic_shopping_bag, "Xanh", "S"),
        Products("CC01-D-M", "Quan bo (Xanh/M)", 2000.0, R.drawable.ic_shopping_bag, "Xanh", "M"),
        Products("CC01-D-S", "Quan bo (Xam/S)", 400.0, R.drawable.ic_shopping_bag, "Xam", "S"),
        Products("CC01-D-M", "Quan bo (Tim/M)", 500.0, R.drawable.ic_shopping_bag, "Tim", "M"),
    )
    val listCus = mutableListOf(
        Customer("Nguyễn Thái Bảo", "0948621305", "Hà Tĩnh"),
        Customer("Nguyễn Thái Long", "0948621305", "Hà Tĩnh"),
        Customer("Nguyễn Thái Hoàng", "0948621305", "Hà Tĩnh"),
        Customer("Nguyễn Thái Huy", "0948621305", "Hà Tĩnh"),
        Customer("Nguyễn Thái Đức", "0948621305", "Hà Tĩnh"),
        Customer("Nguyễn Thái Minh", "0948621305", "Hà Tĩnh"),
        Customer("Nguyễn Thái Đạt", "0948621305", "Hà Tĩnh"),
        Customer("Nguyễn Thái Kiên", "0948621305", "Hà Tĩnh"),

        )
}