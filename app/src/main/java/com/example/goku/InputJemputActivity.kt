package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.button.MaterialButton

/**
 * InputJemputActivity
 * Halaman untuk memasukkan lokasi penjemputan dan tujuan.
 * Menerima tipe kendaraan (motor/mobil) dari HomeFragment.
 */
class InputJemputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_jemput)

        // 1. Tangkap Data dari Halaman Sebelumnya (Home atau Riwayat)
        val tipeKendaraan = intent.getStringExtra("TIPE_KENDARAAN") ?: "motor"

        // Data tambahan dari Riwayat (bisa null kalau dari Home)
        val riwayatDari = intent.getStringExtra("LOKASI_DARI_RIWAYAT")
        val riwayatKe = intent.getStringExtra("LOKASI_KE_RIWAYAT")

        // 2. Inisialisasi Komponen UI
        val etDari = findViewById<EditText>(R.id.etDari)
        val etKe = findViewById<EditText>(R.id.etKe)
        val btnBatal = findViewById<AppCompatButton>(R.id.btnBatal)
        val btnKonfirmasi = findViewById<MaterialButton>(R.id.btnKonfirmasi)

        // --- OPTIONAL: ISI OTOMATIS JIKA ADA DATA RIWAYAT ---
        if (riwayatDari != null) {
            etDari.setText(riwayatDari)
        }
        if (riwayatKe != null) {
            etKe.setText(riwayatKe)
        }
        // ----------------------------------------------------

        // 3. Logika Tampilan (Sesuaikan Ikon & Teks Kendaraan)
        if (tipeKendaraan == "mobil") {
            btnKonfirmasi.setIconResource(R.drawable.mobil)
            btnKonfirmasi.text = "Pesan Mobil"
        } else {
            btnKonfirmasi.setIconResource(R.drawable.motor)
            btnKonfirmasi.text = "Pesan Motor"
        }

        // 4. Logika Tombol Batal -> Kembali ke menu sebelumnya
        btnBatal.setOnClickListener {
            finish()
        }

        // 5. Logika Tombol Konfirmasi -> Lanjut ke Halaman Konfirmasi Jemput
        btnKonfirmasi.setOnClickListener {
            val lokasiDari = etDari.text.toString()
            val lokasiKe = etKe.text.toString()

            // Validasi Input
            if (lokasiDari.isEmpty() || lokasiKe.isEmpty()) {
                Toast.makeText(this, "Mohon isi lokasi jemput dan tujuan", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, KonfirmasiJemputActivity::class.java)
                intent.putExtra("TIPE_KENDARAAN", tipeKendaraan)
                intent.putExtra("LOKASI_DARI", lokasiDari)
                intent.putExtra("LOKASI_KE", lokasiKe)
                startActivity(intent)
            }
        }
    }
}