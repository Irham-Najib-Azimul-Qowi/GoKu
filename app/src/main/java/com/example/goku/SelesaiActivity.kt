package com.example.goku


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class SelesaiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selesai)


        val btnBackHome = findViewById<MaterialButton>(R.id.btnBackHome)
        btnBackHome.setOnClickListener {
            pindahKeBeranda()
        }
    }

    private fun pindahKeBeranda() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}