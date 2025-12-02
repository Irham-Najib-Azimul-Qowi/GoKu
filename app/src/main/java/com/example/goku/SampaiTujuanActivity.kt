package com.example.goku

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class SampaiTujuanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sampai_tujuan)


        val btnPayQris = findViewById<ConstraintLayout>(R.id.btnPayQris)
//
        btnPayQris.setOnClickListener {
            pindahKeScanQris()
        }
    }

    private fun pindahKeScanQris () {
        val intent = Intent(this, ScanQrisActivity::class.java)
        startActivity(intent)
    }

}