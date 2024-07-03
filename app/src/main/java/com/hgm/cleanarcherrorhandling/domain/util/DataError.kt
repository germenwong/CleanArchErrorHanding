package com.hgm.cleanarcherrorhandling.domain.util

/**
 * @author：HGM
 * @created：2024/3/17 0017
 * @description：
 **/
sealed interface DataError : Error {
      enum class NetworkError: DataError {
            REQUEST_TIMEOUT,
            PAYLOAD_TOO_LARGE,
            NO_INTERNET,
            SERVER_ERROR,
            UNKNOWN,
            TOO_MANY_REQUESTS,
            UNAUTHORIZED
      }

      enum class LocalError: DataError {
            NO_PERMISSION,
            DISK_FULL
      }
}