package com.kc_hsu.mviarchdemo.ui.intent

sealed class MainIntent {
    object FetchUser : MainIntent()
}