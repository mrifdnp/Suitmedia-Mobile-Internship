package com.example.ispalindrome.data.services.retrofit

import com.example.ispalindrome.data.services.response.UserResponse

import retrofit2.http.GET
import retrofit2.http.Query

// ApiService.kt

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10
    ): UserResponse
}
