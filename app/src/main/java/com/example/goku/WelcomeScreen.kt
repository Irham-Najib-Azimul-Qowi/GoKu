package com.example.goku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button // Import Button

/**
 * WelcomeScreen (Splash Screen)
 * Tampilan awal saat aplikasi dibuka.
 * Menampilkan logo selama 3 detik sebelum pindah ke LoginActivity.
 */
class WelcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen) 

        // Menggunakan Handler untuk menunda perpindahan activity
        Handler(Looper.getMainLooper()).postDelayed({
            // Pindah ke LoginActivity setelah 3 detik
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }, 3000) // Delay 3000 ms (3 detik)

    }
}


