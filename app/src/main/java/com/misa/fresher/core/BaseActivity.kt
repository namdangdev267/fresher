package com.misa.fresher.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Base class cho các Activity, truyền vào Binding layout, contract.view và presenter của nó
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 3
 * @updated 3/9/2022: Tạo class
 * @updated 3/23/2022: Thêm [updateUI] và [presenter]
 * @updated 3/25/2022: Thay đổi generic, chỉ yêu cầu [BaseContract.Presenter] là đủ
 */
abstract class BaseActivity<BD: ViewBinding, PS: BaseContract.Presenter>
    : AppCompatActivity() {

    protected val binding: BD by lazy { getInflater(layoutInflater) }
    protected var presenter: PS? = null

    protected abstract val getInflater: (inflater: LayoutInflater) -> BD
    protected abstract val initPresenter: (Context) -> PS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = initPresenter(this)

        initUI()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    /**
     * Khởi tạo UI cho Activity
     *
     * @author Nguyễn Công Chính
     * @since 3/9/2022
     *
     * @version 1
     * @updated 3/9/2022: Tạo function
     */
    open fun initUI() {}

    /**
     * Cập nhật UI cho Fragment
     *
     * @author Nguyễn Công Chính
     * @since 3/22/2022
     *
     * @version 1
     * @updated 3/22/2022: Tạo function
     */
    open fun updateUI() { }

    override fun onDestroy() {
        super.onDestroy()
        presenter = null
    }
}