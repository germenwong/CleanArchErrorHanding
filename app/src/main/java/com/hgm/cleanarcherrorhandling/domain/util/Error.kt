package com.hgm.cleanarcherrorhandling.domain.util



// 通过实现 Error 这个接口我们可以自定义很多错误信息组
// 验证错误、网络错误、配置错误等等
sealed interface Error

// 使用别名防止与Kotlin Error类重名
typealias RootError = Error