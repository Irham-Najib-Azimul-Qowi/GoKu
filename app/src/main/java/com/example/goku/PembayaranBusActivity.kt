package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * PembayaranBusActivity
 * Halaman setelah user memilih tiket, untuk melanjutkan ke proses pembayaran.
 * Saat ini berisi tombol kembali ke menu utama.
 */
class PembayaranBusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran_bus)

        // Tombol Kembali / Selesai
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            // Kembali ke Menu Utama
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}