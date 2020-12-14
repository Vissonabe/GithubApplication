package com.viswa.stockapplication.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.viswa.stockapplication.R
import com.viswa.stockapplication.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash), IBiometricAuthCallback {

    lateinit var binding : FragmentSplashBinding

    @Inject
    lateinit var bioMetricAuthImpl: IBiometricAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        binding.root.setOnClickListener {
            navigationToHome()
        }

        binding.login.setOnClickListener {
//            bioMetricPrompt()
            bioMetricAuthImpl.authenticate(this, this)
        }
    }

    fun navigationToHome() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
    }

    private fun bioMetricPrompt() {
        val executor = ContextCompat.getMainExecutor(context)
        val prompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(context,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(context,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                    navigationToHome()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(context, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
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

    override fun authenticationSuccess() {
        navigationToHome()
    }

    override fun authenticationError() {
        Toast.makeText(context, "Authentication failed",
            Toast.LENGTH_SHORT)
            .show()
    }
}