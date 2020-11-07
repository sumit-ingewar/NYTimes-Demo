package com.sumit.test.ui.fragment

import com.sumit.core.DemoLogger
import com.sumit.core.data.model.ArticleParentResponse
import com.sumit.core.data.remote.ArticleUseCase
import com.sumit.core.domain.remote.BaseError
import com.sumit.core.domain.rxcallback.CallbackWrapper
import com.sumit.core.util.ApiErrorMessages
import com.sumit.test.BuildConfig
import com.sumit.test.base.BaseViewModel
import com.sumit.test.ui.fragment.item.ArticleItemViewType
import com.sumit.test.ui.fragment.item.ArticleViewType
import com.sumit.test.ui.fragment.mapper.ArticleMapper
import javax.inject.Inject

class ListFragmentViewModel @Inject constructor(
    private val articleUseCase: ArticleUseCase
) : BaseViewModel<ListFragmentNavigator>() {


    companion object {
        const val TAG = "ListFragmentViewModel"
    }

    fun callArticleApi() {

        addDisposable(
            articleUseCase.execute(
                ArticleSubscriber(),
                ArticleUseCase.Params.create(
                    BuildConfig.APIKEY
                )
            )
        )
    }

    inner class ArticleSubscriber() : CallbackWrapper<ArticleParentResponse>() {

        override fun onApiSuccess(response: ArticleParentResponse) {
            if (response.status == "OK") {
                val list = ArticleMapper.getMapperArticleList(response.results)
                getNavigator()?.setAdapter(list)
            }
            DemoLogger.e(TAG, response.copyright)
        }

        override fun onApiError(error: BaseError) {
            DemoLogger.e("Error", ApiErrorMessages.getErrorMessage(error))
            getNavigator()?.clearItems()
            getNavigator()?.diplayErrorToast(ApiErrorMessages.getErrorMessage(error))
        }
    }

    fun getEmptyList(): ArrayList<Any> {
        val list = ArrayList<Any>()

        for (i in 0 until 10) {
            val viewType = ArticleItemViewType()
            viewType.viewType = ArticleViewType.EMPTY_TYPE_ARTICLE
            list.add(viewType)
        }

        return list
    }

}