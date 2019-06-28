package com.zero.tzz.kotlinbasemvplibs.bean

import com.squareup.moshi.Json

/**
 *
 * @author Zero_Tzz
 * @date 2019-06-28 10:44
 * @description DataResponse
 */

data class BaseBean<T>(
        @Json(name = "data") val data: T,
        @Json(name = "errorCode") val errorCode: Int,
        @Json(name = "errorMsg") val errorMsg: String
)

data class Banner(
        @Json(name = "desc") val desc: String,
        @Json(name = "id") val id: Int,
        @Json(name = "imagePath") val imagePath: String,
        @Json(name = "isVisible") val isVisible: Int,
        @Json(name = "order") val order: Int,
        @Json(name = "title") val title: String,
        @Json(name = "type") val type: Int,
        @Json(name = "url") val url: String
)