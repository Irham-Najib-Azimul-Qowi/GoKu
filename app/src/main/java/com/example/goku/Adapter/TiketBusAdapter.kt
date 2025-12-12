package com.example.goku.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goku.R
import com.example.goku.model.TiketBus // Impor Data Model
import com.example.goku.PembayaranBusActivity // Asumsi akan mengarah ke Pembayaran

/**
 * Adapter untuk RecyclerView Tiket Bus.
 * Menampilkan daftar tiket bus yang tersedia untuk dibeli.
 */
class TiketBusAdapter(val listTiket: List<TiketBus>) :
    RecyclerView.Adapter<TiketBusAdapter.TiketBusViewHolder>() {

    // 1. ViewHolder: Menyimpan referensi element UI dari layout item_tiket_bus.xml
    inner class TiketBusViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvTanggalWaktu: TextView = view.findViewById(R.id.tvTanggalWaktuBus)
        val tvHarga: TextView = view.findViewById(R.id.tvHargaBus)
        val ivBusIcon: ImageView = view.findViewById(R.id.ivBusIcon)
        val tvRute: TextView = view.findViewById(R.id.tvRuteBus)
        val tvOperator: TextView = view.findViewById(R.id.tvOperatorBus)
        val btnBeli: TextView = view.findViewById(R.id.btnBeliTiket)
    }

    // 2. onCreateViewHolder: Membuat instance ViewHolder baru dan meng-inflate layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiketBusViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tiket_bus, parent, false)
        return TiketBusViewHolder(layout)
    }

    // 3. onBindViewHolder: Mengisi data tiket ke dalam ViewHolder pada posisi tertentu
    override fun onBindViewHolder(holder: TiketBusViewHolder, position: Int) {
        val tiket: TiketBus = listTiket[position]

        // --- Set Data ke View ---
        holder.tvTanggalWaktu.text = tiket.tanggalWaktu
        holder.tvHarga.text = tiket.harga
        holder.tvRute.text = tiket.rute
        holder.tvOperator.text = tiket.operator
        holder.ivBusIcon.setImageResource(tiket.iconResId)

        // --- Logika Tombol "Beli" ---
        holder.btnBeli.setOnClickListener {
            // Pindah ke halaman Pembayaran (PembayaranBusActivity)
            val intent = Intent(holder.view.context, PembayaranBusActivity::class.java)
            
            // Kirim detail tiket yang dipilih
            intent.putExtra("TIKET_ID", tiket.id)
            intent.putExtra("TIKET_HARGA", tiket.harga)
            
            holder.view.context.startActivity(intent)
        }

        // --- Logika Klik Item (Opsional) ---
        holder.view.setOnClickListener {
            // Bisa ditambahkan untuk melihat detail tiket lebih lengkap
        }
    }

    // 4. getItemCount: Mengembalikan jumlah total item
    override fun getItemCount(): Int = listTiket.size
}