// app/src/main/java/com/example/goku/ProfileFragment.kt

package com.example.goku

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

/**
 * ProfileFragment menampilkan informasi akun pengguna.
 * Saat ini hanya memiliki tombol Logout fungsional.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Load layout XML
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // 1. Inisialisasi tombol Logout
        val btnLogout: Button = view.findViewById(R.id.btn_logout)

        // 2. Logika Tombol Logout -> Kembali ke LoginActivity
        btnLogout.setOnClickListener {
            // Tampilkan notifikasi "anda berhasil logout"
            Toast.makeText(requireContext(), "Anda berhasil logout", Toast.LENGTH_LONG).show()

            // Pindah ke LoginActivity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)

            // Tutup semua Activity di back stack agar tidak bisa kembali dengan tombol Back
            requireActivity().finishAffinity()
        }

        return view
    }
}