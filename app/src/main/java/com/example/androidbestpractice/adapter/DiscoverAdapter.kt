package com.example.androidbestpractice.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbestpractice.entity.DataResult

class DiscoverAdapter : RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder>() {

    class DiscoverViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private var items= arrayListOf<DataResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        TODO("Not yet implemented")

    }

    fun addItem(items:List<DataResult>){
        val discoverDiffUtilCallback=DiscoverDiffUtilCallback(this.items,items)
        val diffResult=DiffUtil.calculateDiff(discoverDiffUtilCallback)
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun getItemCount()=0

    class DiscoverDiffUtilCallback(
        private val oldList: List<DataResult>,
        private val newList: List<DataResult>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id


        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

    }


}