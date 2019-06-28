package com.zero.tzz.kotlinbasemvplibs.mvp.presenter

import com.tzz.kotlin.baselibs.http.SchedulerUtils
import com.tzz.kotlin.baselibs.mvp.BasePresenter
import com.zero.tzz.kotlinbasemvplibs.mvp.contract.BannerContract
import com.zero.tzz.kotlinbasemvplibs.mvp.model.BannerModel

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-28 14:21
 * @description BannerPresenter
 */
class BannerPresenter : BasePresenter<BannerContract.Model, BannerContract.View>(), BannerContract.Presenter {
    override fun createModel(): BannerContract.Model? = BannerModel()

    override fun getBanner() {
        addDisposable(mModel
                ?.getBanner()
                ?.compose(SchedulerUtils.ioToMain())
                ?.subscribe({

                    mView?.showBanner(it?.data)
                }, {
                    mView?.showErrorMsg(it?.toString() ?: "")
                }))
    }

}