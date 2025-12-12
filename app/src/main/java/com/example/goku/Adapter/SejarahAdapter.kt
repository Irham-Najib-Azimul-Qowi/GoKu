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

// Adapter untuk menampilkan daftar riwayat transaksi (Sejarah) dalam RecyclerView.
class SejarahAdapter(val listTransaksi: List<Transaksi>) :
    RecyclerView.Adapter<SejarahAdapter.SejarahViewHolder>() {

    /**
     * Metode ini dipanggil untuk membuat ViewHolder baru.
     * Meng-inflate layout item_sejarah.xml.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SejarahViewHolder {
        val layout: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sejarah, parent, false)
        return SejarahViewHolder(layout)
    }

    /**
     * Metode ini dipanggil untuk mengikat data ke ViewHolder pada posisi tertentu.
     */
    override fun onBindViewHolder(holder: SejarahViewHolder, position: Int) {
        // Mendapatkan objek Transaksi dari daftar
        val transaksi: Transaksi = listTransaksi[position]

        // Mapping Data dari objek Transaksi ke Tampilan (TextViews & ImageView)
        holder.tvTanggalWaktu.text = transaksi.tanggalWaktu
        holder.tvHarga.text = transaksi.harga
        holder.tvRute.text = transaksi.rute
        holder.tvStatus.text = transaksi.status
        // Mengatur ikon layanan berdasarkan ID sumber daya (resource ID)
        holder.ivLayananIcon.setImageResource(transaksi.jenisLayananIconResId)

        // --- LOGIKA TOMBOL "PESAN LAGI!" ---
        holder.btnPesanLagi.setOnClickListener {
            val context = holder.view.context

            // Catatan: Logika memecah string rute ("Asal → Tujuan") telah dihapus sesuai permintaan.
            // Data rute tidak lagi diekstrak dan dikirimkan ke Activity tujuan.

            // 2. Cek Jenis Layanan (Apakah Bus atau Ojek?) berdasarkan resource ID ikon
            if (transaksi.jenisLayananIconResId == R.drawable.bus || transaksi.jenisLayananIconResId == R.drawable.tiket_bus) {

                // === KASUS BUS: Navigasi ke Activity Pilih Tiket ===
                val intent = Intent(context, PilihTiketActivity::class.java)

                // Logika pengiriman data lokasi DARI/KE RIWAYAT dihapus sesuai permintaan.
                // Tidak ada data riwayat rute yang dikirimkan.

                context.startActivity(intent)

            } else {

                // === KASUS OJEK (MOTOR/MOBIL): Navigasi ke Activity Input Jemput ===
                // Menentukan tipe kendaraan (motor/mobil) berdasarkan resource ID ikon
                val tipeKendaraan = if (transaksi.jenisLayananIconResId == R.drawable.mobil) "mobil" else "motor"

                val intent = Intent(context, InputJemputActivity::class.java)
                // Mengirim tipe kendaraan (mobil atau motor)
                intent.putExtra("TIPE_KENDARAAN", tipeKendaraan)
                // Logika pengiriman data lokasi DARI/KE RIWAYAT dihapus sesuai permintaan.
                // Tidak ada data riwayat rute yang dikirimkan.

                context.startActivity(intent)
            }
        }
    }

    /**
     * Mengembalikan jumlah total item dalam daftar transaksi.
     */
    override fun getItemCount(): Int = listTransaksi.size

    /**
     * ViewHolder: Kelas internal untuk menampung referensi ke semua View yang dibutuhkan
     * dari layout item_sejarah.xml
     */
    class SejarahViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvTanggalWaktu: TextView = view.findViewById(R.id.tvTanggalWaktu)
        val tvHarga: TextView = view.findViewById(R.id.tvHarga)
        val ivLayananIcon: ImageView = view.findViewById(R.id.ivLayananIcon)
        val tvRute: TextView = view.findViewById(R.id.tvRute)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val btnPesanLagi: AppCompatButton = view.findViewById(R.id.btnPesanLagi)
    }
}