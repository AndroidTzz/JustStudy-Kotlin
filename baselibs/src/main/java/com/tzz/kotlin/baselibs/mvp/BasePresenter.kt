package com.tzz.kotlin.baselibs.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-26 13:28
 * @description BasePresenter
 */
class BasePresenter<M : IModel, V : IView> : IPresenter<V>, LifecycleObserver {

    protected var mView: V? = null

    protected var mModel: M? = null

    private var mCompositeDisposable: CompositeDisposable? = null

    fun useEventBus(): Boolean = false

    override fun attachView(mView: V) {
        this.mView = mView
        mModel = createModel()
        if (mView is LifecycleOwner) {
            mView.lifecycle.addObserver(this)
            mModel?.let {
                if (it is LifecycleObserver) {
                    mView.lifecycle.addObserver(it)
                }
            }
        }
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
    }

    override fun detachView() {
        // 取消所有订阅
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        clearDisposable()
        mModel?.clearDisposable()
        mModel = null
        mView = null
        mCompositeDisposable = null
    }

    private fun clearDisposable() {
        mCompositeDisposable?.clear()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestory(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
    }

    //////////////////////////////////////////// 外部调用 ////////////////////////////////////////////

    open fun createModel(): M? = null

    open fun addDisposable(disposable: Disposable?) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        disposable?.let { mCompositeDisposable?.add(it) }
    }
}