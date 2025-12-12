package com.example.goku.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.goku.InputJemputActivity
import com.example.goku.PilihTiketActivity // Pastikan Import ini ada
import com.example.goku.R
import com.example.goku.model.Transaksi

/**
 * Adapter untuk RecyclerView Riwayat Transaksi.
 * Menghubungkan data List<Transaksi> ke layout item_sejarah.xml.
 */
class SejarahAdapter(val listTransaksi: List<Transaksi>) :
    RecyclerView.Adapter<SejarahAdapter.SejarahViewHolder>() {

    /**
     * Membuat ViewHolder baru.
     * Meng-inflate layout XML item list (item_sejarah.xml).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SejarahViewHolder {
        val layout: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sejarah, parent, false)
        return SejarahViewHolder(layout)
    }

    /**
     * Menghubungkan data ke View pada posisi tertentu.
     * Mengatur teks, gambar, dan logika klik tombol "Pesan Lagi".
     */
    override fun onBindViewHolder(holder: SejarahViewHolder, position: Int) {
        val transaksi: Transaksi = listTransaksi[position]

        // --- 1. SET DATA TAMPILAN ---
        holder.tvTanggalWaktu.text = transaksi.tanggalWaktu
        holder.tvHarga.text = transaksi.harga
        holder.tvRute.text = transaksi.rute
        holder.tvStatus.text = transaksi.status
        holder.ivLayananIcon.setImageResource(transaksi.jenisLayananIconResId)

        // --- 2. LOGIKA TOMBOL "LAGI!" (RE-ORDER) ---
        holder.btnPesanLagi.setOnClickListener {
            val context = holder.view.context

            // a. Parsing String Rute untuk mendapatkan Asal dan Tujuan
            // Format asumsi: "Asal → Tujuan"
            val lokasi = transaksi.rute.split(" → ")
            var lokasiDari = if (lokasi.size >= 2) lokasi[0] else transaksi.rute
            var lokasiKe = if (lokasi.size >= 2) lokasi[1] else ""

            // b. Cek Jenis Layanan berdasarkan Icon
            if (transaksi.jenisLayananIconResId == R.drawable.bus || transaksi.jenisLayananIconResId == R.drawable.tiket_bus) {

                // === JIKA RIWAYAT ADALAH BUS ===
                // Arahkan ke PilihTiketActivity (Bus)
                val intent = Intent(context, PilihTiketActivity::class.java)

                // Kirim data lokasi agar bisa digunakan untuk filter tiket
                intent.putExtra("LOKASI_DARI_RIWAYAT", lokasiDari)
                intent.putExtra("LOKASI_KE_RIWAYAT", lokasiKe)

                context.startActivity(intent)

            } else {

                // === JIKA RIWAYAT ADALAH OJEK/TAKSI ===
                // Tentukan tipe kendaraan (Mobil/Motor)
                val tipeKendaraan = if (transaksi.jenisLayananIconResId == R.drawable.mobil) "mobil" else "motor"

                // Arahkan ke InputJemputActivity (Pemesanan Ojek)
                val intent = Intent(context, InputJemputActivity::class.java)
                intent.putExtra("TIPE_KENDARAAN", tipeKendaraan)
                intent.putExtra("LOKASI_DARI_RIWAYAT", lokasiDari)
                intent.putExtra("LOKASI_KE_RIWAYAT", lokasiKe)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = listTransaksi.size

    class SejarahViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvTanggalWaktu: TextView = view.findViewById(R.id.tvTanggalWaktu)
        val tvHarga: TextView = view.findViewById(R.id.tvHarga)
        val ivLayananIcon: ImageView = view.findViewById(R.id.ivLayananIcon)
        val tvRute: TextView = view.findViewById(R.id.tvRute)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val btnPesanLagi: AppCompatButton = view.findViewById(R.id.btnPesanLagi)
    }
}