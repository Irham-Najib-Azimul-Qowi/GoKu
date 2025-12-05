package com.example.goku // Sesuaikan package

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // 1. Inisialisasi
        val etNama = findViewById<EditText>(R.id.etNama)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPass = findViewById<EditText>(R.id.etPassword)
        val btnDaftar = findViewById<Button>(R.id.btnDaftarAction)
        val btnKembali = findViewById<Button>(R.id.btnKembali)
        val tvLogin = findViewById<TextView>(R.id.tvLoginLink)

        // 2. Tombol Kembali
        btnKembali.setOnClickListener { finish() }

        // 3. Link "Sudah Punya Akun"
        tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // 4. LOGIKA TOMBOL DAFTAR
        btnDaftar.setOnClickListener {
            val nama = etNama.text.toString()
            val email = etEmail.text.toString()
            val pass = etPass.text.toString()

            if (nama.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                // Simulasi Daftar Berhasil
                Toast.makeText(this, "Pendaftaran Berhasil! Silakan Login.", Toast.LENGTH_LONG).show()

                // Pindah ke Login
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}