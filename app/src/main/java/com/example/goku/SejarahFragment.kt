package com.example.goku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goku.Adapter.SejarahAdapter
import com.example.goku.model.Transaksi

/**
 * SejarahFragment menampilkan riwayat transaksi user.
 * Menggunakan RecyclerView untuk menampilkan list pesanan (Ojek & Bus).
 */
class SejarahFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment_sejarah.xml
        return inflater.inflate(R.layout.fragment_sejarah, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inisialisasi RecyclerView dari layout
        val rvSejarah = view.findViewById<RecyclerView>(R.id.rvSejarah)

        // 2. Generate Data Dummy untuk simulasi history
        // PENTING: Format rute harus "Lokasi A → Lokasi B" (dipisahkan " -> ")
        val dataTransaksi = generateDummyHistory()

        // 3. Pasang Adapter ke RecyclerView
        val adapter = SejarahAdapter(dataTransaksi)
        rvSejarah.adapter = adapter

        // 4. Pasang Layout Manager (Linear ke bawah)
        rvSejarah.layoutManager = LinearLayoutManager(context)
    }

    /**
     * Membuat list dummy transaksi untuk ditampilkan.
     * Mengembalikan list objek Transaksi.
     */
    private fun generateDummyHistory(): List<Transaksi> {
        return listOf(
            Transaksi(
                id = "T001",
                tanggalWaktu = "23 Agu 2025, 17:09",
                harga = "Rp 15.000",
                rute = "Kampus 1 PNM → Gacoan", // Adapter akan memecah string ini
                status = "Perjalanan Selesai",
                jenisLayananIconResId = R.drawable.mobil // Icon mobil
            ),
            Transaksi(
                id = "T002",
                tanggalWaktu = "24 Agu 2025, 09:15",
                harga = "Rp 12.500",
                rute = "Stasiun Kota → Alun-Alun",
                status = "Dibatalkan",
                jenisLayananIconResId = R.drawable.motor // Icon motor
            ),
            Transaksi(
                id = "T003",
                tanggalWaktu = "25 Agu 2025, 20:00",
                harga = "Rp 35.000",
                rute = "Terminal Bus A → Terminal Bus B",
                status = "Tiket Aktif",
                jenisLayananIconResId = R.drawable.bus // Icon bus
            )
        )
    }
}