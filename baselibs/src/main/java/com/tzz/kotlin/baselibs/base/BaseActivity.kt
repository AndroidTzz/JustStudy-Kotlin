package com.tzz.kotlin.baselibs.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.MotionEvent
import android.view.WindowManager
import com.cxz.kotlin.baselibs.utils.CommonUtil
import com.cxz.kotlin.baselibs.utils.KeyBoardUtil
import com.cxz.kotlin.baselibs.utils.StatusBarUtil
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tzz.kotlin.baselibs.app.BaseApp
import org.greenrobot.eventbus.EventBus

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-25 14:35
 * @description BaseActivity
 */
abstract class BaseActivity : AppCompatActivity() {

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
    abstract fun initViewAndData()

    override fun onCreate(savedInstanceState: Bundle?) {
        // 软键盘处理
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        super.onCreate(savedInstanceState)
        // 强制竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(attachLayoutRes())
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        baseInit()
        initViewAndData()
    }

    open fun baseInit() {}

    /** 设置状态栏颜色 */
    fun setStatusBarColor(@ColorInt color: Int) {
        StatusBarUtil.setColor(this, color, 0)
    }

    /**
     * 设置状态栏图标颜色
     * @param dark true 黑色 false 白色
     */
    fun setStatusBarIcon(dark: Boolean) {
        StatusBarUtil.setLightStatusBar(this, dark)

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            // 不是Edittext 关闭输入法
            if (KeyBoardUtil.isHideKeyboard(v, ev)) {
                KeyBoardUtil.hideKeyBoard(this, v)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        CommonUtil.fixInputMethodManagerLeak(this)
        BaseApp.getRefWatcher(this)?.watch(this)
    }

}