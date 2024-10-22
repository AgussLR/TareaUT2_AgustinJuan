package com.example.hakunamatata


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conf)

        // Configuramos la Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Verifica si es la primera vez que se está cargando la actividad
        if (savedInstanceState == null) {
            // Inicia la transacción del fragmento
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ConfiguracionFragment()
                ) // Carga ConfiguracionFragment en el contenedor
                .commit() // Confirma la transacción
        }

    }

}
