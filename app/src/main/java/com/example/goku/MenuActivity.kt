package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * MenuActivity
 * Halaman utama yang menampung Fragment (Home, Chat, History, Profile).
 * Mengatur navigasi Bottom Bar yang kustom (Capsule UI).
 */
class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // --- PENGATURAN KLIK NAVIGASI ---
        
        // 1. TOMBOL HOME (Link Kiri)
        findViewById<ImageView>(R.id.left_home).setOnClickListener {
            updateMenu(1)
        }

        // 2. TOMBOL CHAT (Link Kiri & Kanan) - Tergantung state aktif
        findViewById<ImageView>(R.id.left_chat).setOnClickListener { updateMenu(2) }
        findViewById<ImageView>(R.id.right_chat).setOnClickListener { updateMenu(2) }

        // 3. TOMBOL HISTORY (Link Kiri & Kanan)
        findViewById<ImageView>(R.id.left_history).setOnClickListener { updateMenu(3) }
        findViewById<ImageView>(R.id.right_history).setOnClickListener { updateMenu(3) }

        // 4. TOMBOL PROFILE (Link Kanan)
        findViewById<ImageView>(R.id.right_profile).setOnClickListener {
            updateMenu(4)
        }

        // Set default menu aktif ke Home saat pertama kali dibuka
        updateMenu(1)
    }

    /**
     * Memperbarui UI Navigasi dan Mengganti Fragment.
     * Mengatur visibilitas ikon di kapsul kiri dan kanan berdasarkan menu yang dipilih.
     * @param selectedMenu: 1=Home, 2=Chat, 3=History, 4=Profile
     */
    private fun updateMenu(selectedMenu: Int) {
        // --- 1. INISIALISASI VIEWS ---
        val capsuleLeft = findViewById<androidx.cardview.widget.CardView>(R.id.capsule_left)
        val capsuleRight = findViewById<androidx.cardview.widget.CardView>(R.id.capsule_right)

        // Ikon Aktif (Besar/Berwarna)
        val activeHome = findViewById<ImageView>(R.id.active_home)
        val activeChat = findViewById<ImageView>(R.id.active_chat)
        val activeHistory = findViewById<ImageView>(R.id.active_history)
        val activeProfile = findViewById<ImageView>(R.id.active_profile)

        // Ikon Tidak Aktif (Kecil/Abu)
        val leftHome = findViewById<ImageView>(R.id.left_home)
        val leftChat = findViewById<ImageView>(R.id.left_chat)
        val leftHistory = findViewById<ImageView>(R.id.left_history)

        val rightChat = findViewById<ImageView>(R.id.right_chat)
        val rightHistory = findViewById<ImageView>(R.id.right_history)
        val rightProfile = findViewById<ImageView>(R.id.right_profile)


        // --- 2. RESET SEMUA VIEW MENJADI GONE ---
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

        // --- 3. ATUR LOGIKA TAMPILAN BERDASARKAN PILIHAN ---
        when (selectedMenu) {
            1 -> { // === HOME ACTIVE ===
                gantiFragment(HomeFragment())
                activeHome.visibility = View.VISIBLE
                capsuleLeft.visibility = View.GONE // Kiri Kosong karena Home paling kiri

                // Kanan menampilkan sisa menu
                capsuleRight.visibility = View.VISIBLE
                rightChat.visibility = View.VISIBLE
                rightHistory.visibility = View.VISIBLE
                rightProfile.visibility = View.VISIBLE
            }

            2 -> { // === CHAT ACTIVE ===
                gantiFragment(PesanFragment())
                activeChat.visibility = View.VISIBLE

                // Kiri menampilkan Home
                capsuleLeft.visibility = View.VISIBLE
                leftHome.visibility = View.VISIBLE

                // Kanan menampilkan History & Profile
                capsuleRight.visibility = View.VISIBLE
                rightHistory.visibility = View.VISIBLE
                rightProfile.visibility = View.VISIBLE
            }

            3 -> { // === HISTORY ACTIVE ===
                gantiFragment(SejarahFragment())
                activeHistory.visibility = View.VISIBLE

                // Kiri menampilkan Home & Chat
                capsuleLeft.visibility = View.VISIBLE
                leftHome.visibility = View.VISIBLE
                leftChat.visibility = View.VISIBLE

                // Kanan menampilkan Profile
                capsuleRight.visibility = View.VISIBLE
                rightProfile.visibility = View.VISIBLE
            }

            4 -> { // === PROFILE ACTIVE ===
                gantiFragment(ProfileFragment())
                activeProfile.visibility = View.VISIBLE

                // Kiri menampilkan semua menu selain Profile
                capsuleLeft.visibility = View.VISIBLE
                leftHome.visibility = View.VISIBLE
                leftChat.visibility = View.VISIBLE
                leftHistory.visibility = View.VISIBLE

                capsuleRight.visibility = View.GONE // Kanan Kosong karena Profile paling kanan
            }
        }
    }

    /**
     * Fungsi Helper untuk mengganti Fragment di container.
     */
    private fun gantiFragment(fragmet: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragmet)
            .commit()
    }
}
