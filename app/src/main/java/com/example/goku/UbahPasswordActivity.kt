package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * UbahPasswordActivity
 * Halaman untuk mereset atau mengubah password.
 * Diakses dari halaman Login jika lupa password.
 */
class UbahPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_password)

        val btnKembali = findViewById<Button>(R.id.btnKembali)
        val btnMasuk = findViewById<Button>(R.id.btnMasuk)

        // 1. Tombol Kembali -> Batal dan kembali ke Login
        btnKembali.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            // Membersihkan back stack agar tidak bisa kembali ke halaman ini
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // 2. Tombol Masuk -> Simpan Password Baru dan Login
        btnMasuk.setOnClickListener {
            // Simulasi perubahan password sukses
            Toast.makeText(this, "Password berhasil Diubah", Toast.LENGTH_SHORT).show()

            // Pindah ke Login
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}