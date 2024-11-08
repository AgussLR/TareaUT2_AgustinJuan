package com.example.hakunamatata

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
//import com.example.hakunamatata.databinding.ConfiguracionBinding


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
        val switchIdiomas = findPreference<SwitchPreferenceCompat>("switch_idiomas")
        switchIdiomas?.setOnPreferenceChangeListener { _, newValue ->
            val isChecked = newValue as Boolean
            if (isChecked) {
                Toast.makeText(requireContext(), "Idiomas activados", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Idiomas desactivados", Toast.LENGTH_SHORT).show()
            }
            true // Devuelve true para guardar el estado
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


}
