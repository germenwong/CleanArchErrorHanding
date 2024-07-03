package com.hgm.cleanarcherrorhandling.domain

import com.hgm.cleanarcherrorhandling.domain.util.Error
import com.hgm.cleanarcherrorhandling.domain.util.Result

/**
 * @author：HGM
 * @created：2024/3/17 0017
 * @description：
 **/
class DataValidator {

      fun validatePassword(password: String): Result<Unit, PasswordError> {
            if (password.length < 9) {
                  return Result.Error(PasswordError.TOO_SHORT)
            }

            val hasUppChar = password.any { it.isUpperCase() }
            if (!hasUppChar) {
                  return Result.Error(PasswordError.NO_UPP)
            }

            val hasDigitChar = password.any { it.isDigit() }
            if (!hasDigitChar) {
                  return Result.Error(PasswordError.NO_DIGIT)
            }

            return Result.Success(Unit)
      }

      enum class PasswordError : Error {
            TOO_SHORT, NO_UPP, NO_DIGIT
      }
}