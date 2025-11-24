package com.example.goku // Sesuaikan package

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.button.MaterialButton

class InputJemputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_jemput)

        // 1. Ambil Data yang dikirim dari Beranda
        // Kalau kosong, defaultnya "motor"
        val tipeKendaraan = intent.getStringExtra("TIPE_KENDARAAN") ?: "motor"

        // 2. Kenalkan Komponen (Binding)
        val etDari = findViewById<EditText>(R.id.etDari)
        val etKe = findViewById<EditText>(R.id.etKe)
        val btnBatal = findViewById<AppCompatButton>(R.id.btnBatal)
        val btnKonfirmasi = findViewById<MaterialButton>(R.id.btnKonfirmasi)

        // 3. LOGIKA GANTI IKON TOMBOL
        // Cek nama file drawable Anda.
        // Pastikan Anda punya 'motor' dan 'mobil' (atau 'ic_motor'/'ic_mobil') di folder drawable.
        if (tipeKendaraan == "mobil") {
            btnKonfirmasi.setIconResource(R.drawable.mobil)
        } else {
            btnKonfirmasi.setIconResource(R.drawable.motor)
        }

        // 4. Logika Tombol Batal
        btnBatal.setOnClickListener {
            // Kembali ke halaman sebelumnya (Beranda)
            finish()
        }

        // 5. Logika Tombol Konfirmasi
        btnKonfirmasi.setOnClickListener {
            val lokasiDari = etDari.text.toString()
            val lokasiKe = etKe.text.toString()

            // Validasi sederhana: Tidak boleh kosong
            if (lokasiDari.isEmpty() || lokasiKe.isEmpty()) {
                Toast.makeText(this, "Mohon isi lokasi jemput dan tujuan", Toast.LENGTH_SHORT).show()
            } else {
                // Siapkan pengiriman ke Halaman 3
                val intent = Intent(this, KonfirmasiJemputActivity::class.java)

                // Bungkus data untuk dibawa
                intent.putExtra("TIPE_KENDARAAN", tipeKendaraan)
                intent.putExtra("LOKASI_DARI", lokasiDari)
                intent.putExtra("LOKASI_KE", lokasiKe)

                // Berangkat!
                startActivity(intent)
            }
        }
    }
}