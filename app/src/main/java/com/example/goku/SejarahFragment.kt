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

        // 1. Inisialisasi RecyclerView
        val rvSejarah = view.findViewById<RecyclerView>(R.id.rvSejarah)

        // 2. Buat Data Dummy
        // PENTING: Format rute harus "Lokasi A → Lokasi B" (pakai panah)
        // karena di Adapter kita memisahkan teksnya berdasarkan tanda panah itu.
        val dataTransaksi = generateDummyHistory()

        // 3. Pasang Adapter
        val adapter = SejarahAdapter(dataTransaksi)
        rvSejarah.adapter = adapter

        // 4. Pasang Layout Manager
        rvSejarah.layoutManager = LinearLayoutManager(context)
    }

    private fun generateDummyHistory(): List<Transaksi> {
        return listOf(
            Transaksi(
                id = "T001",
                tanggalWaktu = "23 Agu 2025, 17:09",
                harga = "Rp 15.000",
                rute = "Kampus 1 PNM → Gacoan", // Adapter akan memecah ini jadi dua lokasi
                status = "Perjalanan Selesai",
                jenisLayananIconResId = R.drawable.mobil // Pastikan gambar ini ada di drawable
            ),
            Transaksi(
                id = "T002",
                tanggalWaktu = "24 Agu 2025, 09:15",
                harga = "Rp 12.500",
                rute = "Stasiun Kota → Alun-Alun",
                status = "Dibatalkan",
                jenisLayananIconResId = R.drawable.motor
            ),
            Transaksi(
                id = "T003",
                tanggalWaktu = "25 Agu 2025, 20:00",
                harga = "Rp 35.000",
                rute = "Terminal Bus A → Terminal Bus B",
                status = "Tiket Aktif",
                jenisLayananIconResId = R.drawable.bus // Pastikan gambar ini ada
            )
        )
    }
}