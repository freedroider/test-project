package com.spacewl.test.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.spacewl.test.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = MainAdapter()
        adapter.onItemRemoveCallback = { position ->
            val item = adapter.getItem(position)
            viewModel.delete(item)
        }
        rvItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainActivity.adapter
        }
        btAdd.setOnClickListener { viewModel.insert() }
        with(viewModel) {
            itemsLD.observe(this@MainActivity, Observer { items ->
                items ?: return@Observer
                adapter.replace(items.toList().map { it.second })
            })
        }
    }
}