package com.example.goku

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VoucherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Pastikan nama file XML layout kamu adalah activity_voucher.xml
        setContentView(R.layout.activity_voucher)

        // 1. Inisialisasi Tombol Kembali
        val btnKembali = findViewById<Button>(R.id.btnKembaliVoucher)

        // 2. Inisialisasi Tombol Pakai
        val btnPakai1 = findViewById<Button>(R.id.btnPakai1)
        val btnPakai2 = findViewById<Button>(R.id.btnPakai2)

        // --- LOGIKA KLIK ---

        // Aksi Tombol Kembali (Menutup Activity)
        btnKembali.setOnClickListener {
            finish() // Kembali ke halaman sebelumnya
        }

        // Aksi Tombol Pakai 1
        btnPakai1.setOnClickListener {
            Toast.makeText(this, "Voucher 1 berhasil digunakan!", Toast.LENGTH_SHORT).show()
        }

        // Aksi Tombol Pakai 2
        btnPakai2.setOnClickListener {
            Toast.makeText(this, "Voucher 2 berhasil digunakan!", Toast.LENGTH_SHORT).show()
        }
    }
}