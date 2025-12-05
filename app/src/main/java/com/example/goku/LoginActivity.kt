package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast // Import Toast untuk pesan pop-up
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1. Inisialisasi Komponen
        val etEmail = findViewById<EditText>(R.id.etEmailLogin)
        val etPassword = findViewById<EditText>(R.id.etPassLogin)
        val btnMasuk = findViewById<Button>(R.id.btnMasukAction)
        val btnKembali = findViewById<Button>(R.id.btnKembaliLogin)

        // 2. Logika Tombol Kembali
        btnKembali.setOnClickListener {
            finish()
        }

        // 3. LOGIKA TOMBOL MASUK (PENTING!)
        btnMasuk.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // Cek apakah kosong?
            if (email.isEmpty() || password.isEmpty()) {
                // Munculkan pesan peringatan kecil di bawah
                Toast.makeText(this, "Mohon isi Email dan Password!", Toast.LENGTH_SHORT).show()
            } else {
                // SEMENTARA: Anggap login berhasil, langsung ke Home (MainActivity)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // Hapus semua history login biar pas di-back gak balik ke login lagi
                finishAffinity()
            }
        }
    }
}