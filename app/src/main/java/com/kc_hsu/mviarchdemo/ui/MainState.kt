package com.kc_hsu.mviarchdemo.ui

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Users(val users: com.kc_hsu.mviarchdemo.data.Users) : MainState()
    data class Error(val error: String?) : MainState()
}