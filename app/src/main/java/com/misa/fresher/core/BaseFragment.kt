package com.misa.fresher.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Base class cho các Fragment
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 2
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Vì nav component luôn tạo lại view kể cả khi back trở lại.
 * Vì vậy [isInit] sẽ kiểm tra nếu đã khởi tạo rồi thì không chạy lại hàm khởi tạo nữa.
 */
abstract class BaseFragment<BD: ViewBinding> : Fragment() {

    protected val binding by lazy { getInflater(layoutInflater) }

    protected abstract val getInflater: (LayoutInflater) -> BD

    private var isInit = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isInit) {
            initUI()
            isInit = true
        }
    }

    /**
     * Khởi tạo UI cho Fragment
     *
     * @author Nguyễn Công Chính
     * @since 3/9/2022
     *
     * @version 1
     * @updated 3/9/2022: Tạo function
     */
    open fun initUI() { }
}