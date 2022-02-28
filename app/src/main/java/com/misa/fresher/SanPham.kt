package com.misa.fresher
class SanPham(val anhSanPham:Int,val tenSanPham:String,val maSanPham:String,val giaSanPham:Int) {

    companion object{
        fun getListSanPham(): ArrayList<SanPham> {
            val listSP = ArrayList<SanPham>()
            listSP.add(SanPham(R.mipmap.ic_launcher, "san pham 1", "masp1", 1000))
            listSP.add(SanPham(R.mipmap.ic_launcher, "san pham 1", "masp1", 1000))
            listSP.add(SanPham(R.mipmap.ic_launcher, "san pham 1", "masp1", 1000))
            listSP.add(SanPham(R.mipmap.ic_launcher, "san pham 1", "masp1", 1000))
            listSP.add(SanPham(R.mipmap.ic_launcher, "san pham 1", "masp1", 1000))
            listSP.add(SanPham(R.mipmap.ic_launcher, "san pham 1", "masp1", 1000))
            listSP.add(SanPham(R.mipmap.ic_launcher, "san pham 1", "masp1", 1000))
            listSP.add(SanPham(R.mipmap.ic_launcher, "san pham 1", "masp1", 1000))
            listSP.add(SanPham(R.mipmap.ic_launcher, "san pham 1", "masp1", 1000))
            listSP.add(SanPham(R.mipmap.ic_launcher, "san pham 1", "masp1", 1000))
            return listSP
        }
    }

}