package com.tzz.kotlin.baselibs.http

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-28 14:05
 * @description SchedulerUtils
 */
object SchedulerUtils {

    fun <T> ioToMain(): ObservableTransformer<T, T> = ObservableTransformer { it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }

}