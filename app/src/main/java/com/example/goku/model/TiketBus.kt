package com.example.goku.model

data class TiketBus(
    val id: String,
    val tanggalWaktu: String, // Contoh: "23 Agu 2025, 14.30"
    val rute: String,         // Contoh: "Madiun → Ponorogo"
    val harga: String,        // Contoh: "Rp 15.000"
    val operator: String,     // Contoh: "Restu" atau "Sugeng Rahayu"
    val iconResId: Int        // Contoh: R.drawable.bus
)
