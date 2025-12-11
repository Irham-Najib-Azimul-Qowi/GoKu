package com.example.goku.model

data class Transaksi(
    val id: String,
    val tanggalWaktu: String, // Contoh: "23 Agu 2025, 17:09"
    val harga: String,        // Contoh: "Rp 15.000"
    val rute: String,         // Contoh: "Kampus 1 PNM → Gacoan"
    val status: String,       // Contoh: "Perjalanan Selesai"
    val jenisLayananIconResId: Int // Contoh: R.drawable.mobil
)
