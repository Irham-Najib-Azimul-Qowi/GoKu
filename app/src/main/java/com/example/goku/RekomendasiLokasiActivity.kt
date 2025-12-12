package com.example.goku

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * RekomendasiLokasiActivity
 * Halaman pencarian lokasi dengan fitur rekomendasi/saran (simulasi).
 */
class RekomendasiLokasiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rekomendasi_lokasi)

        // 1. Inisialisasi View
        val searchBar = findViewById<LinearLayout>(R.id.layoutSearchBar)
        val hasilRekomendasi = findViewById<LinearLayout>(R.id.layoutHasilRekomendasi)
        val textPencarian = findViewById<TextView>(R.id.tvSearchText)

        // 2. Pasang Listener pada Search Bar
        // Logika: Klik sekali munculkan simulasi hasil, klik lagi sembunyikan.
        searchBar.setOnClickListener {
            if (hasilRekomendasi.visibility == View.GONE) {
                // TAMPILKAN HASIL REKOMENDASI
                hasilRekomendasi.visibility = View.VISIBLE
                textPencarian.text = "Kampus 1 P" // Simulasi user mengetik
            } else {
                // SEMBUNYIKAN
                hasilRekomendasi.visibility = View.GONE
                textPencarian.text = "Cari Lokasi..." // Reset placeholder
            }
        }

        // 3. Aksi jika Hasil Rekomendasi diklik
        hasilRekomendasi.setOnClickListener {
            Toast.makeText(this, "Lokasi Kampus 1 Dipilih!", Toast.LENGTH_SHORT).show()
            hasilRekomendasi.visibility = View.GONE
            textPencarian.text = "Kampus 1 PNM" // Set hasil pilihan
        }
    }
}