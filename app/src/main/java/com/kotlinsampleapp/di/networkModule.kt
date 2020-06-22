package com.kotlinsampleapp.di

import com.kotlinsampleapp.mainfeature.data.API
import com.kotlinsampleapp.util.APIUtils
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author federico.giardini
 */
val networkModule = module {
    single { provideRetrofit(get()) }

    factory { provideOkHttpClient() }

    factory { provideApi(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(APIUtils.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

fun provideApi(retrofit: Retrofit): API =
    retrofit.create(API::class.java)