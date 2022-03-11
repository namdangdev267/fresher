package com.misa.fresher.core

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Base class cho các Activity
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 1
 * @updated 3/9/2022: Tạo class
 */
abstract class BaseActivity<BD: ViewBinding>: AppCompatActivity() {

    protected val binding: BD by lazy { getInflater(layoutInflater) }

    abstract val getInflater: (inflater: LayoutInflater) -> BD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUI()
    }

    /**
     * Khởi tạo UI cho Activity
     *
     * @author Nguyễn Công Chính
     * @since 3/9/2022
     *
     * @version 1.0.0
     * @updated 3/9/2022: Tạo function
     */
    open fun initUI() {}
}