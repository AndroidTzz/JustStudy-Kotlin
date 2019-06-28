package com.zero.tzz.kotlinbasemvplibs.mvp.contract

import com.tzz.kotlin.baselibs.mvp.IModel
import com.tzz.kotlin.baselibs.mvp.IPresenter
import com.tzz.kotlin.baselibs.mvp.IView
import com.zero.tzz.kotlinbasemvplibs.bean.Banner
import com.zero.tzz.kotlinbasemvplibs.bean.BaseBean
import io.reactivex.Observable

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-28 14:20
 * @description BannerContract
 */
interface BannerContract {
    interface View : IView {
        fun showBanner(list: MutableList<Banner>?)
    }

    interface Presenter : IPresenter<View> {
        fun getBanner()
    }

    interface Model : IModel {
        fun getBanner(): Observable<BaseBean<MutableList<Banner>>>
    }
}