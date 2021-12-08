package com.kc_hsu.mviarchdemo.data

class Repository(private val apiService: ApiService) {
    suspend fun getUsers(): Users = apiService.getUsers()
}