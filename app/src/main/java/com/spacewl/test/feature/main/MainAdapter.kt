package com.spacewl.test.feature.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spacewl.test.R
import com.spacewl.test.core.BaseAdapter
import com.spacewl.test.entity.ToDo
import com.spacewl.test.utils.ToDoHelper
import com.spacewl.test.utils.extensions.inflate
import kotlinx.android.synthetic.main.item_todo.view.*

class MainAdapter : BaseAdapter<ToDo, MainAdapter.VH>() {
    var onItemRemoveCallback: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(parent.inflate(R.layout.item_todo))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        with(holder.itemView) {
            tvToDoTitle.text = item.title
            tvToDoState.text = ToDoHelper.status(item)
            btToDoDelete.setOnClickListener {
                val adapterPosition = holder.adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemRemoveCallback.invoke(adapterPosition)
                }
            }
        }
    }


    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
}