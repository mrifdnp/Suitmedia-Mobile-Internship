package com.example.ispalindrome.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ispalindrome.data.services.response.User

import com.example.ispalindrome.data.services.repositories.UserRepository
import com.example.ispalindrome.data.services.retrofit.ApiConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val apiService = ApiConfig.apiService

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore.asStateFlow()

    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: StateFlow<User?> = _selectedUser.asStateFlow()

    private var currentPage = 1
    private var totalPages = 1
    private val perPage = 10

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                currentPage = 1
                val response = apiService.getUsers(page = currentPage, perPage = perPage)
                _users.value = response.data
                totalPages = response.totalPages
            } catch (e: Exception) {
                _users.value = emptyList()
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun loadNextPage() {
        if (_isLoadingMore.value || currentPage >= totalPages) return

        viewModelScope.launch {
            _isLoadingMore.value = true
            try {
                currentPage++
                val response = apiService.getUsers(page = currentPage, perPage = perPage)
                _users.value = _users.value + response.data
            } catch (e: Exception) {
                // error handling optional
            } finally {
                _isLoadingMore.value = false
            }
        }
    }

    fun selectUser(user: User) {
        _selectedUser.value = user
    }
}

