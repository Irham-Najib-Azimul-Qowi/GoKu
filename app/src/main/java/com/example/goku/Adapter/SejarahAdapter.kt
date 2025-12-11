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

        // --- LOGIKA TOMBOL "LAGI!" (INI YANG PENTING) ---
        holder.btnPesanLagi.setOnClickListener {
            val context = holder.view.context

            // 1. Pecah String Rute (misal: "Kampus 1 PNM → Gacoan")
            // Kita pisahkan teksnya berdasarkan tanda panah " → "
            val lokasi = transaksi.rute.split(" → ")

            var lokasiDari = ""
            var lokasiKe = ""

            if (lokasi.size >= 2) {
                lokasiDari = lokasi[0] // Ambil bagian kiri
                lokasiKe = lokasi[1]   // Ambil bagian kanan
            } else {
                lokasiDari = transaksi.rute // Jaga-jaga kalau format beda
            }

            // 2. Cek Tipe Kendaraan (Mobil/Motor) berdasarkan Icon
            val tipeKendaraan = if (transaksi.jenisLayananIconResId == R.drawable.mobil) {
                "mobil"
            } else {
                "motor"
            }

            // 3. Pindah ke InputJemputActivity bawa data
            val intent = Intent(context, InputJemputActivity::class.java)

            // Kirim data agar kolom terisi otomatis
            intent.putExtra("TIPE_KENDARAAN", tipeKendaraan)
            intent.putExtra("LOKASI_DARI_RIWAYAT", lokasiDari)
            intent.putExtra("LOKASI_KE_RIWAYAT", lokasiKe)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listTransaksi.size

    // Inner class ViewHolder (Sesuai ID punya kamu)
    class SejarahViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvTanggalWaktu: TextView = view.findViewById(R.id.tvTanggalWaktu)
        val tvHarga: TextView = view.findViewById(R.id.tvHarga)
        val ivLayananIcon: ImageView = view.findViewById(R.id.ivLayananIcon)
        val tvRute: TextView = view.findViewById(R.id.tvRute)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val btnPesanLagi: AppCompatButton = view.findViewById(R.id.btnPesanLagi)
    }
}