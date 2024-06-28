package com.hgm.cleanarcherrorhandling.data

import com.hgm.cleanarcherrorhandling.data.dto.RegisterQuest
import com.hgm.cleanarcherrorhandling.data.dto.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author: HGM
 * @created: 2024/6/28 0028
 * @description:
 **/
interface AuthApi {

      companion object {
            const val BASE_URL="http://192.168.31.161:8100"
      }

      @POST("/register")
      suspend fun register(@Body quest: RegisterQuest): RegisterResponse
}