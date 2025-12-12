package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * LoginActivity
 * Halaman untuk pengguna masuk ke aplikasi.
 * Menangani validasi input email/password dan navigasi ke Menu Utama, Daftar, atau Lupa Password.
 */
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // --- Inisialisasi Widget UI ---
        val etEmail = findViewById<EditText>(R.id.etEmailLogin)
        val etPassword = findViewById<EditText>(R.id.etPassLogin)
        val btnMasuk = findViewById<Button>(R.id.btnMasukAction)
        val tvLupaPass = findViewById<TextView>(R.id.tvLupaPassword)
        val tvDaftarAkun = findViewById<TextView>(R.id.tvDaftarAkun)

        // --- LOGIKA LUPA PASSWORD ---
        // Mengarahkan pengguna ke halaman ubah password
        tvLupaPass.setOnClickListener {
            val intent = Intent(this, UbahPasswordActivity::class.java)
            startActivity(intent)
        }

        // --- LOGIKA DAFTAR AKUN ---
        // Mengarahkan pengguna ke halaman registrasi
        tvDaftarAkun.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // --- LOGIKA TOMBOL MASUK ---
        btnMasuk.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // Validasi Input
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Mohon isi Email dan Password!", Toast.LENGTH_SHORT).show()
            } else {
                // Simulasi Login Berhasil
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                // Pindah ke MenuActivity (Halaman Utama)
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish() // Menutup LoginActivity agar tidak bisa kembali dengan tombol Back
            }
        }
    }
}