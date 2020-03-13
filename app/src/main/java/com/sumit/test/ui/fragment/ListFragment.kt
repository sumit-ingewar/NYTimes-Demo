package com.sumit.test.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumit.test.BR
import com.sumit.test.R
import com.sumit.test.base.BaseFragment
import com.sumit.test.base.ItemClickListener
import com.sumit.test.databinding.FragmentListBinding
import com.sumit.test.databinding.FragmentListBindingImpl
import com.sumit.test.ui.fragment.adapter.ArticleAdapter
import com.sumit.test.ui.fragment.item.ArticleItem
import kotlinx.android.synthetic.main.fragment_list.*
import java.lang.Exception


class ListFragment : BaseFragment<FragmentListBinding, ListFragmentViewModel>(),
    ListFragmentNavigator, ItemClickListener {

    private var list: ArrayList<Any> = arrayListOf()

    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var articleAdapter: ArticleAdapter

    override val viewModel = ListFragmentViewModel::class.java

    override fun getBindingVariable() = BR.listViewModel

    override fun getLayoutId() = R.layout.fragment_list


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectedViewModel.setNavigator(this)
        injectedViewModel.callArticleApi()
        list = injectedViewModel.getEmptyList()
    }

    override fun initUserInterface(view: View?) {

        sharedViewModel = activity?.run {
            ViewModelProviders.of(this)
                .get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        articleAdapter = ArticleAdapter(this)
        recyclerViewArticles.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articleAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager(context).orientation
                )
            )
        }

        setAdapter(list)
    }

    override fun setAdapter(list: ArrayList<Any>) {
        this.list = list
        articleAdapter.setItems(list)

    }

    override fun onItemClick(position: Int, view: View) {
        sharedViewModel.articleUrl((list[position] as ArticleItem).url)
        findNavController().navigate(R.id.details_fragment)

    }

    override fun clearItems() {
        articleAdapter.clearItems()
    }

    override fun diplayErrorToast(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }

}