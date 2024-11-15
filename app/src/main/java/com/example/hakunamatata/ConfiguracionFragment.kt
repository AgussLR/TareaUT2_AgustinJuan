package com.example.hakunamatata

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import java.util.Locale

class ConfiguracionFragment : PreferenceFragmentCompat() {

    //private lateinit var binding: ConfiguracionBinding

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.conf, rootKey)

        // Cargar la preferencia de modo oscuro de SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val isDarkModeEnabled = sharedPreferences.getBoolean("dark_mode", false)


        // SWITCHES.
        // ---------

        // Idiomas switch

        val listPreferenceIdiomas = findPreference<ListPreference>("lista_idiomas")
        listPreferenceIdiomas?.setOnPreferenceChangeListener { _, newValue ->
            val idiomaSeleccionado = newValue as String
            when (idiomaSeleccionado) {
                "es" -> {
                    Toast.makeText(requireContext(), "Idioma cambiado a Español", Toast.LENGTH_SHORT).show()
                    setLocale("es")
                }
                "en" -> {
                    Toast.makeText(requireContext(), "Idioma cambiado a Inglés", Toast.LENGTH_SHORT).show()
                    setLocale("en")
                }

            }
            true // Devuelve true para guardar el cambio.
        }



        // Notificaciones switch
        val switchNotificaciones = findPreference<SwitchPreferenceCompat>("switch_notificaciones")
        switchNotificaciones?.setOnPreferenceChangeListener { _, newValue ->
            val isChecked = newValue as Boolean
            if (isChecked) {
                Toast.makeText(requireContext(), "Notificaciones activadas", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Notificaciones desactivadas", Toast.LENGTH_SHORT)
                    .show()
            }
            true
        }

        // Modo oscuro switch
        val switchModo = findPreference<SwitchPreferenceCompat>("switch_modo")
        switchModo?.isChecked = isDarkModeEnabled  // Establecer el estado inicial del switch según la preferencia guardada
        switchModo?.setOnPreferenceChangeListener { _, newValue ->
            val isChecked = newValue as Boolean
            if (isChecked) {
                Toast.makeText(requireContext(), "Modo oscuro activado", Toast.LENGTH_SHORT).show()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                Toast.makeText(requireContext(), "Modo oscuro desactivado", Toast.LENGTH_SHORT).show()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            // Guardar la preferencia de modo oscuro
            sharedPreferences.edit().putBoolean("dark_mode", isChecked).apply()
            true
        }
    }


    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)


        // Forzar reinicio de la actividad
        val refreshIntent = requireActivity().intent
        requireActivity().finish()
        startActivity(refreshIntent)
    }

}
