package com.example.goku

// Import Data Model dan Adapter dari paket yang terpisah
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goku.Adapter.SejarahAdapter
import com.example.goku.model.Transaksi

class SejarahFragment : Fragment() {

    // Perubahan: Hanya meng-inflate layout. Semua inisialisasi view di onViewCreated.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Asumsi R.layout.fragment_sejarah sudah diubah untuk memiliki RecyclerView dengan ID: rvSejarah
        return inflater.inflate(R.layout.fragment_sejarah, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- Hapus semua logika search, TextWatcher, dan inisialisasi card/tombol hardcode. ---

        // 1. Inisialisasi RecyclerView (Asumsi ID di XML adalah rvSejarah)
        val rvSejarah = view.findViewById<RecyclerView>(R.id.rvSejarah)

        // 2. Buat Data Dummy untuk Transaksi (Simulasi data dari API/Database)
        val dataTransaksi = generateDummyHistory() // Memanggil fungsi generator data

        // 3. Inisialisasi dan Pasang Adapter
        val adapter = SejarahAdapter(dataTransaksi)

        rvSejarah.adapter = adapter

        // 4. Pasang Layout Manager (Wajib untuk RecyclerView)
        rvSejarah.layoutManager = LinearLayoutManager(context)

        // CATATAN:
        // Logika tombol "Lagi!" kini harus dipindahkan ke dalam 'SejarahAdapter.kt'
        // di dalam metode onBindViewHolder agar setiap item memiliki listener-nya sendiri.
        // Di sini hanya fokus pada tampilan daftar (list view) menggunakan adapter.
    }

    // Fungsi Pembantu untuk membuat data dummy (Mirip dengan ProdukAdapter di Katalog)
    private fun generateDummyHistory(): List<Transaksi> {
        return listOf(
            Transaksi(
                id = "T001",
                tanggalWaktu = "23 Agu 2025, 17:09",
                harga = "Rp 15.000",
                rute = "Kampus 1 PNM → Gacoan",
                status = "Perjalanan Selesai",
                jenisLayananIconResId = R.drawable.mobil // Asumsi resource R.drawable.mobil ada
            ),
            Transaksi(
                id = "T002",
                tanggalWaktu = "24 Agu 2025, 09:15",
                harga = "Rp 12.500",
                rute = "Stasiun Kota → Alun-Alun",
                status = "Dibatalkan",
                jenisLayananIconResId = R.drawable.motor // Asumsi resource R.drawable.motor ada
            ),
            Transaksi(
                id = "T003",
                tanggalWaktu = "25 Agu 2025, 20:00",
                harga = "Rp 35.000",
                rute = "Terminal Bus A → Terminal Bus B",
                status = "Tiket Aktif",
                jenisLayananIconResId = R.drawable.bus // Asumsi resource R.drawable.bus ada
            )
        )
    }
}