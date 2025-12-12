package com.example.goku // Sesuaikan dengan package kamu

import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * PenentuanJemputActivity
 * Halaman alternatif untuk menentukan tujuan jemputan (Mungkin Work in Progress / Versi Lama).
 */
class PenentuanJemputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // PENTING: Hubungkan dengan XML yang baru dibuat
        setContentView(R.layout.activity_penentuan_jemput)

        // 1. Inisialisasi Komponen
        val btnBatal = findViewById<TextView>(R.id.btnBatal)
        val btnKonfirmasi = findViewById<LinearLayout>(R.id.btnKonfirmasi)
        val etTujuan = findViewById<EditText>(R.id.etTujuan)

        // 2. Aksi Tombol Batal -> Menutup halaman ini (kembali)
        btnBatal.setOnClickListener {
            finish()
        }

        // 3. Aksi Tombol Konfirmasi
        btnKonfirmasi.setOnClickListener {
            val tujuan = etTujuan.text.toString()

            if (tujuan.isNotEmpty()) {
                // Jika tujuan sudah diisi, tampilkan konfirmasi
                Toast.makeText(this, "Pesanan ke $tujuan Dikonfirmasi!", Toast.LENGTH_SHORT).show()
                // Disini nanti bisa lanjut ke halaman berikutnya
            } else {
                // Jika tujuan masih kosong
                Toast.makeText(this, "Silakan isi tujuan dulu", Toast.LENGTH_SHORT).show()
            }
        }
    }
}