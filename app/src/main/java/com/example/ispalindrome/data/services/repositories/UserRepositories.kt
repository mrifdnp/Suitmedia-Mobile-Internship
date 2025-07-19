package com.example.ispalindrome.data.services.repositories


import UserPagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.ispalindrome.data.services.response.User

import com.example.ispalindrome.data.services.retrofit.ApiConfig
import kotlinx.coroutines.flow.Flow


class UserRepository {

    private val apiService = ApiConfig.apiService

    fun getUserStream(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserPagingSource(apiService) }
        ).flow
    }
}