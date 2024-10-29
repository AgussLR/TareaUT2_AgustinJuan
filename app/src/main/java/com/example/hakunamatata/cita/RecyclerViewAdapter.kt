package com.example.hakunamatata.cita

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.hakunamatata.databinding.ItemsCitaBinding

class RecyclerViewAdapter(private val items: List<CitaData>) : RecyclerView.Adapter<CitaViewHolder>() {

    // Método que crea el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaViewHolder {
        val binding = ItemsCitaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CitaViewHolder(binding)
    }

    // Método que enlaza datos al ViewHolder
    override fun onBindViewHolder(holder: CitaViewHolder, position: Int) {
        val currentItem = items[position]

        holder.bind(currentItem)

        // Manejar el evento de clic
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Clic en: ${currentItem.textMascota}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = items.size
}