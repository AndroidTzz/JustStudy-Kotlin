package com.tzz.kotlin.baselibs.http.constants

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-28 11:10
 * @description HttpConstant
 */
object HttpConstant {
    /** 超时 */
    const val DEFAULT_TIME_OUT: Long = 30
    /** 缓存大小 */
    const val MAX_CACHE_SIZE: Long = 50 * 1024 * 1024

    const val CACHE_CONTROL = "Cache-Control"

    const val TOKEN_KEY = "token"
    const val SET_COOKIE_KEY = "set-cookie"
    const val COOKIE_NAME = "Cookie"

    const val SAVE_USER_LOGIN_KEY = "user/login"
    const val SAVE_USER_REGISTER_KEY = "user/register"
    const val REMOVE_USER_LOGOUT_KEY = "user/logout"
}