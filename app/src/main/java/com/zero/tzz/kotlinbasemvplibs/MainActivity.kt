package com.zero.tzz.kotlinbasemvplibs

import com.tzz.kotlin.baselibs.base.BaseMvpActivity
import com.tzz.kotlin.baselibs.utils.ToastUtil
import com.zero.tzz.kotlinbasemvplibs.bean.Banner
import com.zero.tzz.kotlinbasemvplibs.mvp.contract.BannerContract
import com.zero.tzz.kotlinbasemvplibs.mvp.presenter.BannerPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<BannerContract.View, BannerContract.Presenter>(), BannerContract.View {

    override fun createPresenter(): BannerContract.Presenter? = BannerPresenter()

    override fun attachLayoutRes(): Int = R.layout.activity_main

    override fun initViewAndData() {
        tv.setOnClickListener {
            mPresenter?.getBanner()
        }
    }

    override fun showBanner(list: MutableList<Banner>?) {
        tv.text = list?.toString()
    }

    override fun showErrorMsg(errorMsg: String) {
        ToastUtil.show(errorMsg)
    }

}
