package com.misa.fresher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var sanphams : ArrayList<SanPham>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        val rcvSanPhams = findViewById<View>(R.id.rcv_dulieu) as RecyclerView
        sanphams = SanPham.getListSanPham()
        val adapter = SanPhamAdapter(sanphams)
        rcvSanPhams.adapter = adapter
        rcvSanPhams.layoutManager = LinearLayoutManager(this)
    }

}