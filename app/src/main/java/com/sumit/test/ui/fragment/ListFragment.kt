package com.sumit.test.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumit.test.R
import com.sumit.test.base.BaseFragment
import com.sumit.test.base.ItemClickListener
import com.sumit.test.databinding.FragmentListBinding
import com.sumit.test.ui.fragment.adapter.ArticleAdapter
import com.sumit.test.ui.fragment.adapter.ArticleDiffUtils
import com.sumit.test.ui.fragment.item.ArticleItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment :  BaseFragment<FragmentListBinding>(),
    ListFragmentNavigator, ItemClickListener {

    // region VARIABLES

    override val bindingInflater: (LayoutInflater) -> FragmentListBinding
        get() = FragmentListBinding::inflate


    private var articleAdapter: ArticleAdapter? = null
    private val listFragmentViewModel: ListFragmentViewModel by viewModels()
    override fun getLayoutId() = R.layout.fragment_list

    private var list: ArrayList<Any> = arrayListOf()
    private val sharedViewModel: SharedViewModel by activityViewModels()


    // endregion


    // region LIFECYCLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listFragmentViewModel.setNavigator(this)
    }

    override fun initUserInterface(view: View?) {
        listFragmentViewModel.getArticlesList()
    }
    // endregion

    // region OVERRIDDEN
    override fun setAdapter(list: ArrayList<Any>) {
        this.list = list

        if (articleAdapter != null) {
            val diffCallback = ArticleDiffUtils(articleAdapter!!.getItems(), list)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            val recyclerViewState: Parcelable? =
                binding.recyclerViewArticles.layoutManager?.onSaveInstanceState()

            articleAdapter!!.diffUtilRefresh(diffResult, list)

            recyclerViewState?.let {
                binding.recyclerViewArticles.layoutManager?.onRestoreInstanceState(it)
            }

            binding.recyclerViewArticles.apply {
                if (adapter == null) {
                    adapter = articleAdapter
                }
            }

        } else {
            initRecyclerViewAndAdapter()
            articleAdapter?.setItems(list)
        }
    }

    override fun onItemClick(position: Int, view: View) {
        sharedViewModel.articleUrl((list[position] as ArticleItem).url)
        val action = ListFragmentDirections.actionDetails()
        view.findNavController().navigate(action)

    }

    override fun clearItems() {
        articleAdapter?.clearItems()
    }

    override fun diplayErrorToast(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }

    override fun clearResources() {
        binding.recyclerViewArticles.adapter = null
        articleAdapter = null
    }

    // endregion


    // region UTIL
    private fun initRecyclerViewAndAdapter() {
        articleAdapter = ArticleAdapter(this)
        binding.recyclerViewArticles.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articleAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager(context).orientation
                )
            )
        }
    }

    // endregion
}