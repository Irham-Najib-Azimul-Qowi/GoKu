package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ImageView

/**
 * DalamPerjalananActivity
 * Menampilkan status saat user sedang dalam perjalanan di atas kendaraan.
 * Otomatis pindah ke SampaiTujuanActivity setelah 3 detik (Simulasi).
 */
class DalamPerjalananActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dalam_perjalanan)

        val btnChat = findViewById<ImageView>(R.id.btnChat)
        val btnCall = findViewById<ImageView>(R.id.btnCall)

        // Bisa Chat driver saat diperjalanan (misal info jalan)
        btnChat.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        // Bisa Telepon driver
        btnCall.setOnClickListener {
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
        }

        val tipeKendaraan = intent.getStringExtra("TIPE_KENDARAAN")

        // Simulasi sampai tujuan dalam 3 detik
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SampaiTujuanActivity::class.java)
            intent.putExtra("TIPE_KENDARAAN", tipeKendaraan)
            startActivity(intent)
            finish()
        }, 3000)
    }
}