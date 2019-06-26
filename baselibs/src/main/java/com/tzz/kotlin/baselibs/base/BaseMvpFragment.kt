package com.tzz.kotlin.baselibs.base

import com.tzz.kotlin.baselibs.mvp.IPresenter
import com.tzz.kotlin.baselibs.mvp.IView

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-26 15:32
 * @description BaseMvpFragment
 */
abstract class BaseMvpFragment<in V : IView, P : IPresenter<V>> : BaseFragment(), IView {

    protected var mPresenter: P? = null

    override fun baseInit() {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.detachView()
        mPresenter = null
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showErrorMsg(errorMsg: String) {}

    override fun showMsg(msg: String) {}

    abstract fun createPresenter(): P?
}