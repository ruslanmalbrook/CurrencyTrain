package com.currency.traintest.presentation.stat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.currency.traintest.MainActivity
import com.currency.traintest.databinding.FragmentStatBinding
import com.currency.traintest.presentation.train.TrainFragment


class StatFragment : Fragment() {

    private var _binding: FragmentStatBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = StatFragment()
    }

    private lateinit var viewModel: StatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.wvHtml.settings.setJavaScriptEnabled(true);
        binding.wvHtml.settings.setDomStorageEnabled(true);
        binding.wvHtml.settings.setLoadWithOverviewMode(true);
        binding.wvHtml.settings.setUseWideViewPort(true);
        binding.wvHtml.settings.setBuiltInZoomControls(true);
        binding.wvHtml.settings.setDisplayZoomControls(false);
        binding.wvHtml.settings.setSupportZoom(true);
        binding.wvHtml.settings.setDefaultTextEncodingName("utf-8");
        binding.wvHtml.setWebChromeClient(WebChromeClient())
        binding.wvHtml.setWebViewClient(WebViewClient())
        binding.wvHtml.loadUrl("file:///android_asset/currencys.html")

        binding.submit.setOnClickListener {
            (requireActivity() as MainActivity).replaceFragment(TrainFragment.newInstance())
        }
    }

}