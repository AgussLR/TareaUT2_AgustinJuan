package com.example.hakunamatata.cita

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.hakunamatata.databinding.ItemsCitaBinding

class RecyclerViewAdapter(private val items: List<CitaData>, private val onItemClicked: (CitaData) -> Unit) : RecyclerView.Adapter<CitaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaViewHolder {
        val binding = ItemsCitaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CitaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CitaViewHolder, position: Int) {
        val currentItem = items[position]

        holder.bind(currentItem)

        // Manejar el evento de clic
        holder.itemView.setOnClickListener {
            onItemClicked(currentItem)
        }

    }

    override fun getItemCount(): Int = items.size
}