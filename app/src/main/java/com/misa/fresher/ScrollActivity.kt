package com.misa.fresher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ScrollActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrollview)
        initData()
        configUI()

    }

    private fun configUI() {
        configToolbar()
        configRC()
    }

    private fun configRC() {

    }

    private fun configToolbar() {
        TODO("Not yet implemented")
    }

    /**
     * khởi tạo dữ liệu
     * @Author: DHNam
     * @Date: 09/03/2022
     **/
    private fun initData() {

    }

}