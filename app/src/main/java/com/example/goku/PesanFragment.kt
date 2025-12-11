package com.example.goku

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class PesanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout XML
        return inflater.inflate(R.layout.fragment_pesan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Cari ID tombol chat dari XML (chatItem1 dan chatItem2)
        val chatDriverJoko = view.findViewById<LinearLayout>(R.id.chatItem1)
        val chatDriverRiyan = view.findViewById<LinearLayout>(R.id.chatItem2)

        // 2. Aksi Klik untuk Chat 1 (Driver Joko)
        chatDriverJoko.setOnClickListener {
            // Pindah ke ChatActivity
            val intent = Intent(requireContext(), ChatActivity::class.java)

            // (Opsional) Kita titipkan nama driver biar nanti bisa diambil di ChatActivity
            intent.putExtra("NAMA_DRIVER", "Driver Joko")

            startActivity(intent)
        }

        // 3. Aksi Klik untuk Chat 2 (Driver Riyan)
        chatDriverRiyan.setOnClickListener {
            // Pindah ke ChatActivity
            val intent = Intent(requireContext(), ChatActivity::class.java)

            // (Opsional) Kita titipkan nama driver
            intent.putExtra("NAMA_DRIVER", "Driver Riyan")

            startActivity(intent)
        }
    }
}