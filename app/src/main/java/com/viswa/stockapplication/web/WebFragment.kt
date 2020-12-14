package com.viswa.stockapplication.web

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import com.viswa.stockapplication.R
import com.viswa.stockapplication.databinding.FragmentWebBinding


class WebFragment : Fragment(R.layout.fragment_web) {

    companion object {
        const val DEFAULT = "https://www.google.com"
    }

    val args: WebFragmentArgs by navArgs()
    lateinit var binding : FragmentWebBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWebBinding.bind(view)
        binding.webView.webViewClient = WebViewClient()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(binding.webView.canGoBack()){
                    binding.webView.goBack()
                } else {
                    isEnabled = false
                    activity?.onBackPressed()
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        binding.webView.loadUrl(args.urlArg ?: DEFAULT)
    }
}