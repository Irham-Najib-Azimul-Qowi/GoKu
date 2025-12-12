package com.example.goku.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goku.R
import com.example.goku.model.TiketBus // Impor Data Model untuk struktur data tiket
import com.example.goku.PembayaranBusActivity // Asumsi mengarah ke Activity pembayaran

/**
 * Adapter untuk menampilkan daftar Tiket Bus dalam RecyclerView.
 * Menerima list data model TiketBus.
 */
class TiketBusAdapter(val listTiket: List<TiketBus>) :
    RecyclerView.Adapter<TiketBusAdapter.TiketBusViewHolder>() {

    // 1. ViewHolder: Kelas internal untuk menampung dan mengelola referensi View
    // dari layout item_tiket_bus.xml
    inner class TiketBusViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        // Deklarasi dan inisialisasi semua komponen UI
        val tvTanggalWaktu: TextView = view.findViewById(R.id.tvTanggalWaktuBus)
        val tvHarga: TextView = view.findViewById(R.id.tvHargaBus)
        val ivBusIcon: ImageView = view.findViewById(R.id.ivBusIcon)
        val tvRute: TextView = view.findViewById(R.id.tvRuteBus)
        val tvOperator: TextView = view.findViewById(R.id.tvOperatorBus)
        val btnBeli: TextView = view.findViewById(R.id.btnBeliTiket)
    }

    /**
     * Metode yang dipanggil ketika RecyclerView membutuhkan ViewHolder baru.
     * Meng-inflate layout item_tiket_bus.xml.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiketBusViewHolder {
        val layout = LayoutInflater.from(parent.context)
            // Meng-inflate layout item_tiket_bus.xml
            .inflate(R.layout.item_tiket_bus, parent, false)
        return TiketBusViewHolder(layout)
    }

    /**
     * Metode untuk mengikat (bind) data dari listTiket ke komponen UI di ViewHolder
     * pada posisi (position) tertentu.
     */
    override fun onBindViewHolder(holder: TiketBusViewHolder, position: Int) {
        // Mendapatkan objek TiketBus pada posisi saat ini
        val tiket: TiketBus = listTiket[position]

        // Binding data ke komponen UI
        holder.tvTanggalWaktu.text = tiket.tanggalWaktu
        holder.tvHarga.text = tiket.harga
        holder.tvRute.text = tiket.rute
        holder.tvOperator.text = tiket.operator
        holder.ivBusIcon.setImageResource(tiket.iconResId)

        // Menambahkan Click Listener pada tombol "Beli"
        holder.btnBeli.setOnClickListener {
            // Logika navigasi ke Activity PembayaranBusActivity
            val intent = Intent(holder.view.context, PembayaranBusActivity::class.java)

            // Catatan: Logika pengiriman data tiket (seperti ID atau Harga)
            // menggunakan intent.putExtra telah dihapus sesuai permintaan.

            holder.view.context.startActivity(intent)
        }

        // Menambahkan Click Listener pada seluruh card (jika diperlukan untuk detail)
        holder.view.setOnClickListener {
            // Logika opsional: Misalnya menampilkan detail tiket
        }
    }

    /**
     * Mengembalikan jumlah total item yang ada dalam daftar tiket.
     */
    override fun getItemCount(): Int = listTiket.size
}