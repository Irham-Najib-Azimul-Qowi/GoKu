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

class KonfirmasiJemputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_jemput)

        // 1. Tangkap Data dari Halaman 2
        val lokasiDari = intent.getStringExtra("LOKASI_DARI") ?: "Lokasi Kosong"
        val lokasiKe = intent.getStringExtra("LOKASI_KE") ?: "Lokasi Kosong"
        val tipeKendaraan = intent.getStringExtra("TIPE_KENDARAAN") ?: "motor"

        // 2. Kenalkan Komponen
        val etPickup = findViewById<EditText>(R.id.etPickupPoint)
        val etDest = findViewById<EditText>(R.id.etDestination)
        val btnBatal = findViewById<AppCompatButton>(R.id.btnBatalKonfirmasi)
        val btnCari = findViewById<MaterialButton>(R.id.btnCariDriver)
        // (Opsional) Jika ingin mengubah harga berdasarkan kendaraan, bisa bind TextView harga di sini

        // 3. TEMPELKAN DATA (Populate)
        // Walaupun enabled="false" di XML, kita tetap bisa set teks lewat kodingan.
        etPickup.setText(lokasiDari)
        etDest.setText(lokasiKe)

        try {// 4. Logika Tombol Batal
            // "user harus batal terlebih dahulu" -> Artinya kembali ke halaman sebelumnya
            btnBatal.setOnClickListener {
                finish() // Menutup halaman ini, kembali ke Halaman 2
            }
        } catch (e: Exception) {
            TODO("Not yet implemented")
        }

        // 5. Logika Tombol Cari Driver
        btnCari.setOnClickListener {
            val intent = Intent(this, MencariDriverActivity::class.java)
            // Kita oper lagi tipe kendaraannya biar konsisten sampai akhir
            intent.putExtra("TIPE_KENDARAAN", tipeKendaraan)
            startActivity(intent)
        }
    }
}