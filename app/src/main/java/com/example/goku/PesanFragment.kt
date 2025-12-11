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
        return inflater.inflate(R.layout.fragment_pesan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- BAGIAN ERROR SUDAH DIHAPUS ---
        // (val etSearch = ... itu dihapus karena ID-nya sudah tidak ada di XML)

        // --- TAMBAHAN: Logika Klik Chat ---
        // Kita panggil ID chatItem1 dan chatItem2 yang ada di XML tadi
        val chatItem1 = view.findViewById<LinearLayout>(R.id.chatItem1)
        val chatItem2 = view.findViewById<LinearLayout>(R.id.chatItem2)

        // Kalau Chat 1 diklik -> Masuk ke ChatActivity
        chatItem1.setOnClickListener {
            bukaChatRoom()
        }

        // Kalau Chat 2 diklik -> Masuk ke ChatActivity
        chatItem2.setOnClickListener {
            bukaChatRoom()
        }
    }

    private fun bukaChatRoom() {
        // Pastikan kamu punya ChatActivity.kt
        val intent = Intent(requireContext(), ChatActivity::class.java)
        startActivity(intent)
    }
}