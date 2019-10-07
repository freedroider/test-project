package com.spacewl.test.core

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<M, VH : RecyclerView.ViewHolder>(private val items: MutableList<M> = arrayListOf()) :
    RecyclerView.Adapter<VH>() {
    override fun getItemCount() = items.size

    fun getItem(position: Int) = items[position]

    fun replace(items: List<M>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun first() : M = items[0]

    fun isEmpty() = items.isEmpty()

    fun isNotEmpty() = items.isNotEmpty()
}