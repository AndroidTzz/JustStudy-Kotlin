package com.tzz.kotlin.baselibs.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import com.tzz.kotlin.baselibs.app.BaseApp
import java.lang.ref.WeakReference

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-26 15:39
 * @description ToastUtils
 */
object ToastUtil {
    private var toast: Toast? = null
    private val context: WeakReference<Context> = WeakReference(BaseApp.instance.applicationContext)

    fun show(message: String, duration: Int = Toast.LENGTH_SHORT, gravity: Int = Gravity.CENTER) {
        toast?.cancel()
        toast = Toast.makeText(context.get(), message, duration)
        toast?.setGravity(gravity, 0, 0)
        toast?.show()
    }
}