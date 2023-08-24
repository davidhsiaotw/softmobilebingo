package com.example.bingo

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
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
            binding.numberMaterialButton.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
            binding.numberMaterialButton.setOnClickListener {
                // change button's color
                if (ColorStateList.valueOf(Color.GRAY) == it.backgroundTintList) {
                    binding.numberMaterialButton.backgroundTintList =
                        ColorStateList.valueOf(Color.RED)
                } else {
                    binding.numberMaterialButton.backgroundTintList =
                        ColorStateList.valueOf(Color.GRAY)
                }
                // bingo game logic
                clickListener.onClick(bingo)
            }
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

class GridListener(val clickListener: (Bingo) -> Unit) {
    fun onClick(bingo: Bingo) = clickListener(bingo)
}