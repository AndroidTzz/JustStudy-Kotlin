package com.tzz.kotlin.baselibs.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.multidex.MultiDex
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.tzz.kotlin.baselibs.BuildConfig
import kotlin.properties.Delegates

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-25 11:40
 * @description BaseApp
 */
open class BaseApp : Application() {
    var refwatcher: RefWatcher? = null

    companion object {
        const val TAG = "BaseApp"

        var instance: Context by Delegates.notNull()
            private set

        fun getRefWatcher(context: Context): RefWatcher? {
            val app = context.applicationContext as BaseApp
            return app.refwatcher
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        // 内存泄漏检测
        refwatcher = setupLeakcanary()
        // lifcycle
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
        // ARouter
        initRouter()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun setupLeakcanary(): RefWatcher? {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else {
            LeakCanary.install(this)
        }
    }

    private fun initRouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }

    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {

        }

        override fun onActivityResumed(activity: Activity?) {

        }

        override fun onActivityStarted(activity: Activity?) {
            Log.d(TAG, "onActivityStarted: " + activity?.componentName?.className)
        }

        override fun onActivityDestroyed(activity: Activity?) {
            Log.d(TAG, "onActivityDestroyed: " + activity?.componentName?.className)
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

        }

        override fun onActivityStopped(activity: Activity?) {

        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            Log.d(TAG, "onActivityCreated: " + activity?.componentName?.className)
        }

    }
}