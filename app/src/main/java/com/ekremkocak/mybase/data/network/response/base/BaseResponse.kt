package com.ekremkocak.mybase.data.network.response.base

data class BaseResponse(
    val code: Int,
    val errors: List<String>,
    val message: String,
    val result: String,
    val succeeded: Boolean
)