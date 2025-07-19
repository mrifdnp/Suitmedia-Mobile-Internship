package com.example.ispalindrome.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {
    private val _selectedUserName = MutableStateFlow("")
    val selectedUserName = _selectedUserName.asStateFlow()

    fun setSelectedUserName(name: String) {
        _selectedUserName.value = name
    }
}
