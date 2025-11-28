package com.example.goku // ⚠️ GANTI INI SESUAI PACKAGE KAMU DI MAIN ACTIVITY

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        // 1. Inisialisasi Tombol Tutup Telepon (Merah)
        val btnEndCall = findViewById<ImageView>(R.id.btnEndCall)

        // 2. Logika saat tombol merah ditekan
        btnEndCall.setOnClickListener {
            // Tampilkan pesan kecil "Telepon Diakhiri"
            Toast.makeText(this, "Telepon Diakhiri", Toast.LENGTH_SHORT).show()

            // Tutup halaman ini (kembali ke halaman sebelumnya)
            finish()
        }
    }
}