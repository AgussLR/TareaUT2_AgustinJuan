package com.example.hakunamatata

import androidx.recyclerview.widget.RecyclerView
import com.example.hakunamatata.databinding.ItemsCitaBinding

class CitaViewHolder(private val binding: ItemsCitaBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CitaData) {
        binding.item = item
        binding.executePendingBindings()
    }
}