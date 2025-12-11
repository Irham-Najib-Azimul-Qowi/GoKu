package com.example.goku

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat) // Pastikan nama XML sesuai

        // Tombol Kembali
        val btnBack = findViewById<ImageView>(R.id.btnBackChat)
        btnBack.setOnClickListener {
            finish()
        }

        // Tombol Kirim (Hanya contoh toast)
        val btnSend = findViewById<ImageView>(R.id.btnSend)
        btnSend.setOnClickListener {
            Toast.makeText(this, "Pesan terkirim!", Toast.LENGTH_SHORT).show()
        }
    }
}