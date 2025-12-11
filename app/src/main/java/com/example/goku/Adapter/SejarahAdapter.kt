package com.example.goku.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.goku.R // Pastikan R diimport dengan benar
import com.example.goku.model.Transaksi

// Mengikuti algoritma dari ProdukAdapter.kt:
// 1. Extend RecyclerView.Adapter dengan inner class ViewHolder
class SejarahAdapter(val listTransaksi: List<Transaksi>) :
    RecyclerView.Adapter<SejarahAdapter.SejarahViewHolder>() { // Ganti Produk -> Sejarah

    // 2. Override onCreateViewHolder (Inflating the new item layout)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SejarahViewHolder {
        val layout: View = LayoutInflater.from(parent.context)
            // Ganti R.layout.item_produk menjadi R.layout.item_sejarah
            .inflate(R.layout.item_sejarah, parent, false)
        return SejarahViewHolder(layout)
    }

    // 3. Override onBindViewHolder (Binding the new data to the new views)
    override fun onBindViewHolder(holder: SejarahViewHolder, position: Int) {
        val transaksi: Transaksi = listTransaksi[position] // Ganti listProduk -> listTransaksi

        // Mapping Data Transaksi ke View Holder
        holder.tvTanggalWaktu.text = transaksi.tanggalWaktu
        holder.tvHarga.text = transaksi.harga
        holder.tvRute.text = transaksi.rute
        holder.tvStatus.text = transaksi.status
        // Menggunakan Icon yang sesuai
        holder.ivLayananIcon.setImageResource(transaksi.jenisLayananIconResId)

        // Contoh: Menambahkan onClickListener pada tombol "Lagi!"
        holder.btnPesanLagi.setOnClickListener {
            // Tulis logika saat tombol "Lagi!" ditekan di sini
            // Contoh: Toast.makeText(holder.view.context, "Pesan lagi rute ${transaksi.rute}", Toast.LENGTH_SHORT).show()
        }
    }

    // 4. Override getItemCount (Returning the size of the new list)
    override fun getItemCount(): Int = listTransaksi.size // Ganti listProduk -> listTransaksi


    // 5. Inner class ViewHolder (Holding references to the new views in item_sejarah.xml)
    class SejarahViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        // Mendefinisikan semua View dari item_sejarah.xml berdasarkan ID yang baru dibuat
        val tvTanggalWaktu: TextView = view.findViewById(R.id.tvTanggalWaktu)
        val tvHarga: TextView = view.findViewById(R.id.tvHarga)
        val ivLayananIcon: ImageView = view.findViewById(R.id.ivLayananIcon)
        val tvRute: TextView = view.findViewById(R.id.tvRute)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val btnPesanLagi: AppCompatButton = view.findViewById(R.id.btnPesanLagi)
    }
}