package com.tzz.kotlin.baselibs.mvp

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-25 17:24
 * @description IPresenter
 */
interface IPresenter<in V : IView> {

    /** 绑定View */
    fun attachView(mView: V)

    /** 解绑View */
    fun detachView()
}