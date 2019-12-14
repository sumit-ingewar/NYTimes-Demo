package com.sumit.test.ui.fragment

import com.sumit.core.DemoLogger
import com.sumit.core.data.model.ArticleParentResponse
import com.sumit.core.data.remote.ArticleUseCase
import com.sumit.core.domain.rxcallback.CallbackWrapper
import com.sumit.test.BuildConfig
import com.sumit.test.base.BaseViewModel
import com.sumit.test.ui.fragment.item.ArticleItem
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
        override fun onResponseSuccess(response: ArticleParentResponse) {

            if (response.status == "OK") {
                val list = ArticleMapper.getMapperArticleList(response.results)
                getNavigator()?.setAdapter(list)
            }

            DemoLogger.e(TAG, response.copyright)

        }

        override fun onResponseError(error: String) {
            DemoLogger.e("onResponseError", error)
            getNavigator()?.clearItems()
            getNavigator()?.diplayErrorToast(error)

        }

        override fun onNewtorkError(error: String) {
            DemoLogger.e("onResponseError", error)
            getNavigator()?.clearItems()
            getNavigator()?.diplayErrorToast(error)
        }

        override fun onServerError(error: String) {
            DemoLogger.e("onServerError", error)
            getNavigator()?.clearItems()
            getNavigator()?.diplayErrorToast(error)
        }

    }

    fun getEmptyList(): ArrayList<Any> {
        val list = ArrayList<Any>()

        for (i in 0 until 3) {
            val viewType = ArticleItemViewType()
            viewType.viewType = ArticleViewType.EMPTY_TYPE_ARTICLE
            list.add(viewType)
        }

        return list
    }

}