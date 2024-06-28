package com.hgm.cleanarcherrorhandling.domain


// 使用别名防止与Kotlin Error类重名
typealias RootError = Error

// 接收任何类型的数据和错误信息
sealed interface Result<out D, out E : RootError> {
      data class Success<out D, out E : RootError>(val data: D) : Result<D, E>
      data class Error<out D, out E : RootError>(val message:E) : Result<D, E>
}
