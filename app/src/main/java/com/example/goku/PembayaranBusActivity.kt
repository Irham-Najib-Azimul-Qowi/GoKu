package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PembayaranBusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran_bus)

        // ID btnRateKami sudah ada di XML Anda
        val btnRate = findViewById<Button>(R.id.btnRateKami)

        btnRate.setOnClickListener {
            val intent = Intent(this, ReviewBusActivity::class.java)
            startActivity(intent)
        }
    }
}