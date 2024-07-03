package com.hgm.cleanarcherrorhandling.presentation

import com.hgm.cleanarcherrorhandling.R
import com.hgm.cleanarcherrorhandling.domain.DataError
import com.hgm.cleanarcherrorhandling.domain.DataValidator
import com.hgm.cleanarcherrorhandling.domain.Result

/**
 * @author：HGM
 * @created：2024/3/17 0017
 * @description：
 **/
fun DataError.asUiText(): UiText {
      return when (this) {
            DataError.NetworkError.REQUEST_TIMEOUT -> UiText.StringResource(R.string.request_timeout)
            DataError.NetworkError.PAYLOAD_TOO_LARGE -> UiText.StringResource(R.string.payload_too_large)
            DataError.NetworkError.NO_INTERNET -> UiText.StringResource(R.string.no_internet)
            DataError.NetworkError.SERVER_ERROR -> UiText.StringResource(R.string.server_error)
            DataError.NetworkError.UNKNOWN -> UiText.StringResource(R.string.unknown)
            DataError.LocalError.NO_PERMISSION -> UiText.StringResource(R.string.no_permission)
            DataError.LocalError.DISK_FULL -> UiText.StringResource(R.string.disk_full)
            DataError.NetworkError.TOO_MANY_REQUESTS -> UiText.StringResource(R.string.too_many_requests)
            DataError.NetworkError.UNAUTHORIZED -> UiText.StringResource(R.string.unauthorized)
      }
}

fun Result.Error<DataError>.asErrorUiText(): UiText {
      return message.asUiText()
}

fun DataValidator.PasswordError.asUiText():UiText{
      return when (this) {
            DataValidator.PasswordError.TOO_SHORT -> UiText.StringResource(R.string.too_short)
            DataValidator.PasswordError.NO_UPP -> UiText.StringResource(R.string.NO_UPP)
            DataValidator.PasswordError.NO_DIGIT -> UiText.StringResource(R.string.no_digit)
      }
}