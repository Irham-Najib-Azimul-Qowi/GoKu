package com.example.goku // Sesuaikan package

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.button.MaterialButton
import java.text.NumberFormat
import java.util.Locale

/**
 * KonfirmasiJemputActivity
 * Halaman konfirmasi pesanan sebelum mencari driver.
 * Menampilkan detail lokasi jemput, tujuan, dan jenis kendaraan.
 */
class KonfirmasiJemputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_jemput)

        // 1. Tangkap Data dari Halaman Sebelumnya
        val lokasiDari = intent.getStringExtra("LOKASI_DARI") ?: "Lokasi Kosong"
        val lokasiKe = intent.getStringExtra("LOKASI_KE") ?: "Lokasi Kosong"
        val tipeKendaraan = intent.getStringExtra("TIPE_KENDARAAN") ?: "motor"

        // 2. Inisialisasi Komponen UI
        val etPickup = findViewById<EditText>(R.id.etPickupPoint)
        val etDest = findViewById<EditText>(R.id.etDestination)
        val btnBatal = findViewById<AppCompatButton>(R.id.btnBatalKonfirmasi)
        val btnCari = findViewById<MaterialButton>(R.id.btnCariDriver)
        // (Opsional) Jika ingin mengubah harga berdasarkan kendaraan, bisa bind TextView harga di sini

        // 3. Set Text pada EditText (Read-only di XML mungkin)
        etPickup.setText(lokasiDari)
        etDest.setText(lokasiKe)

        // 4. Logika Tombol Batal -> Kembali ke InputJemputActivity
        try {
            btnBatal.setOnClickListener {
                finish() // Menutup halaman ini
            }
        } catch (e: Exception) {
            // Handle error jika tombol tidak ditemukan dll
            e.printStackTrace()
        }

        // 5. Logika Tombol Cari Driver -> Melanjutkan ke MencariDriverActivity
        btnCari.setOnClickListener {
            val intent = Intent(this, MencariDriverActivity::class.java)
            // Teruskan data tipe kendaraan
            intent.putExtra("TIPE_KENDARAAN", tipeKendaraan)
            startActivity(intent)
        }
    }
}