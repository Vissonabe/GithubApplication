package com.viswa.stockapplication.splash

interface IBiometricAuthCallback {
    fun authenticationSuccess()
    fun authenticationError()
}