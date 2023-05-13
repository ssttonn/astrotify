package com.sstonn.astrotify.common


sealed class MainResult<out R> {

    data class Success<out T>(val data: T) : MainResult<T>()
    data class Error(val exception: Exception) : MainResult<Nothing>()
    object Loading : MainResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val MainResult<*>.succeeded
    get() = this is MainResult.Success && data != null