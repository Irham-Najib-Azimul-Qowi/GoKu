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

// Mengikuti algoritma dari ProdukAdapter.kt:
class TiketBusAdapter(val listTiket: List<TiketBus>) :
    RecyclerView.Adapter<TiketBusAdapter.TiketBusViewHolder>() {

    // 1. ViewHolder: Menyimpan referensi View dari item_tiket_bus.xml
    inner class TiketBusViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvTanggalWaktu: TextView = view.findViewById(R.id.tvTanggalWaktuBus)
        val tvHarga: TextView = view.findViewById(R.id.tvHargaBus)
        val ivBusIcon: ImageView = view.findViewById(R.id.ivBusIcon)
        val tvRute: TextView = view.findViewById(R.id.tvRuteBus)
        val tvOperator: TextView = view.findViewById(R.id.tvOperatorBus)
        val btnBeli: TextView = view.findViewById(R.id.btnBeliTiket)
    }

    // 2. onCreateViewHolder: Membuat ViewHolder baru dengan layout yang di-inflate
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiketBusViewHolder {
        val layout = LayoutInflater.from(parent.context)
            // Meng-inflate layout item_tiket_bus.xml
            .inflate(R.layout.item_tiket_bus, parent, false)
        return TiketBusViewHolder(layout)
    }

    // 3. onBindViewHolder: Mengikat data dari listTiket ke View Holder
    override fun onBindViewHolder(holder: TiketBusViewHolder, position: Int) {
        val tiket: TiketBus = listTiket[position]

        // Binding data ke komponen UI
        holder.tvTanggalWaktu.text = tiket.tanggalWaktu
        holder.tvHarga.text = tiket.harga
        holder.tvRute.text = tiket.rute
        holder.tvOperator.text = tiket.operator
        holder.ivBusIcon.setImageResource(tiket.iconResId)

        // Menambahkan Click Listener pada tombol "Beli"
        holder.btnBeli.setOnClickListener {
            // Logika navigasi saat tombol Beli ditekan
            val intent = Intent(holder.view.context, PembayaranBusActivity::class.java)
            // Anda bisa mengirim data tiket yang dipilih ke activity berikutnya
            intent.putExtra("TIKET_ID", tiket.id)
            intent.putExtra("TIKET_HARGA", tiket.harga)
            holder.view.context.startActivity(intent)
        }

        // Menambahkan Click Listener pada seluruh card (jika perlu)
        holder.view.setOnClickListener {
            // Logika opsional: Misalnya menampilkan detail tiket
        }
    }

    // 4. getItemCount: Mengembalikan jumlah total item
    override fun getItemCount(): Int = listTiket.size
}