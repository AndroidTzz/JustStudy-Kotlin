package com.zero.tzz.kotlinbasemvplibs.mvp.model

import com.tzz.kotlin.baselibs.mvp.BaseModel
import com.zero.tzz.kotlinbasemvplibs.bean.Banner
import com.zero.tzz.kotlinbasemvplibs.bean.BaseBean
import com.zero.tzz.kotlinbasemvplibs.http.SimpleRetrofit
import com.zero.tzz.kotlinbasemvplibs.mvp.contract.BannerContract
import io.reactivex.Observable

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-28 14:21
 * @description BannerModel
 */
class BannerModel : BaseModel(), BannerContract.Model {
    override fun getBanner(): Observable<BaseBean<MutableList<Banner>>> {
        return SimpleRetrofit.service.getBanner()
    }

}