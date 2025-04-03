package com.hanatransaction.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Singleton class to create Retrofit instance for API access
 */
object RetrofitInstance {
    
    private const val BASE_URL = "https://api.hanabank.com/v1/" // Mock URL, replace with actual API URL
    
    // Create logging interceptor for debugging
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    
    // Create OkHttp client with interceptors and timeout configuration
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    
    // Create Retrofit instance
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    // Create the API service
    val hanaBankApiService: HanaBankApiService by lazy {
        retrofit.create(HanaBankApiService::class.java)
    }
} 