package com.example.hakunamatata.cita

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hakunamatata.cita.DatePickerFragment
import com.example.hakunamatata.databinding.CitaAddBinding

class AddCitaFragment: Fragment() {

    private lateinit var binding: CitaAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CitaAddBinding.inflate(inflater,container,false)

        //DatePicker.
        binding.sphorario.setOnClickListener{showDatePickerDialog()}
        return binding.root

    }

    private fun showDatePickerDialog(){
        val datePicker = DatePickerFragment{day,month,year -> onDateSelected(day,month,year)}
        datePicker.show(parentFragmentManager,"datePicker")
    }

    fun onDateSelected(day:Int,month:Int,year:Int){
        binding.sphorario.setText("$day/$month/$year")
    }


}