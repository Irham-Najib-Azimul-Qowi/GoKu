package com.example.goku

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

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
                activeHome.visibility = View.VISIBLE
                capsuleLeft.visibility = View.GONE // Kiri Kosong

                capsuleRight.visibility = View.VISIBLE
                rightChat.visibility = View.VISIBLE
                rightHistory.visibility = View.VISIBLE
                rightProfile.visibility = View.VISIBLE
            }

            2 -> { // === CHAT ACTIVE ===
                activeChat.visibility = View.VISIBLE

                capsuleLeft.visibility = View.VISIBLE
                leftHome.visibility = View.VISIBLE

                capsuleRight.visibility = View.VISIBLE
                rightHistory.visibility = View.VISIBLE
                rightProfile.visibility = View.VISIBLE
            }

            3 -> { // === HISTORY ACTIVE ===
                activeHistory.visibility = View.VISIBLE

                capsuleLeft.visibility = View.VISIBLE
                leftHome.visibility = View.VISIBLE
                leftChat.visibility = View.VISIBLE

                capsuleRight.visibility = View.VISIBLE
                rightProfile.visibility = View.VISIBLE
            }

            4 -> { // === PROFILE ACTIVE ===
                activeProfile.visibility = View.VISIBLE

                capsuleLeft.visibility = View.VISIBLE
                leftHome.visibility = View.VISIBLE
                leftChat.visibility = View.VISIBLE
                leftHistory.visibility = View.VISIBLE

                capsuleRight.visibility = View.GONE // Kanan Kosong
            }
        }
    }
}