package com.zero.tzz.kotlinbasemvplibs.http

import com.zero.tzz.kotlinbasemvplibs.bean.Banner
import com.zero.tzz.kotlinbasemvplibs.bean.BaseBean
import io.reactivex.Observable
import retrofit2.http.GET

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-28 10:42
 * @description ServiceApi
 */
interface ServiceApi {
    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    @GET("banner/json")
    fun getBanner(): Observable<BaseBean<MutableList<Banner>>>

}