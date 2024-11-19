package com.example.hakunamatata

import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.Manifest

import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.findFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController

import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.hakunamatata.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el NavController desde el NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Configurar menu toogle
        configureToggleMenu()
        // Configurar la navegación
        configureNavigation()

        // Configurar el icono del menú en la ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Modo oscuro aplicar.
        applyDarkModePreference()

        // Verificar y solicitar permisos
        verificarPermisoNotificaciones()

        // Maneja la opción de perfil del header del menú
        val headerView = binding.navView.getHeaderView(0) // Obtiene la vista del encabezado
        val profileImageView: ImageView = headerView.findViewById(R.id.header_image)

        profileImageView.setOnClickListener {
            navController.navigate(R.id.nav_perfil) // Navegar al fragmento de perfil
            binding.drawerLayout.closeDrawers() // Cerrar el menú
        }

    }

    private fun configureToggleMenu() {
        // Configurar el ActionBarDrawerToggle
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun configureNavigation() {
        NavigationUI.setupWithNavController(binding.navView, navController)

        // Manejar la selección de elementos del menú
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_citas -> {
                    navController.navigate(R.id.CitaFragment) // Navegar al fragmento de citas
                }
                R.id.menu_consultas -> {
                    navController.navigate(R.id.nav_consultas) // Navegar al fragmento de consultas
                }
                R.id.menu_contacto -> {
                    navController.navigate(R.id.ContactosFragment) // Navegar al fragmento contactos
                }
                R.id.menu_mascotas -> {
                    navController.navigate(R.id.mascotasFragment) // Navegar al fragmento mascotas
                }
                R.id.menu_calendario -> {
                    navController.navigate(R.id.nav_calendario) // Navegar al fragmento calendario
                }
                R.id.menu_conf -> {
                    navController.navigate(R.id.nav_conf) // Navegar al fragmento configuración
                }
            }
            binding.drawerLayout.closeDrawers() // Cerrar el menú
            true
        }
    }

    private fun applyDarkModePreference() {

        // Obtener el estado del modo oscuro guardado en SharedPreferences.
        val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val isDarkModeEnabled = sharedPreferences.getBoolean("dark_mode", false)

        // Configurar el modo oscuro.
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkModeEnabled) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Manejar clics en el icono del menú
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            NavHostFragment.findNavController(binding.navView.findFragment()),
            binding.drawerLayout
        ) || super.onSupportNavigateUp()
    }

    private fun verificarPermisoNotificaciones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Verificar si ya tenemos el permiso
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Mostrar un diálogo explicativo antes de solicitar el permiso
                mostrarDialogoPermiso()
            }
        }
    }

    private fun mostrarDialogoPermiso() {
        AlertDialog.Builder(this)
            .setTitle("Permiso para notificaciones")
            .setMessage("Esta aplicación necesita tu permiso para enviarte notificaciones importantes.")
            .setPositiveButton("Aceptar") { _, _ ->
                // Solicitar el permiso
                solicitarPermisoNotificaciones()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun solicitarPermisoNotificaciones() {
        val solicitarPermisoLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permiso concedido
                mostrarNotificacionPermisoConcedido()
            } else {
                // Permiso denegado
                mostrarDialogoPermisoDenegado()
            }
        }

        solicitarPermisoLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }

    private fun mostrarNotificacionPermisoConcedido() {
        AlertDialog.Builder(this)
            .setTitle("¡Gracias!")
            .setMessage("Ahora puedes recibir notificaciones de nuestra aplicación.")
            .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun mostrarDialogoPermisoDenegado() {
        AlertDialog.Builder(this)
            .setTitle("Permiso denegado")
            .setMessage("No podrás recibir notificaciones si no otorgas el permiso.")
            .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}