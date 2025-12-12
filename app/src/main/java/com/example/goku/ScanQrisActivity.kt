package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

/**
 * ScanQrisActivity
 * Halaman simulasi scan QRIS.
 * Menampilkan loading seolah-olah sedang memproses pembayaran,
 * lalu otomatis pindah ke SelesaiActivity setelah 3 detik.
 */
class ScanQrisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qris)

        // Delay 3 detik simulasi proses scan
        Handler(Looper.getMainLooper()).postDelayed({
            // Pindah ke Halaman Selesai
            val intent = Intent(this, SelesaiActivity::class.java)
            startActivity(intent)
            finish() // Tutup halaman scan agar tidak bisa back
        }, 3000)
    }
}
