package com.misa.fresher.base

interface IBaseContract {
    interface Presenter<T> {
        fun attach(view: T)
        fun detach()
    }

    interface View {
        fun initPresenter()
    }
}