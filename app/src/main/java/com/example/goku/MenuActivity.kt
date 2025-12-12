package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // ======================================================
        // BAGIAN INI YANG BIKIN ERROR TADI (SUDAH DIPERBAIKI)
        // ======================================================

        // 1. TOMBOL HOME (Hanya ada di Kiri)
        findViewById<ImageView>(R.id.left_home).setOnClickListener {
            updateMenu(1)
        }

        // 2. TOMBOL CHAT (Bisa diklik di Kiri atau Kanan)
        findViewById<ImageView>(R.id.left_chat).setOnClickListener { updateMenu(2) }
        findViewById<ImageView>(R.id.right_chat).setOnClickListener { updateMenu(2) }

        // 3. TOMBOL HISTORY (Bisa diklik di Kiri atau Kanan)
        findViewById<ImageView>(R.id.left_history).setOnClickListener { updateMenu(3) }
        findViewById<ImageView>(R.id.right_history).setOnClickListener { updateMenu(3) }

        // 4. TOMBOL PROFILE (Hanya ada di Kanan)
        findViewById<ImageView>(R.id.right_profile).setOnClickListener {
            updateMenu(4)
        }

        // Panggil fungsi ini agar saat pertama buka, Home langsung aktif
        updateMenu(1)
    }

    private fun updateMenu(selectedMenu: Int) {
        // --- 1. REFERENSI VIEWS ---
        val capsuleLeft = findViewById<androidx.cardview.widget.CardView>(R.id.capsule_left)
        val capsuleRight = findViewById<androidx.cardview.widget.CardView>(R.id.capsule_right)

        val activeHome = findViewById<ImageView>(R.id.active_home)
        val activeChat = findViewById<ImageView>(R.id.active_chat)
        val activeHistory = findViewById<ImageView>(R.id.active_history)
        val activeProfile = findViewById<ImageView>(R.id.active_profile)

        val leftHome = findViewById<ImageView>(R.id.left_home)
        val leftChat = findViewById<ImageView>(R.id.left_chat)
        val leftHistory = findViewById<ImageView>(R.id.left_history)

        val rightChat = findViewById<ImageView>(R.id.right_chat)
        val rightHistory = findViewById<ImageView>(R.id.right_history)
        val rightProfile = findViewById<ImageView>(R.id.right_profile)


        // --- 2. RESET TOTAL ---
        activeHome.visibility = View.GONE
        activeChat.visibility = View.GONE
        activeHistory.visibility = View.GONE
        activeProfile.visibility = View.GONE

        leftHome.visibility = View.GONE
        leftChat.visibility = View.GONE
        leftHistory.visibility = View.GONE
        rightChat.visibility = View.GONE
        rightHistory.visibility = View.GONE
        rightProfile.visibility = View.GONE

        // --- 3. ATUR FORMASI ---
        when (selectedMenu) {
            1 -> { // === HOME ACTIVE ===
                gantiFragment(HomeFragment())
                activeHome.visibility = View.VISIBLE
                capsuleLeft.visibility = View.GONE // Kiri Kosong

                capsuleRight.visibility = View.VISIBLE
                rightChat.visibility = View.VISIBLE
                rightHistory.visibility = View.VISIBLE
                rightProfile.visibility = View.VISIBLE
            }

            2 -> { // === CHAT ACTIVE ===
                gantiFragment(PesanFragment())
                activeChat.visibility = View.VISIBLE

                capsuleLeft.visibility = View.VISIBLE
                leftHome.visibility = View.VISIBLE

                capsuleRight.visibility = View.VISIBLE
                rightHistory.visibility = View.VISIBLE
                rightProfile.visibility = View.VISIBLE
            }

            3 -> { // === HISTORY ACTIVE ===
                gantiFragment(SejarahFragment())
                activeHistory.visibility = View.VISIBLE

                capsuleLeft.visibility = View.VISIBLE
                leftHome.visibility = View.VISIBLE
                leftChat.visibility = View.VISIBLE

                capsuleRight.visibility = View.VISIBLE
                rightProfile.visibility = View.VISIBLE
            }

            4 -> { // === PROFILE ACTIVE ===
                gantiFragment(ProfileFragment())
                activeProfile.visibility = View.VISIBLE

                capsuleLeft.visibility = View.VISIBLE
                leftHome.visibility = View.VISIBLE
                leftChat.visibility = View.VISIBLE
                leftHistory.visibility = View.VISIBLE

                capsuleRight.visibility = View.GONE // Kanan Kosong
            }
        }
    }

    private fun gantiFragment(fragmet: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragmet)
            .commit()
    }
}

class PembayaranBusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran_bus)

        // ID btnRateKami sudah ada di XML Anda
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}