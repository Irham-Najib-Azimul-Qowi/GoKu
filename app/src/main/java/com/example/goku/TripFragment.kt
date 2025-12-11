package com.example.goku

import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.view.LayoutInflater
import android.view.View
//import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import com.example.goku.databinding.FragmentTripBinding
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TripFragment : Fragment(R.layout.fragment_trip) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ambil data status awal dari activity
        val statusAwal = arguments?.getString("STATUS_TEXT") ?: "Driver Sedang Menuju Lokasimu"
        val tvStatus = view.findViewById<TextView>(R.id.tvStatusTrip)

        tvStatus.text = statusAwal

        viewLifecycleOwner.lifecycleScope.launch {

            if (statusAwal.contains("Menuju")) {
                delay(5000)
                (activity as? MapActivity)?.gantiStatus(RideState.SAMPAI)
            }
        }
    }
}