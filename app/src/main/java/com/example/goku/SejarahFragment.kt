package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView // Tombol Lagi bentuknya TextView
import android.widget.LinearLayout

class SejarahFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sejarah, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inisialisasi Komponen (Pastikan ID sudah ditambahkan di XML)
        val etSearch = view.findViewById<EditText>(R.id.etSearchHistory) // Tambahkan ID ini di XML
        val btnLagi1 = view.findViewById<TextView>(R.id.btnLagi1)       // Tambahkan ID ini di XML
        val btnLagi2 = view.findViewById<TextView>(R.id.btnLagi2)       // Tambahkan ID ini di XML

        val card1 = view.findViewById<LinearLayout>(R.id.cardHistory1)  // Tambahkan ID ini di XML
        val card2 = view.findViewById<LinearLayout>(R.id.cardHistory2)  // Tambahkan ID ini di XML

        // 2. Logika Tombol "Lagi" -> Input Jemput
        val listenerLagi = View.OnClickListener {
            val intent = Intent(requireContext(), InputJemputActivity::class.java)
            // Bisa kirim data tambahan jika perlu, misal tipe kendaraan
            intent.putExtra("TIPE_KENDARAAN", "motor")
            startActivity(intent)
        }

        btnLagi1?.setOnClickListener(listenerLagi)
        btnLagi2?.setOnClickListener(listenerLagi)

        // 3. Logika Search (Sederhana: Filter Visibility)
        etSearch?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase()

                // Cek isi teks hardcode di XML Anda ("Gacoan", "Kampus 1", dll)
                // Jika query cocok, tampilkan. Jika tidak, sembunyikan (GONE).
                if (query.isEmpty()) {
                    card1?.visibility = View.VISIBLE
                    card2?.visibility = View.VISIBLE
                } else {
                    // Logika dummy untuk layout statis
                    // Card 1 dianggap berisi "Gacoan"
                    if ("gacoan".contains(query) || "kampus".contains(query)) {
                        card1?.visibility = View.VISIBLE
                    } else {
                        card1?.visibility = View.GONE
                    }

                    // Card 2 dianggap sama (sesuai XML)
                    if ("gacoan".contains(query) || "kampus".contains(query)) {
                        card2?.visibility = View.VISIBLE
                    } else {
                        card2?.visibility = View.GONE
                    }
                }
            }
        })
    }
}