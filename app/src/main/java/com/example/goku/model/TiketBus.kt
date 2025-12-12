package com.example.goku.model

/**
 * Model Data untuk Tiket Bus.
 * Merepresentasikan satu item tiket bus yang tersedia untuk dipesan.
 */
data class TiketBus(
    val id: String,           // ID unik untuk tiket
    val tanggalWaktu: String, // Tanggal dan waktu keberangkatan (Contoh: "23 Agu 2025, 14.30")
    val rute: String,         // Asal dan tujuan (Contoh: "Madiun → Ponorogo")
    val harga: String,        // Harga tiket dalam format string (Contoh: "Rp 15.000")
    val operator: String,     // Nama operator bus (Contoh: "Restu" atau "Sugeng Rahayu")
    val iconResId: Int        // Resource ID untuk ikon bus (Contoh: R.drawable.bus)
)
