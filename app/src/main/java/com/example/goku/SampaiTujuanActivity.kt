package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * SampaiTujuanActivity
 * Menampilkan informasi tagihan setelah sampai di tujuan.
 * User dapat melakukan pembayaran via QRIS.
 */
class SampaiTujuanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sampai_tujuan)

        // Tombol Chat & Call (Mungkin driver masih di dekat user)
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

        // Tombol Bayar via QRIS
        val btnPayQris = findViewById<ConstraintLayout>(R.id.btnPayQris)
        
        btnPayQris.setOnClickListener {
            pindahKeScanQris()
        }
    }

    /**
     * Navigasi ke halaman Scan QRIS untuk pembayaran.
     */
    private fun pindahKeScanQris () {
        val intent = Intent(this, ScanQrisActivity::class.java)
        startActivity(intent)
    }

}