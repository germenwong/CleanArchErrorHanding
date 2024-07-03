package com.hgm.cleanarcherrorhandling.domain.util


// 接收任何类型的数据和错误信息
sealed interface Result<out D, out E : RootError> {
      data class Success<out D>(val data: D) : Result<D, Nothing>
      data class Error<out E : RootError>(val message: E) : Result<Nothing, E>
      //data class Success<out D,out E:RootError>(val data:D) : Result<D, E>
      //data class Error<out D,out E : RootError>(val message: E) : Result<D, E>
}

inline fun <T, E : RootError, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
      return when (this) {
            is Result.Error -> Result.Error(message)
            is Result.Success -> Result.Success(map(data))
      }
}

fun <T, E : RootError> Result<T, E>.asEmptyDataResult(): EmptyResult<E> {
      return map { }
}

typealias EmptyResult<E> = Result<Unit, E>