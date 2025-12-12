package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

/**
 * HomeFragment adalah halaman utama aplikasi yang menampilkan menu layanan.
 * User dapat memilih layanan Ojek (Motor/Mobil) atau Tiket Bus dari sini.
 */
class HomeFragment : Fragment() {
    
    // Meng-inflate layout fragment_home.xml
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // Menginisialisasi komponen UI dan mengatur aksi klik setelah view dibuat
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi CardView untuk setiap menu
        val cardMotor = view.findViewById<CardView>(R.id.cardMotor)
        val cardMobil = view.findViewById<CardView>(R.id.cardMobil)
        val cardTiket = view.findViewById<CardView>(R.id.cardTiket)

        // Listener untuk menu Motor -> InputJemputActivity
        cardMotor.setOnClickListener {
            pindahKeInputJemput("motor")
        }

        // Listener untuk menu Mobil -> InputJemputActivity
        cardMobil.setOnClickListener {
            pindahKeInputJemput("mobil")
        }

        // Listener untuk menu Tiket Bus -> TiketBusActivity
        cardTiket.setOnClickListener {
            pindahKeBus()
        }
    }

    /**
     * Fungsi helper untuk navigasi ke InputJemputActivity.
     * Mengirimkan parameter jenis kendaraan ("motor" atau "mobil").
     */
    private fun pindahKeInputJemput(jenis: String) {
        val intent = Intent(requireContext(), InputJemputActivity::class.java)
        intent.putExtra("TIPE_KENDARAAN", jenis)
        startActivity(intent)
    }

    /**
     * Fungsi helper untuk navigasi ke TiketBusActivity.
     */
    private fun pindahKeBus() {
        val intent = Intent(requireContext(), TiketBusActivity::class.java)
        startActivity(intent)
    }
}

