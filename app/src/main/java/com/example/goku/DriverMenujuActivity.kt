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
 * DriverMenujuActivity
 * Menampilkan status bahwa driver sedang menuju lokasi jemput.
 * User bisa Chat atau Telepon driver dari sini.
 * Otomatis pindah ke DalamPerjalananActivity setelah 3 detik (Simulasi).
 */
class DriverMenujuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_menuju)

        // Inisialisasi tombol Chat dan Call
        val btnChat = findViewById<ImageView>(R.id.btnChat)
        val btnCall = findViewById<ImageView>(R.id.btnCall)

        // Logika Tombol Chat -> Buka ChatActivity
        btnChat.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        // Logika Tombol Call -> Buka CallActivity
        btnCall.setOnClickListener {
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
        }


        val tipeKendaraan = intent.getStringExtra("TIPE_KENDARAAN")

        // Simulasi driver sampai dalam 3 detik -> Pindah ke status "Dalam Perjalanan"
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, DalamPerjalananActivity::class.java)
            intent.putExtra("TIPE_KENDARAAN", tipeKendaraan)
            startActivity(intent)
            finish() // Tutup halaman ini agar tidak bisa kembali
        }, 3000)
    }
}