package com.example.hakunamatata

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.findFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.hakunamatata.databinding.ActivityMainBinding

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

//        // Configurar la Toolbar como barra de acción
//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)

        // Configurar menu toogle
        configureToggleMenu()
        // Configurar la navegación
        configureNavigation()

        // Configurar el icono del menú en la ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
                    navController.navigate(R.id.nav_citas) // Navegar al fragmento de citas
                }
                R.id.menu_consultas -> {
                    navController.navigate(R.id.nav_consultas) // Navegar al fragmento de consultas
                }
                R.id.menu_contacto -> {
                    navController.navigate(R.id.nav_contacto) // Navegar al fragmento contactos
                }
                R.id.menu_mascotas -> {
                    navController.navigate(R.id.nav_mascotas) // Navegar al fragmento mascotas
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

        // Maneja la opción de perfil del header del menú
        val headerView = binding.navView.getHeaderView(0) // Obtiene la vista del encabezado
        val profileImageView: ImageView = headerView.findViewById(R.id.header_image)

        profileImageView.setOnClickListener {
            navController.navigate(R.id.nav_perfil) // Navegar al fragmento de perfil
            binding.drawerLayout.closeDrawers() // Cerrar el menú
        }

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


}