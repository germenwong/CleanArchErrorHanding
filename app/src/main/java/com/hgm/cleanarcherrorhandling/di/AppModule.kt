package com.hgm.cleanarcherrorhandling.di

import com.hgm.cleanarcherrorhandling.data.AuthApi
import com.hgm.cleanarcherrorhandling.data.AuthApi.Companion.BASE_URL
import com.hgm.cleanarcherrorhandling.data.AuthRepositoryImpl
import com.hgm.cleanarcherrorhandling.domain.AuthRepository
import com.hgm.cleanarcherrorhandling.domain.DataValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author: HGM
 * @created: 2024/6/28 0028
 * @description:
 **/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

      private val interceptor: HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                  level = HttpLoggingInterceptor.Level.BODY
            }

      @Provides
      @Singleton
      fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                  .addInterceptor(interceptor)
                  .build()
      }

      @Provides
      @Singleton
      fun provideRetrofit(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                  .baseUrl(BASE_URL)
                  .client(client)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build()
      }

      @Provides
      @Singleton
      fun provideUserApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

      @Provides
      @Singleton
      fun provideAuthRepository(api:AuthApi): AuthRepository = AuthRepositoryImpl(api)

      @Provides
      @Singleton
      fun provideDataValidator(): DataValidator = DataValidator()
}