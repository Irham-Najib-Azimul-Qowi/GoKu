package com.example.goku

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * VoucherActivity
 * Halaman untuk melihat dan menggunakan voucher diskon.
 * Simulasi penggunaan dua voucher berbeda.
 */
class VoucherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher)

        // 1. Inisialisasi Komponen UI
        val btnKembali = findViewById<Button>(R.id.btnKembaliVoucher)
        val btnPakai1 = findViewById<Button>(R.id.btnPakai1)
        val btnPakai2 = findViewById<Button>(R.id.btnPakai2)

        // --- LOGIKA KLIK ---

        // Aksi Tombol Kembali (Menutup Activity)
        btnKembali.setOnClickListener {
            finish() // Kembali ke halaman sebelumnya
        }

        // Aksi Tombol Pakai Voucher 1
        btnPakai1.setOnClickListener {
            Toast.makeText(this, "Voucher 1 berhasil digunakan!", Toast.LENGTH_SHORT).show()
            // Di aplikasi nyata, logic pengurangan harga bisa diterapkan di sini
        }

        // Aksi Tombol Pakai Voucher 2
        btnPakai2.setOnClickListener {
            Toast.makeText(this, "Voucher 2 berhasil digunakan!", Toast.LENGTH_SHORT).show()
        }
    }
}