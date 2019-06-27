package com.tzz.kotlin.baselibs.http

import retrofit2.Retrofit

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-27 17:05
 * @description RetrofitFactory
 */
abstract class RetrofitFactory<T>(baseUrl: String, serviceClazz: Class<T>) {

    private var mBaseUrl = ""

    private var mRetrofit: Retrofit? = null

    var service: T? = null

    init {
        mBaseUrl = baseUrl
        if (mBaseUrl.isEmpty()) {
            throw RuntimeException("you must set base url")
        }
        mRetrofit = initRetrofit()
        service = mRetrofit?.create(serviceClazz)
    }

    private fun initRetrofit(): Retrofit? {
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder().build()
        }

        return mRetrofit
    }
}