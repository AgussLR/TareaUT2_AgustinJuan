package com.example.hakunamatata

import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
//import com.example.hakunamatata.databinding.ConfiguracionBinding


class ConfiguracionFragment : PreferenceFragmentCompat() {

    //private lateinit var binding: ConfiguracionBinding

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.conf, rootKey)


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
        switchModo?.setOnPreferenceChangeListener { _, newValue ->
            val isChecked = newValue as Boolean
            if (isChecked) {
                Toast.makeText(requireContext(), "Modo oscuro activado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Modo oscuro desactivado", Toast.LENGTH_SHORT)
                    .show()
            }
            true
        }
    }

}
