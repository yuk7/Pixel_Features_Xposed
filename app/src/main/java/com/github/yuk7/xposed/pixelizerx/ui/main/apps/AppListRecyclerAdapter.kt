package com.github.yuk7.xposed.pixelizerx.ui.main.apps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.yuk7.xposed.pixelizerx.databinding.AppslistRecyclerItemBinding

class AppListRecyclerAdapter(
    private val viewLifecycleOwner: LifecycleOwner,
    private val viewModel: AppListViewModel
) :
    ListAdapter<AppListItem, AppListRecyclerAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: AppslistRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: AppListItem,
            viewLifecycleOwner: LifecycleOwner,
            viewModel: AppListViewModel
        ) {
            binding.run {
                lifecycleOwner = viewLifecycleOwner
                listItem = item
                this.viewModel = viewModel
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AppslistRecyclerItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), viewLifecycleOwner, viewModel)
    }

}

private object DiffCallback : DiffUtil.ItemCallback<AppListItem>() {
    override fun areItemsTheSame(oldItem: AppListItem, newItem: AppListItem): Boolean {
        return oldItem.packageName == newItem.packageName
    }

    override fun areContentsTheSame(oldItem: AppListItem, newItem: AppListItem): Boolean {
        // TODO: fix to compare contents
        return oldItem.packageName == newItem.packageName
    }

}