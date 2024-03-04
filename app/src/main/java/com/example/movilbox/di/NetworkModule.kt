package com.example.movilbox.di

import com.example.movilbox.data.network.ProductApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "https://dummyjson.com/"

   @Provides
    @Singleton
    fun provideMercadoLibreApi(@ApiUrl apiUrl: String): ProductApiClient {
        val okHttpClient = HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(this).build()
        }

       return Retrofit.Builder()
               .baseUrl(apiUrl)
               .client(okHttpClient)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create()
    }
}
