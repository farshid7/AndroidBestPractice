package com.example.androidbestpractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbestpractice.R
import com.example.androidbestpractice.databinding.ItemBinding
import com.example.androidbestpractice.entity.DataResult

class DiscoverAdapter(
    private val context: Context, val clickDelegate: ((DataResult) -> Unit),
) :
    RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder>() {

    class DiscoverViewHolder(val item: ItemBinding) : RecyclerView.ViewHolder(item.root)

    private var items = arrayListOf<DataResult>()
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DiscoverViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        setAnimation(holder.itemView, position)
        holder.item.dataResult = items[position]
        holder.item.view.setOnClickListener {
            clickDelegate.invoke(items[position])
        }
    }

    fun addItem(items: List<DataResult>) {
        val discoverDiffUtilCallback = DiscoverDiffUtilCallback(this.items, items)
        val diffResult = DiffUtil.calculateDiff(discoverDiffUtilCallback)
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateItem(items: List<DataResult>) {
        val discoverDiffUtilCallback = DiscoverDiffUtilCallback(this.items, items)
        val diffResult = DiffUtil.calculateDiff(discoverDiffUtilCallback)
        this.items = items as ArrayList<DataResult>
        diffResult.dispatchUpdatesTo(this)
    }


    override fun getItemCount() = items.size

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.item_animation_fall_down)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

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

    companion object Builder {
        fun build(recyclerView: RecyclerView, clickDelegate: ((DataResult) -> Unit)) =
            DiscoverAdapter(recyclerView.context, clickDelegate).apply {
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = this

            }
    }

}