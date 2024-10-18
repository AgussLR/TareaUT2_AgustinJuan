package com.example.hakunamatata

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hakunamatata.databinding.ConfiguracionBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ConfiguracionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        supportActionBar?.title ="Configuración"

        supportActionBar?.show()


        binding = ConfiguracionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchIdiomas = binding.switchIdiomas

        switchIdiomas.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                Toast.makeText(this, "Idiomas activados", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Idiomas desactivados", Toast.LENGTH_SHORT).show()
            }
        }

        val switchNotificaciones = binding.switchNotificaciones

        switchNotificaciones.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                Toast.makeText(this, "Notificaciones activadas", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notificaciones desactivadas", Toast.LENGTH_SHORT).show()
            }
        }

        val switchModo = binding.switchModo

        switchModo.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                Toast.makeText(this, "Modo oscuro activado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Modo oscuro desactivado", Toast.LENGTH_SHORT).show()
            }
        }


    }


    private fun loadFragment(fragment: Fragment){

        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.firstFragment,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}