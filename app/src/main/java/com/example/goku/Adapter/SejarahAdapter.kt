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
import com.example.goku.R
import com.example.goku.TiketBusActivity
import com.example.goku.model.Transaksi

class SejarahAdapter(val listTransaksi: List<Transaksi>) :
    RecyclerView.Adapter<SejarahAdapter.SejarahViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SejarahViewHolder {
        val layout: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sejarah, parent, false)
        return SejarahViewHolder(layout)
    }

    override fun onBindViewHolder(holder: SejarahViewHolder, position: Int) {
        val transaksi: Transaksi = listTransaksi[position]

        // Mapping Data ke Tampilan
        holder.tvTanggalWaktu.text = transaksi.tanggalWaktu
        holder.tvHarga.text = transaksi.harga
        holder.tvRute.text = transaksi.rute
        holder.tvStatus.text = transaksi.status
        holder.ivLayananIcon.setImageResource(transaksi.jenisLayananIconResId)

        // --- LOGIKA TOMBOL "LAGI!" ---
        holder.btnPesanLagi.setOnClickListener {
            val context = holder.view.context

            // 1. Pecah String Rute ("Asal → Tujuan")
            val lokasi = transaksi.rute.split(" → ")
            var lokasiDari = if (lokasi.size >= 2) lokasi[0] else transaksi.rute
            var lokasiKe = if (lokasi.size >= 2) lokasi[1] else ""

            // 2. Cek Jenis Layanan (Apakah Bus atau Ojek?)
            // Sesuaikan R.drawable.bus dengan nama file ikon bus kamu (misal: tiket_bus atau bus)
            if (transaksi.jenisLayananIconResId == R.drawable.bus || transaksi.jenisLayananIconResId == R.drawable.tiket_bus) {

                // === KASUS BUS ===
                val intent = Intent(context, TiketBusActivity::class.java)
                intent.putExtra("LOKASI_DARI_RIWAYAT", lokasiDari)
                intent.putExtra("LOKASI_KE_RIWAYAT", lokasiKe)
                context.startActivity(intent)

            } else {

                // === KASUS OJEK (MOTOR/MOBIL) ===
                val tipeKendaraan = if (transaksi.jenisLayananIconResId == R.drawable.mobil) "mobil" else "motor"

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