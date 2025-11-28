package com.example.goku // Sesuaikan package

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
// import com.google.android.material.bottomnavigation.BottomNavigationView (Nanti dibuka saat menu sudah ada)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Definisi Tombol
        val cardMotor = findViewById<CardView>(R.id.cardMotor)
        val cardMobil = findViewById<CardView>(R.id.cardMobil)

        // 2. Logika Klik Motor
        cardMotor.setOnClickListener {
            pindahKeInputJemput("motor")
        }

        // 3. Logika Klik Mobil
        cardMobil.setOnClickListener {
            pindahKeInputJemput("mobil")
        }
    }

    // Fungsi khusus biar kodingan rapi
    private fun pindahKeInputJemput(jenis: String) {
        val intent = Intent(this, InputJemputActivity::class.java)
        intent.putExtra("TIPE_KENDARAAN", jenis) // Membawa data "motor" atau "mobil"
        startActivity(intent)
    }
}