package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // --- Inisialisasi ---
        val etEmail = findViewById<EditText>(R.id.etEmailLogin)
        val etPassword = findViewById<EditText>(R.id.etPassLogin)
        val btnMasuk = findViewById<Button>(R.id.btnMasukAction)
        val btnKembali = findViewById<Button>(R.id.btnKembaliLogin)
        val tvLupaPass = findViewById<TextView>(R.id.tvLupaPassword)

        // --- Logika Tombol Kembali ---
        btnKembali.setOnClickListener {
            finish() // Menutup activity login, kembali ke halaman sebelumnya (Welcome)
        }

        // --- Logika Tombol Lupa Password ---
        tvLupaPass.setOnClickListener {
            // Pastikan kamu punya LupaPasswordActivity
            val intent = Intent(this, LupaPasswordActivity::class.java)
            startActivity(intent)
        }

        // --- LOGIKA TOMBOL MASUK (PENTING!) ---
        btnMasuk.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // 1. Validasi Sederhana: Cek apakah kolom kosong
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Mohon isi Email dan Password!", Toast.LENGTH_SHORT).show()
            } else {
                // 2. Jika isi ada (Login Berhasil), Pindah ke MenuActivity
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)

                // 3. PENTING: Pakai finish() agar saat di Menu user tekan Back,
                // tidak balik ke halaman Login lagi (langsung keluar app)
                finish()
            }
        }
    }
}