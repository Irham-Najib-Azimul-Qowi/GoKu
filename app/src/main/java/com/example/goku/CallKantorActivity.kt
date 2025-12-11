package com.example.goku // Sesuaikan dengan package kamu

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CallKantorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_kantor)

        // Inisialisasi Tombol Merah
        val btnEnd = findViewById<ImageView>(R.id.btnEndCallKantor)

        // Logika Tombol End Call
        btnEnd.setOnClickListener {
            Toast.makeText(this, "Panggilan Darurat Diakhiri", Toast.LENGTH_SHORT).show()
            finish() // Menutup layar call dan kembali
        }
    }
}