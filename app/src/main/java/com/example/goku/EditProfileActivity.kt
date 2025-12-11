package com.example.goku

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // ID btnBack sudah ada di XML Anda
        val btnBack = findViewById<TextView>(R.id.btnBack)

        btnBack.setOnClickListener {
            // Menutup activity ini akan otomatis kembali ke halaman sebelumnya (ProfileFragment di MenuActivity)
            finish()
        }

        // Opsional: Tombol Simpan juga bisa menutup activity
        /*
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        btnSimpan.setOnClickListener {
             // Logic simpan data...
             finish()
        }
        */
    }
}