package com.helloworld.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    
    private val _greetingText = MutableLiveData<String>()
    val greetingText: LiveData<String> = _greetingText
    
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    
    private val _clickCount = MutableLiveData(0)
    val clickCount: LiveData<Int> = _clickCount
    
    init {
        loadGreeting()
    }
    
    private fun loadGreeting() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                delay(500)
                _greetingText.value = "Welcome to Hello World App!\nOptimized for Redmi Note 7"
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to load: ${e.message}"
                _greetingText.value = "Hello World!"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun onButtonClicked() {
        val currentCount = _clickCount.value ?: 0
        _clickCount.value = currentCount + 1
        _greetingText.value = "Hello World! (${currentCount + 1} clicks)"
    }
    
    fun clearError() {
        _error.value = null
    }
}