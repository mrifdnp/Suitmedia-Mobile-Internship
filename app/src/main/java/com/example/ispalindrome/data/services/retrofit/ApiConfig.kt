package com.example.ispalindrome.data.services.retrofit

import android.util.Log
import com.example.ispalindrome.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private const val BASE_URL = "https://reqres.in/api/"

    val apiService: ApiService by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val headerInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", BuildConfig.REQRES_API_KEY) // <== pastikan ini tidak null
                .build()
            Log.d("HeaderCheck", "API Key: ${BuildConfig.REQRES_API_KEY}")
            chain.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(headerInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}