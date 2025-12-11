package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ImageView

class DalamPerjalananActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dalam_perjalanan)

        val btnChat = findViewById<ImageView>(R.id.btnChat)
        val btnCall = findViewById<ImageView>(R.id.btnCall)

        btnChat.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        btnCall.setOnClickListener {
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
        }

        val tipeKendaraan = intent.getStringExtra("TIPE_KENDARAAN")
        findViewById<View>(R.id.btnChat).setOnClickListener {
            Toast.makeText(this, "Fitur Chat belum tersedia", Toast.LENGTH_SHORT).show()

        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SampaiTujuanActivity::class.java)
            intent.putExtra("TIPE_KENDARAAN", tipeKendaraan)
            startActivity(intent)
            finish()
        }, 3000)
    }
}