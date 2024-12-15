package com.example.hakunamatata.contacto

import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hakunamatata.databinding.ContactosBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ContactosFragment : Fragment() , OnMapReadyCallback {

    private lateinit var binding: ContactosBinding
    companion object {
        fun newInstance() = ContactosFragment()
    }

    private val viewModel: ContactoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContactosBinding.inflate(inflater, container, false)
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latlng = LatLng(36.80959, -2.58300)
        googleMap.addMarker(
            MarkerOptions()
                .position(latlng)
                .title("Marcador")
                .contentDescription("Hakuna Matata")
        )

        googleMap.addCircle(
            CircleOptions()
                .center(latlng)
                .radius(500.0)
                .strokeColor(Color.GREEN)
                .strokeWidth(5f)
                .fillColor(Color.argb(100, 0, 255, 0))
        )


        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15f))
    }
}