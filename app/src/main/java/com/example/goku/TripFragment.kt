package com.example.goku

import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.view.LayoutInflater
import android.view.View
//import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import com.example.goku.databinding.FragmentTripBinding
import android.widget.EditText
import android.widget.TextView
import android.widget.LinearLayout
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TripFragment : Fragment(R.layout.fragment_trip) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvStatus = view.findViewById<TextView>(R.id.tvStatusTrip)
        val layoutPayment = view.findViewById<LinearLayout>(R.id.layoutPayment)
        val btnBayarQris = view.findViewById<androidx.cardview.widget.CardView>(R.id.btnBayarQris)
        
        val etAwal = view.findViewById<EditText>(R.id.etLokasiAwal)
        val etTujuan = view.findViewById<EditText>(R.id.etLokasiTujuan)

        // Ambil status awal & Lokasi
        var currentStatus = arguments?.getString("STATUS_TEXT") ?: "Driver Sedang Menuju Lokasimu"
        val lokasiA = arguments?.getString("LOKASI_A") ?: "Lokasi Jemput"
        val lokasiB = arguments?.getString("LOKASI_B") ?: "Lokasi Tujuan"

        tvStatus.text = currentStatus
        etAwal.setText(lokasiA)
        etTujuan.setText(lokasiB)

        layoutPayment.visibility = View.GONE

        // Listener QRIS
        btnBayarQris.setOnClickListener {
            val intent = android.content.Intent(requireContext(), ScanQrisActivity::class.java)
            startActivity(intent)
        }

        // --- Logika Simulasi via MapActivity ---
        if (currentStatus.contains("Menuju", ignoreCase = true)) {
            // 1. Fase Menjemput
            (activity as? MapActivity)?.startSimulationJemput {
                // Callback saat driver sampai di titik jemput
                view.post {
                    currentStatus = "Driver Sedang Mengantar ke Tujuan"
                    tvStatus.text = currentStatus

                    // 2. Fase Mengantar
                    (activity as? MapActivity)?.startSimulationAntar {
                        // Callback saat sampai tujuan
                        view.post {
                           arrivedAtDestination(tvStatus, layoutPayment)
                        }
                    }
                }
            }
        } else {
            // Jika masuk fragment tapi status sudah lewat (misal langsung fase 2), handle manual/default
             arrivedAtDestination(tvStatus, layoutPayment)
        }
    }

    private fun arrivedAtDestination(tvStatus: TextView, layoutPayment: LinearLayout) {
        tvStatus.text = "Sudah sampai tujuanmu"
        layoutPayment.visibility = View.VISIBLE
    }
}