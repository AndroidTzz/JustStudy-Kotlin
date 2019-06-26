package com.tzz.kotlin.baselibs.base

import com.tzz.kotlin.baselibs.mvp.IPresenter
import com.tzz.kotlin.baselibs.mvp.IView

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-26 15:05
 * @description BaseMvpActivity
 */
abstract class BaseMvpActivity<in V : IView, P : IPresenter<V>> : BaseActivity(), IView {

    /** presenter */
    protected var mPresenter: P? = null

    override fun baseInit() {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        mPresenter = null
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showMsg(msg: String) {}

    override fun showErrorMsg(errorMsg: String) {}

    abstract fun createPresenter(): P?
}