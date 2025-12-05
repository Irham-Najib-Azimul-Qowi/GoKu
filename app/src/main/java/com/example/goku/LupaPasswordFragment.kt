package com.example.goku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton


class LupaPasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lupa_password, container, false)

        // 1. Dapatkan referensi ke ImageButton Edit Profil
        val btnKembali: Button = view.findViewById(R.id.btnKembali)

        // 2. Tambahkan Listener untuk navigasi
        btnKembali.setOnClickListener {
            // Membuat instance dari LupaPasswordFragment
            val ProfileFragment = ProfileFragment()

            // Lakukan Fragment Transaction untuk mengganti Fragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment) // R.id.fragment_container adalah ID FrameLayout/tempat Fragment di MenuActivity
                .addToBackStack(null) // Penting: Memungkinkan user menekan tombol back untuk kembali ke ProfilFragment
                .commit()
        }

        return view
    }
}