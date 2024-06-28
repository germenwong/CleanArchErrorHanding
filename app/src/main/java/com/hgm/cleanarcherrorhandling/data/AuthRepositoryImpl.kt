package com.hgm.cleanarcherrorhandling.data

import com.hgm.cleanarcherrorhandling.data.dto.RegisterQuest
import com.hgm.cleanarcherrorhandling.data.dto.RegisterResponse
import com.hgm.cleanarcherrorhandling.domain.AuthRepository
import com.hgm.cleanarcherrorhandling.domain.DataError
import com.hgm.cleanarcherrorhandling.domain.Result
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

/**
 * @author：HGM
 * @created：2024/3/17 0017
 * @description：
 **/
class AuthRepositoryImpl(
      private val authApi: AuthApi
) : AuthRepository {

      override suspend fun register(
            email: String,
            username: String,
            password: String
      ): Result<RegisterResponse, DataError.NetworkError> {
            return safeCall {
                  val request = RegisterQuest(email, username, password)
                  authApi.register(request)
            }
      }
}

inline fun <reified T> safeCall(execute: () -> T): Result<T, DataError.NetworkError> {
      return try {
            val response = execute()
            Result.Success(response)
      } catch (e: HttpException) {
            e.printStackTrace()
            when (e.code()) {
                  401 -> Result.Error(DataError.NetworkError.UNAUTHORIZED)
                  408 -> Result.Error(DataError.NetworkError.REQUEST_TIMEOUT)
                  413 -> Result.Error(DataError.NetworkError.PAYLOAD_TOO_LARGE)
                  429 -> Result.Error(DataError.NetworkError.TOO_MANY_REQUESTS)
                  in 500..599 -> Result.Error(DataError.NetworkError.SERVER_ERROR)
                  else -> Result.Error(DataError.NetworkError.UNKNOWN)
            }
      } catch (e: UnknownHostException) {
            e.printStackTrace()
            Result.Error(DataError.NetworkError.NO_INTERNET)
      } catch (e: SocketTimeoutException) {
            e.printStackTrace()
            Result.Error(DataError.NetworkError.REQUEST_TIMEOUT)
      } catch (e: IOException) {
            e.printStackTrace()
            Result.Error(DataError.NetworkError.UNKNOWN)
      } catch (e: CancellationException) {
            throw e
      } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(DataError.NetworkError.UNKNOWN)
      }
}