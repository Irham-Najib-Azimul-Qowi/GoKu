// app/src/main/java/com/example/goku/ProfileFragment.kt

package com.example.goku

import android.content.Intent // Tambahkan import ini
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast // Tambahkan import ini

// Hapus import yang tidak digunakan (jika ada, seperti LupaPasswordFragment)

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // 1. Dapatkan referensi ke semua tombol
//        val btnEditProfile: Button = view.findViewById(R.id.btn_edit_profile) //
//        val btnVoucher: Button = view.findViewById(R.id.btn_voucher) //
        val btnLogout: Button = view.findViewById(R.id.btn_logout) //

        // 2. Logika Tombol Edit Profil -> EditProfileActivity
        // Mengganti navigasi Fragment lama dengan Intent ke Activity
//        btnEditProfile.setOnClickListener {
//            val intent = Intent(requireContext(), EditProfileActivity::class.java)
//            startActivity(intent)
//        }

        // 3. Logika Tombol Voucher -> VoucherActivity
//        btnVoucher.setOnClickListener {
//            val intent = Intent(requireContext(), VoucherActivity::class.java)
//            startActivity(intent)
//        }

        // 4. Logika Tombol Logout -> LoginActivity + Toast + clear back stack
        btnLogout.setOnClickListener {
            // Tampilkan notifikasi "anda berhasil logout"
            Toast.makeText(requireContext(), "Anda berhasil logout", Toast.LENGTH_LONG).show()

            // Pindah ke LoginActivity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)

            // Tutup semua Activity di back stack (meniru logout penuh)
            requireActivity().finishAffinity()
        }

        return view
    }
}