package com.example.androidbestpractice.view.calenderView.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbestpractice.R
import com.example.androidbestpractice.databinding.ItemPlaceBinding
import com.example.androidbestpractice.entity.DateItem
import com.example.androidbestpractice.entity.PlaceItem

class PlaceAdapter(val context: Context) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    inner class PlaceViewHolder(val itemPlaceBinding: ItemPlaceBinding) :
        RecyclerView.ViewHolder(itemPlaceBinding.root)

    private val calenderItems = arrayListOf<PlaceItem>()

    fun updateItem(items: List<PlaceItem>) {
        calenderItems.clear()
        calenderItems.addAll(items)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PlaceViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_place,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val data = arrayListOf<DateItem>()
        for (i in 1..30) {
            data.add(DateItem("aaa"))
            holder.itemPlaceBinding.view.addView(DateItemCustomView(context,""){})
        }
    }

    override fun getItemCount() = calenderItems.size


    companion object Builder {
        fun build(recyclerView: RecyclerView) =
            PlaceAdapter(recyclerView.context).apply {
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
                     recyclerView.isNestedScrollingEnabled = false
                recyclerView.adapter = this
            }
    }
}