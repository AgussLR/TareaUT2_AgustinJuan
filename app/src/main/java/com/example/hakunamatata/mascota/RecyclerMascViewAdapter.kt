package com.example.hakunamatata.mascota

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.hakunamatata.cita.CitaData
import com.example.hakunamatata.databinding.ItemsMascotaBinding

class RecyclerMascViewAdapter(private val items: List<MascotaData>, private val onItemClicked: (MascotaData) -> Unit) :
    RecyclerView.Adapter<MascotaViewHolder>() {

    // Método que crea el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaViewHolder {
        val binding =
            ItemsMascotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MascotaViewHolder(binding)
    }

    // Método que enlaza datos al ViewHolder
    override fun onBindViewHolder(holder: MascotaViewHolder, position: Int) {
        val currentItem = items[position]

        holder.bind(currentItem)

        // Manejar el evento de clic
        holder.itemView.setOnClickListener {
            onItemClicked(currentItem)
        }
    }

    override fun getItemCount(): Int = items.size
}