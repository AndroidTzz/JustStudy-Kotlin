package com.tzz.kotlin.baselibs.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tzz.kotlin.baselibs.app.BaseApp
import org.greenrobot.eventbus.EventBus

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-25 17:12
 * @description BaseFragment
 */
abstract class BaseFragment : Fragment() {

    /** 是否加载完毕 */
    private var isViewLoad: Boolean = false

    /** 已经加载过数据 */
    private var hasLoad: Boolean = false

    /** 是否使用EventBus */
    private fun useEventBus(): Boolean = false

    /** 获取权限申请类 */
    protected val rxPermissions: RxPermissions by lazy {
        RxPermissions(this)
    }

    /** 布局Id */
    @LayoutRes
    abstract fun attachLayoutRes(): Int

    /** 初始化数据 */
    abstract fun initViewAndData(view: View)

    /** 懒加载 */
    abstract fun lazyLoad()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(attachLayoutRes(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        isViewLoad = true
        baseInit()
        initViewAndData(view)
        needLazyLoad()

    }

    open fun baseInit() {}

    private fun needLazyLoad() {
        if (isViewLoad && userVisibleHint && !hasLoad) {
            hasLoad = true
            lazyLoad()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            needLazyLoad()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        activity?.let { BaseApp.getRefWatcher(it)?.watch(it) }
    }
}