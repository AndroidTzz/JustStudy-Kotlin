package com.tzz.kotlin.baselibs.http

import com.tzz.kotlin.baselibs.BuildConfig
import com.tzz.kotlin.baselibs.app.BaseApp
import com.tzz.kotlin.baselibs.http.constants.HttpConstant
import com.tzz.kotlin.baselibs.http.interceptor.CacheInterceptor
import com.tzz.kotlin.baselibs.http.interceptor.CookieInterceptor
import com.tzz.kotlin.baselibs.http.interceptor.HeaderInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-27 17:05
 * @description RetrofitFactory
 */
abstract class RetrofitFactory<T> {

    private var mBaseUrl = ""

    private var mRetrofit: Retrofit?

    var service: T

    abstract fun baseUrl(): String

    abstract fun createService(): Class<T>

    init {
        mBaseUrl = baseUrl()
        if (mBaseUrl.isEmpty()) {
            throw RuntimeException("you must set base url")
        }
        mRetrofit = initRetrofit()
        service = mRetrofit!!.create(createService())
    }

    private fun initRetrofit(): Retrofit? {
        if (mRetrofit == null) {
            synchronized(RetrofitFactory::class.java) {
                mRetrofit = Retrofit.Builder()
                        .baseUrl(mBaseUrl)
                        .client(createOkHttpClient())
                        // 可以用携程代替
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        // java用
//                        .addConverterFactory(GsonConverterFactory.create())
                        // kotlin用
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build()
            }
        }

        return mRetrofit
    }

    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        // cache
        val file = File(BaseApp.instance.cacheDir, "chche")
        val cache = Cache(file, HttpConstant.MAX_CACHE_SIZE)
        // logger
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return builder
                .readTimeout(HttpConstant.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(HttpConstant.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(HttpConstant.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(CacheInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(CookieInterceptor())
                .addInterceptor(HeaderInterceptor())
                .retryOnConnectionFailure(true)
                .build()
    }
}