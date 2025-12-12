package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

/**
 * MencariDriverActivity
 * Menampilkan animasi loading saat sistem "mencari driver".
 * Otomatis pindah ke DriverMenujuActivity setelah 3 detik.
 */
class MencariDriverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mencari_driver)

        // Ambil tipe kendaraan untuk diteruskan
        val tipeKendaraan = intent.getStringExtra("TIPE_KENDARAAN")

        // Simulasi delay 3 detik seolah-olah sedang mencari driver
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, DriverMenujuActivity::class.java)
            intent.putExtra("TIPE_KENDARAAN", tipeKendaraan)
            startActivity(intent)
            // Tidak finish() agar bisa back? Atau sebaiknya finish() jika flow satu arah.
            // Biasanya finish() agar user tidak balik ke loading screen.
            finish() 
        }, 3000)
    }
}