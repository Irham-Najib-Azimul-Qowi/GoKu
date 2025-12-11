package com.example.goku

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class KeluarRuteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keluar_rute)

        // Inisialisasi Tombol
        val btnEmergency = findViewById<Button>(R.id.btnEmergency)

        // Aksi ketika tombol ditekan
        btnEmergency.setOnClickListener {
            // Ganti "112" dengan nomor polisi atau admin yang diinginkan
            val nomorDarurat = "112"
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$nomorDarurat")
            startActivity(intent)
        }
    }
}