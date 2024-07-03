package com.hgm.cleanarcherrorhandling.domain

import com.hgm.cleanarcherrorhandling.data.dto.RegisterResponse

/**
 * @author：HGM
 * @created：2024/3/17 0017
 * @description：
 **/
interface AuthRepository {

      suspend fun register(
            email: String,
            username: String,
            password: String
      ): Result<RegisterResponse, DataError.NetworkError>
}