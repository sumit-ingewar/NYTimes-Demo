package com.sumit.test.ui.fragment.details

import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.sumit.test.BR
import com.sumit.test.R
import com.sumit.test.base.BaseFragment
import com.sumit.test.databinding.FragmentListBinding
import com.sumit.test.helper.TestWebViewClient
import com.sumit.test.helper.WebViewListener
import com.sumit.test.ui.fragment.SharedViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import java.lang.Exception

class DetailsFragment : BaseFragment<FragmentListBinding, DetailFragmentViewModel>(),
    DetailsFragmentNavigator {

    private lateinit var sharedViewModel: SharedViewModel

    override val viewModel = DetailFragmentViewModel::class.java

    override fun getBindingVariable() = BR.detailFragmentViewModel

    override fun getLayoutId() = R.layout.fragment_details

    override fun initUserInterface(view: View?) {

        sharedViewModel = activity?.run {
            ViewModelProviders.of(this)
                .get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        context?.let {
            webViewArticle.apply {
                webViewClient = TestWebViewClient(webViewListener = object : WebViewListener {
                    override fun onPageStarted() {

                    }

                    override fun onPageFinished() {

                    }
                })

                settings.apply {
                    javaScriptEnabled = true
                    databaseEnabled = true
                    setAppCacheEnabled(true)
                }
            }

        }

        webViewArticle.loadUrl(sharedViewModel.articleUrl.value)

    }

    override fun clearResources() {
        // Loads blank page , clearing arr previous webpage resources
        webViewArticle.loadUrl("about:blank")

        webViewArticle.destroy()
    }


}
