package com.hardbobby.cryptocurrencyapp.presentation.watchlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hardbobby.cryptocurrencyapp.R
import com.hardbobby.cryptocurrencyapp.databinding.ItemWatchListBinding
import com.hardbobby.cryptocurrencyapp.presentation.utils.NumberHelper
import com.hardbobby.cryptocurrencyapp.presentation.utils.NumberHelper.addPrefix
import com.hardbobby.cryptocurrencyapp.presentation.utils.changeTextColor
import com.hardbobby.domain.dto.CryptoModelView

class WatchListAdapter(
    private val onLoadNextPage: () -> Unit
) : ListAdapter<CryptoModelView, RecyclerView.ViewHolder>(CryptoItemDiffCallback()) {
    class CryptoItemDiffCallback : DiffUtil.ItemCallback<CryptoModelView>() {
        override fun areItemsTheSame(
            oldItem: CryptoModelView,
            newItem: CryptoModelView
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CryptoModelView,
            newItem: CryptoModelView
        ): Boolean = oldItem == newItem
    }

    override fun submitList(list: List<CryptoModelView>?) {
        super.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemWatchListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.bind(getItem(position))
        }
        if (position == currentList.size - 1) {
            onLoadNextPage()
        }
    }

    inner class ItemViewHolder(private val binding: ItemWatchListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cryptoModelView: CryptoModelView) {
            with(binding) {
                textViewCoinCode.text = cryptoModelView.name
                textViewCoinName.text = cryptoModelView.fullName
                textViewCoinPriceChange.changeTextColor(predicate1 = {
                    cryptoModelView.changePrice < 0
                }, predicate2 = {
                    cryptoModelView.changePrice > 0
                })
                textViewCoinPrice.text = NumberHelper.formatPrice(cryptoModelView.currentPrice)
                val changePercentage =
                    NumberHelper.formatPriceChanges(cryptoModelView.changePricePercent).addPrefix()
                val changePrice =
                    NumberHelper.formatPriceChanges(cryptoModelView.changePrice).addPrefix()
                textViewCoinPriceChange.text =
                    itemView.context.getString(R.string.changes_info, changePrice, changePercentage)
            }
        }
    }
}