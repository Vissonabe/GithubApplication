package com.viswa.stockapplication.splash

import androidx.fragment.app.Fragment

interface IBiometricAuth {
    fun authenticate(fragment : Fragment, callback: IBiometricAuthCallback)
}