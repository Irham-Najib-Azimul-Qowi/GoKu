package com.example.goku

import android.annotation.SuppressLint
import android.content.Intent // Tambahkan import Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.AppCompatButton // Tambahkan import untuk AppCompatButton

// Impor model dan adapter dari paket yang sudah Anda buat
import com.example.goku.model.TiketBus
import com.example.goku.adapter.TiketBusAdapter

class PilihTiketActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pilih_tiket_bus)

        // Atur padding untuk system bars
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        // Logika Tombol Kembali (Sesuai dengan ID: btnKembaliTiketBus dari XML)
        val btnBack: AppCompatButton = findViewById(R.id.btnKembaliTiketBus)
        btnBack.setOnClickListener {
            // Logika untuk kembali ke MenuActivity (Beranda)
            val intent = Intent(this, TiketBusActivity::class.java)
            // Bendera untuk mengosongkan tumpukan aktivitas di atas MenuActivity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish() // Tutup PilihTiketActivity
        }

        // 1. Inisialisasi RecyclerView
        val rvTiketBus = findViewById<RecyclerView>(R.id.rvTiketBus)

        rvTiketBus.setOnClickListener {
            val intent = Intent(this, PembayaranBusActivity::class.java)
            startActivity(intent)
        }

        // 2. Buat data dummy Tiket Bus
        val dataTiket = generateDummyBusTickets()

        // 3. Pasang Adapter
        val adapter = TiketBusAdapter(dataTiket)
        rvTiketBus.adapter = adapter

        // 4. Pasang Layout Manager
        rvTiketBus.layoutManager = LinearLayoutManager(this)
    }

    // Fungsi untuk menghasilkan data dummy
    private fun generateDummyBusTickets(): List<TiketBus> {
        return listOf(
            TiketBus(
                id = "B001",
                tanggalWaktu = "23 Agu 2025, 14.30",
                rute = "Madiun → Ponorogo",
                harga = "Rp 15.000",
                operator = "Restu",
                iconResId = R.drawable.bus
            ),
            TiketBus(
                id = "B002",
                tanggalWaktu = "23 Agu 2025, 17:09",
                rute = "Madiun → Balong",
                harga = "Rp 18.000",
                operator = "Sugeng Rahayu",
                iconResId = R.drawable.bus
            ),
            TiketBus(
                id = "B003",
                tanggalWaktu = "24 Agu 2025, 06:00",
                rute = "Ponorogo → Surabaya",
                harga = "Rp 45.000",
                operator = "Eka Cepat",
                iconResId = R.drawable.bus
            )
        )
    }
}