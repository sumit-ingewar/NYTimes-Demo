package com.sumit.test.ui.fragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sumit.test.ui.fragment.item.ArticleItemViewType

class ArticleDiffUtils(
    private var oldList: ArrayList<Any> = arrayListOf(),
    private var newList: ArrayList<Any> = arrayListOf()
) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return ((newList[newItemPosition] as ArticleItemViewType).viewType == (oldList[oldItemPosition] as ArticleItemViewType).viewType)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return ((newList[newItemPosition] as ArticleItemViewType).viewType == (oldList[oldItemPosition] as ArticleItemViewType).viewType)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return ((newList[newItemPosition] as ArticleItemViewType).viewType == (oldList[oldItemPosition] as ArticleItemViewType).viewType)
    }

}