package dev.yjyoon.kwnotice.presentation.ui.splash

sealed interface SplashState {
    object Done : SplashState
    object Waiting : SplashState
}
