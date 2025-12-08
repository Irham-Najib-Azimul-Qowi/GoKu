package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
// Import kelas Activity yang sudah Anda buat
// Pastikan nama kelas dan paketnya sudah benar!
import com.example.goku.UbahPasswordActivity


class LupaPasswordActivity : AppCompatActivity() {

    // Asumsikan layout XML untuk Activity ini adalah R.layout.activity_lupa_password
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lupa_password)

        // 1. Dapatkan referensi ke tombol
        // Pastikan ID ini sudah ada di activity_lupa_password.xml
        val btnKembali: Button = findViewById(R.id.btnKembali)
        val btnUbahPassword: Button = findViewById(R.id.btnUbahPassword)

        // 2. Logika Tombol Kembali (Mengarah ke Profile Fragment / Host Activity)
        // Ketika LupaPasswordActivity diluncurkan dari MenuActivity (yang menampung ProfileFragment),
        // memanggil finish() akan menutup Activity saat ini dan kembali ke Activity sebelumnya
        // (MenuActivity), sehingga ProfileFragment akan terlihat lagi.
        btnKembali.setOnClickListener {
            // Tutup Activity saat ini untuk kembali ke Activity sebelumnya (MenuActivity/ProfileFragment)
            finish()
        }

        // 3. Logika Tombol Ubah Password (Mengarah ke UbahPasswordActivity)
        // Gunakan Intent untuk berpindah dari Activity ini ke Activity UbahPassword.
        btnUbahPassword.setOnClickListener {
            // Membuat Intent untuk meluncurkan UbahPasswordActivity
            val intent = Intent(this, UbahPasswordActivity::class.java)
            startActivity(intent)

            // Opsional: Jika Anda ingin menutup LupaPasswordActivity dan tidak bisa
            // kembali ke Activity ini dari UbahPasswordActivity, tambahkan:
            // finish()
        }
    }
}