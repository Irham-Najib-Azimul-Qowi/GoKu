package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ScanQrisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qris)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SelesaiActivity::class.java)
            startActivity(intent)
        }, 3000)
    }
}
