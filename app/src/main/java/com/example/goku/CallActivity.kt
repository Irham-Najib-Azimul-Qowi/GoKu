package com.example.goku

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        // Tombol Tutup Telepon (Merah)
        val btnEndCall = findViewById<ImageView>(R.id.btnEndCall)

        btnEndCall.setOnClickListener {
            Toast.makeText(this, "Telepon Berakhir", Toast.LENGTH_SHORT).show()
            // Finish akan menutup CallActivity dan otomatis kembali ke ChatActivity (yg ada di belakangnya)
            finish()
        }
    }
}