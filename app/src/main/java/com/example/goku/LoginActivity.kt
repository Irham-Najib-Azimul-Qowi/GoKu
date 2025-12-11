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
        val tvDaftarAkun = findViewById<TextView>(R.id.tvDaftarAkun)

        // --- Logika Tombol Kembali ---
        btnKembali.setOnClickListener {
            finish() // Kembali ke WelcomeScreen
        }

        // --- LOGIKA LUPA PASSWORD (INI YANG DIUBAH) ---
        tvLupaPass.setOnClickListener {
            // Arahkan LANGSUNG ke UbahPasswordActivity
            val intent = Intent(this, UbahPasswordActivity::class.java)
            startActivity(intent)
        }

        tvDaftarAkun.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // --- Logika Tombol Masuk ---
        btnMasuk.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Mohon isi Email dan Password!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}