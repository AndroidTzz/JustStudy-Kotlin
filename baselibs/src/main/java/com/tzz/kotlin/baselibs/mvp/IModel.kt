package com.tzz.kotlin.baselibs.mvp

import io.reactivex.disposables.Disposable

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-25 17:24
 * @description IModel
 */
interface IModel {

    fun addDisposable(disposable: Disposable?)

    fun clearDisposable()
}