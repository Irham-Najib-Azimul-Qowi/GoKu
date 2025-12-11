package com.example.goku

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// Perhatikan: Kita mewarisi BottomSheetDialogFragment, bukan AppCompatActivity
class KeluarRuteFragment : BottomSheetDialogFragment() {

    // 1. Menghubungkan Layout XML ke Fragment ini
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_keluar_rute, container, false)
    }

    // 2. Logika tombol ditaruh di sini (setelah View dibuat)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cari tombol menggunakan 'view.findViewById'
        val btnEmergency = view.findViewById<Button>(R.id.btnEmergency)

        // Aksi Klik
        btnEmergency.setOnClickListener {
            panggilKantor()
        }
    }

    private fun panggilKantor() {
        val nomorKantor = "02112345678" // Nomor tujuan
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$nomorKantor")

        try {
            startActivity(intent)
            // Opsional: Tutup popup setelah tombol ditekan
            // dismiss()
        } catch (e: Exception) {
            // Gunakan requireContext() pengganti 'this' di Fragment
            Toast.makeText(requireContext(), "Gagal membuka telepon", Toast.LENGTH_SHORT).show()
        }
    }
}