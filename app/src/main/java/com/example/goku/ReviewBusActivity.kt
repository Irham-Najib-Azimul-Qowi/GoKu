package com.example.goku

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReviewBusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Pastikan nama file XML layout kamu adalah activity_Review_bus.xml
        setContentView(R.layout.activity_review_bus)

        // 1. Inisialisasi Tombol Kembali
        val btnSubmitReview = findViewById<Button>(R.id.btnSubmitReview)


        // --- LOGIKA KLIK ---

        // Aksi Tombol Kembali (Menutup Activity)
        btnSubmitReview.setOnClickListener {
            finish() // Kembali ke halaman sebelumnya
        }

        // Aksi Tombol Pakai 1
        btnSubmitReview.setOnClickListener {
            Toast.makeText(this, "Review Berhasil Dikirim!", Toast.LENGTH_SHORT).show()
        }

    }
}
