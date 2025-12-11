package com.example.goku

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RekomendasiLokasiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pastikan nama file XML ini benar-benar 'activity_rekomendasi_lokasi'
        setContentView(R.layout.activity_rekomendasi_lokasi)

        // 1. Inisialisasi View (SESUAIKAN DENGAN XML KAMU)
        val searchBar = findViewById<LinearLayout>(R.id.layoutSearchBar)
        val hasilRekomendasi = findViewById<LinearLayout>(R.id.layoutHasilRekomendasi)

        // PERBAIKAN: Gunakan TextView dan ID tvSearchText (Bukan EditText)
        val textPencarian = findViewById<TextView>(R.id.tvSearchText)

        // 2. Pasang Listener pada Search Bar
        searchBar.setOnClickListener {
            // Cek kondisi saat ini
            if (hasilRekomendasi.visibility == View.GONE) {
                // TAMPILKAN
                hasilRekomendasi.visibility = View.VISIBLE
                textPencarian.text = "Kampus 1 P" // Simulasi
            } else {
                // SEMBUNYIKAN
                hasilRekomendasi.visibility = View.GONE
                textPencarian.text = "Cari Lokasi..." // Reset
            }
        }

        // 3. Aksi jika Hasil Rekomendasi diklik
        hasilRekomendasi.setOnClickListener {
            Toast.makeText(this, "Lokasi Kampus 1 Dipilih!", Toast.LENGTH_SHORT).show()
            hasilRekomendasi.visibility = View.GONE
            textPencarian.text = "Kampus 1 PNM"
        }
    }
}