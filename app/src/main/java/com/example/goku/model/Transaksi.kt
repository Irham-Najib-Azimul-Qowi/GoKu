package com.example.goku.model

/**
 * Model Data untuk Riwayat Transaksi.
 * Merepresentasikan satu item riwayat pesanan (baik ojek maupun bus).
 */
data class Transaksi(
    val id: String,                 // ID unik transaksi
    val tanggalWaktu: String,       // Waktu transaksi dilakukan (Contoh: "23 Agu 2025, 17:09")
    val harga: String,              // Total biaya transaksi (Contoh: "Rp 15.000")
    val rute: String,               // Rute perjalanan (Contoh: "Kampus 1 PNM → Gacoan")
    val status: String,             // Status terkini pesanan (Contoh: "Perjalanan Selesai")
    val jenisLayananIconResId: Int  // Ikon layanan yang digunakan (Motor/Mobil/Bus)
)
