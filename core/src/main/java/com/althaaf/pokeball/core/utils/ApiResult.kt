package com.althaaf.pokeball.core.utils

sealed class ApiResult<out T> {
    data object Loading: ApiResult<Nothing>()
    data class Success<T>(val data: T): ApiResult<T>()
    data class Error(
        val message: String? = null,
        val exception: Exception? = null,
        val code: Int? = null
    ): ApiResult<Nothing>()
    data object Empty : ApiResult<Nothing>()
}
