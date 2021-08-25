package com.sumit.test.ui.fragment.details

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.activityViewModels
import com.sumit.test.R
import com.sumit.test.base.BaseFragment
import com.sumit.test.databinding.FragmentDetailsBinding
import com.sumit.test.helper.TestWebViewClient
import com.sumit.test.helper.WebViewListener
import com.sumit.test.ui.fragment.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(),
    DetailsFragmentNavigator {

    override val bindingInflater: (LayoutInflater) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun getLayoutId() = R.layout.fragment_details

    override fun initUserInterface(view: View?) {
        context?.let {
            binding.webViewArticle.apply {
                webViewClient = TestWebViewClient(webViewListener = object : WebViewListener {
                    override fun onPageStarted() {}

                    override fun onPageFinished() {}
                })

                settings.apply {
                    javaScriptEnabled = true
                    databaseEnabled = true
                    setAppCacheEnabled(true)
                }
            }
        }
        binding.webViewArticle.loadUrl(sharedViewModel.articleUrl.value)
    }

    override fun clearResources() {
        binding.webViewArticle.loadUrl("about:blank")
        binding.webViewArticle.destroy()
    }
}
