package com.example.goku


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

/**
 * SelesaiActivity
 * Halaman akhir setelah pembayaran selesai.
 * Menyediakan tombol untuk kembali ke Halaman Utama.
 */
class SelesaiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selesai)

        val btnBackHome = findViewById<MaterialButton>(R.id.btnBackHome)
        btnBackHome.setOnClickListener {
            pindahKeBeranda()
        }
    }

    /**
     * Kembali ke MenuActivity (Halaman Utama).
     */
    private fun pindahKeBeranda() {
        val intent = Intent(this, MenuActivity::class.java)
        // Hapus back stack agar clean saat kembali ke home
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}