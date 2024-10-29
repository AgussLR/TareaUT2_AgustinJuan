package com.example.hakunamatata.mascota

import androidx.recyclerview.widget.RecyclerView
import com.example.hakunamatata.databinding.ItemsMascotaBinding

class MascotaViewHolder(private val binding: ItemsMascotaBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MascotaData) {
        binding.item = item
        binding.executePendingBindings()
    }
}
