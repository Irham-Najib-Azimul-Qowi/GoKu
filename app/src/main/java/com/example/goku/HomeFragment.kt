package com.example.goku

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardMotor = view.findViewById<CardView>(R.id.cardMotor)
        val cardMobil = view.findViewById<CardView>(R.id.cardMobil)
        val cardTiket = view.findViewById<CardView>(R.id.cardTiket)
//        val ivMenu = view.findViewById<ImageView>(R.id.ivMenu)
//        val etSearch = view.findViewById<EditText>(R.id.etSearch)

        cardMotor.setOnClickListener {
            pindahKeInputJemput("motor")
        }

        cardMobil.setOnClickListener {
            pindahKeInputJemput("mobil")
        }


        cardTiket.setOnClickListener {
            pindahKeBus()
        }
//        ivMenu.setOnClickListener {
//            Toast.makeText(context, "kmau memilih laayanan motor", Toast.LENGTH_SHORT).show()
//
//        }
//        etSearch.setOnClickListener {
//            Toast.makeText(context, "kmau memilih laayanan motor", Toast.LENGTH_SHORT).show()
//
//        }


    }

    private fun pindahKeInputJemput(jenis: String) {
//        val intent = Intent(requireContext(), InputJemputActivity::class.java)
        val intent = Intent(requireContext(), InputJemputActivity::class.java)
        intent.putExtra("TIPE_KENDARAAN", jenis)
        startActivity(intent)
    }

    private fun pindahKeBus() {
        val intent = Intent(requireContext(), TiketBusActivity::class.java)
        startActivity(intent)
    }
}

