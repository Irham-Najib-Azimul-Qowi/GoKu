package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class MencariDriverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mencari_driver)

        val tipeKendaraan = intent.getStringExtra("TIPE_KENDARAAN")
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, DriverMenujuActivity::class.java)
            intent.putExtra("TIPE_KENDARAAN", tipeKendaraan)
            startActivity(intent)
        }, 3000)
    }
}