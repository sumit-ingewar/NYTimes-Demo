package com.sumit.test.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T>(
    private val itemClickListener: ItemClickListener? = null
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var items = ArrayList<T>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<T> {

        val viewHolder = createBaseViewHolder(
            parent = parent,
            viewType = viewType
        )

        viewHolder.setItemClickListener(itemClickListener)
        return viewHolder
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<T>,
        position: Int
    ) = holder.bindItem(item = items[position])


    fun setItems(items: ArrayList<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItems(): ArrayList<T> {
        val list = arrayListOf<T>()
        list.addAll(items)
        return list
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    protected abstract fun createBaseViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<T>
}