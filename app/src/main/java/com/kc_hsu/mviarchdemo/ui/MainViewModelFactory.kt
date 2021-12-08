package com.kc_hsu.mviarchdemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kc_hsu.mviarchdemo.data.ApiService
import com.kc_hsu.mviarchdemo.data.Repository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(Repository(apiService)) as T
        }

        throw IllegalArgumentException("Unknown modelClass: ${modelClass.name}")
    }
}