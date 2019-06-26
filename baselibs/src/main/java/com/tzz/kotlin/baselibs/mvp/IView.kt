package com.tzz.kotlin.baselibs.mvp

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-25 17:24
 * @description IView
 */
interface IView {

    /** 显示Loading */
    fun showLoading()

    /** 隐藏Loading */
    fun hideLoading()

    /** 显示信息 */
    fun showMsg(msg: String)

    /** 显示错误信息 */
    fun showErrorMsg(errorMsg: String)
}