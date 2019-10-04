package com.jesusbadenas.kotlin_clean_architecture_project.common

import com.google.gson.GsonBuilder
import com.jesusbadenas.kotlin_clean_architecture_project.data.BuildConfig
import com.jesusbadenas.kotlin_clean_architecture_project.data.api.APIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Network instance
 */
object Network {

    const val API_BASE_URL = "https://raw.githubusercontent.com/"
    const val CONNECT_TIMEOUT = 15000
    const val READ_TIMEOUT = 20000
    const val WRITE_TIMEOUT = 20000

    private fun createHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        // Enable logging
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
            clientBuilder.addInterceptor(interceptor)
        }

        return clientBuilder
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .build()
    }

    fun newAPIService(): APIService {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(createHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(APIService::class.java)
    }
}
