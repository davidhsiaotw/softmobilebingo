package com.example.bingo

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bingo.databinding.ButtonBinding

class BingoAdapter : ListAdapter<Bingo, BingoAdapter.BingoViewHolder>(DiffCallback) {

    class BingoViewHolder(private var binding: ButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(bingo: Bingo)  {
                binding.numberMaterialButton.text = bingo.number
            }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Bingo>() {
        override fun areItemsTheSame(oldItem: Bingo, newItem: Bingo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Bingo, newItem: Bingo): Boolean {
            return oldItem.number == newItem.number
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BingoAdapter.BingoViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BingoAdapter.BingoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}