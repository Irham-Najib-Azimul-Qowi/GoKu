package com.example.goku

/**
 * Model Data untuk Pesan Chat.
 * Digunakan untuk menampilkan pesan dalam ChatActivity.
 */
data class ChatModel(
    val message: String,    // Isi pesan teks
    val time: String,       // Waktu pengiriman pesan
    val isSender: Boolean   // Penanda pengirim: true = saya (kanan), false = orang lain (kiri)
)