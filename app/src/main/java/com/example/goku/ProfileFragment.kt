// app/src/main/java/com/example/goku/ProfilFragment.kt

package com.example.goku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

//import android.widget.ImageButton // Import ImageButton

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // 1. Dapatkan referensi ke ImageButton Edit Profil
        val btnEditProfile: Button = view.findViewById(R.id.btn_edit_profile)

        // 2. Tambahkan Listener untuk navigasi
        btnEditProfile.setOnClickListener {
            // Membuat instance dari LupaPasswordFragment
            val lupaPasswordFragment = LupaPasswordFragment()

            // Lakukan Fragment Transaction untuk mengganti Fragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, lupaPasswordFragment) // R.id.fragment_container adalah ID FrameLayout/tempat Fragment di MenuActivity
                .addToBackStack(null) // Penting: Memungkinkan user menekan tombol back untuk kembali ke ProfilFragment
                .commit()
        }

        return view
    }
}