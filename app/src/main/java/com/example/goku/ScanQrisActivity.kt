package com.example.goku

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ScanQrisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qris)

        val btnRateDriver = findViewById<MaterialButton>(R.id.btnRateDriver)

        btnRateDriver.setOnClickListener {
            pindahKeSelesaiDulu()
        }
    }

    private fun pindahKeSelesaiDulu(){
        val intent = Intent(this, SelesaiActivity::class.java)
        startActivity(intent)
    }
}
