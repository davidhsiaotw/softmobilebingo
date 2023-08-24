package com.example.bingo

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bingo.databinding.ButtonBinding

class BingoAdapter(private val clickListener: GridListener) : ListAdapter<Bingo, BingoAdapter
.BingoViewHolder>(DiffCallback) {

    class BingoViewHolder(private var binding: ButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: GridListener, bingo: Bingo) {
            binding.numberMaterialButton.text = bingo.number
            binding.numberMaterialButton.isChecked = bingo.isSelected
            binding.numberMaterialButton.setOnClickListener {
                clickListener.onClick()
            }
            if (bingo.isSelected)
                binding.numberMaterialButton.setBackgroundColor(Color.RED)
            else
                binding.numberMaterialButton.setBackgroundColor(Color.GRAY)
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
        val viewHolder = BingoViewHolder(ButtonBinding.inflate(LayoutInflater.from(parent.context)))
        return viewHolder
    }

    override fun onBindViewHolder(holder: BingoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }
}

class GridListener(val clickListener: () -> Unit) {
    fun onClick() = clickListener()
}