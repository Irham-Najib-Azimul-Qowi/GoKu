package com.example.goku

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class FragmentHarga : Fragment(R.layout.fragment_harga) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. HUBUNGKAN DENGAN ID DI LAYOUT
        val etJemput = view.findViewById<EditText>(R.id.etJemputReview)
        val etTujuan = view.findViewById<EditText>(R.id.etTujuanReview)
        val layoutDiskon = view.findViewById<LinearLayout>(R.id.layoutDiskon)
        val tvTotalHarga = view.findViewById<TextView>(R.id.tvTotalHarga)
        val btnCariDriver = view.findViewById<MaterialButton>(R.id.btnCariDriver)
        val btnBatal = view.findViewById<View>(R.id.btnBatal)

        // 2. PERINTAH GAMBAR RUTE (PENTING!)
        // Begitu halaman ini muncul, suruh MapActivity menggambar garis biru di peta
        (activity as? MapActivity)?.gambarRuteJalan()

        // 3. LOGIKA HARGA & VOUCHER
        // Ambil data voucher yang dikirim dari Activity
        val isVoucher = arguments?.getBoolean("PAKAI_VOUCHER") ?: false

        // Harga Dasar (Nanti bisa diganti rumus jarak asli)
        var harga = 15000

        if (isVoucher) {
            // Jika pakai voucher: Tampilkan kotak diskon & kurangi harga
            layoutDiskon.visibility = View.VISIBLE
            harga -= 5000
        } else {
            // Jika tidak: Sembunyikan kotak diskon
            layoutDiskon.visibility = View.GONE
        }

        // Tampilkan Harga Akhir
        tvTotalHarga.text = "Rp $harga"

        // (Opsional) Tampilkan nama lokasi di kolom review
        // etJemput.text = "Lokasi Jemput Kamu"
        // etTujuan.text = "Tujuan Kamu"

        // 4. LOGIKA TOMBOL CARI DRIVER
        btnCariDriver.setOnClickListener {
            // Pindah ke status MENCARI DRIVER (Loading)
            (activity as? MapActivity)?.gantiStatus(RideState.MENCARI_DRIVER)
        }

        // 5. LOGIKA TOMBOL BATAL
        btnBatal.setOnClickListener {
            // Kembali ke status INPUT LOKASI
            (activity as? MapActivity)?.gantiStatus(RideState.INPUT_LOKASI)
        }
    }
}