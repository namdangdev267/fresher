package com.misa.fresher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class SanPhamAdapter(private val mSanPhams : List<SanPham>) : RecyclerView.Adapter<SanPhamAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contextView = inflater.inflate(R.layout.item_sanpham,parent,false);
        return ViewHolder(contextView);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sanPham : SanPham = mSanPhams.get(position)

        val img_anhSanPham = holder.anhSanPham
        img_anhSanPham.setImageResource(sanPham.anhSanPham)

        val tv_tenSanPham = holder.tenSanPham
        tv_tenSanPham.setText(sanPham.tenSanPham)

        val tv_maSanPham = holder.maSanPham
        tv_maSanPham.setText(sanPham.maSanPham)

        val tv_giaSanPham = holder.giaSanPham
        tv_giaSanPham.setText(""+sanPham.giaSanPham)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val anhSanPham = itemView.findViewById<ImageView>(R.id.img_anhSP)
        val tenSanPham = itemView.findViewById<TextView>(R.id.tv_tenSP)
        val maSanPham = itemView.findViewById<TextView>(R.id.tv_maSP)
        val giaSanPham = itemView.findViewById<TextView>(R.id.tv_giaSP)

    }
    override fun getItemCount(): Int {
       return mSanPhams.size
    }
}