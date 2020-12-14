package com.viswa.stockapplication.splash

import android.content.Context
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import dagger.hilt.android.WithFragmentBindings
import java.lang.ref.WeakReference
import javax.inject.Inject

class BioMetricAuthImpl @Inject constructor(val context: Context) : IBiometricAuth {

    var callbackReference : WeakReference<IBiometricAuthCallback>? = null

    override fun authenticate(fragment : Fragment, callback: IBiometricAuthCallback) {
        callbackReference = WeakReference(callback)
        bioMetricPrompt(fragment)
    }

    private fun bioMetricPrompt(fragment : Fragment) {
        val executor = ContextCompat.getMainExecutor(context)
        val prompt = BiometricPrompt(fragment, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    callbackReference?.get()?.authenticationSuccess()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    callbackReference?.get()?.authenticationSuccess()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    callbackReference?.get()?.authenticationSuccess()
                }
            })
        prompt.authenticate(bioMetricPromptBuilder())
    }

    private fun bioMetricPromptBuilder() : BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()
    }
}