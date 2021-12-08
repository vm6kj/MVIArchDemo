package com.kc_hsu.mviarchdemo.data

import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Users
}