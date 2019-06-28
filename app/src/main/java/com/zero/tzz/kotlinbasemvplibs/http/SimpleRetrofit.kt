package com.zero.tzz.kotlinbasemvplibs.http

import com.tzz.kotlin.baselibs.http.RetrofitFactory

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-28 10:51
 * @description SimpleRetrofit
 */
object SimpleRetrofit : RetrofitFactory<ServiceApi>() {
    override fun baseUrl(): String = ServiceApi.BASE_URL

    override fun createService(): Class<ServiceApi> = ServiceApi::class.java
}