package com.example.goku

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import android.content.Intent
import androidx.appcompat.widget.AppCompatButton
import android.widget.EditText
import android.app.DatePickerDialog // Diperlukan
import android.app.Dialog // Diperlukan
import android.widget.DatePicker // Diperlukan
import androidx.fragment.app.DialogFragment // Diperlukan untuk Fragment Date Picker
import java.util.Calendar // Diperlukan
import java.text.SimpleDateFormat // Diperlukan untuk format tanggal
import java.util.Locale // Diperlukan untuk format tanggal


// TiketBusActivity mengimplementasikan OnDateSetListener untuk menerima tanggal dari fragment
/**
 * TiketBusActivity
 * Halaman pencarian tiket bus.
 * User memilih kota asal, tujuan, dan tanggal keberangkatan.
 * Mengimplementasikan OnDateSetListener untuk menangani input tanggal.
 */
class TiketBusActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var etTanggalBerangkat: EditText // Input tanggal keberangkatan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tiket_bus)

        // Inisialisasi komponen UI
        val btnCariTiket = findViewById<MaterialButton>(R.id.btnCariTiket)
        val btnKembali = findViewById<AppCompatButton>(R.id.btnKembali)
        etTanggalBerangkat = findViewById(R.id.etTanggalBerangkat)

        // 1. Logika Tombol "Cari Tiket" -> Pindah ke hasil pencarian (PilihTiketActivity)
        btnCariTiket.setOnClickListener {
            kePilihTiket()
        }

        // 2. Logika Tombol "Kembali" -> Ke Menu Utama
        btnKembali.setOnClickListener {
            keBeranda()
        }

        // 3. Listener pada Input Tanggal -> Buka Dialog Kalender
        etTanggalBerangkat.setOnClickListener {
            showDatePickerDialog()
        }
    }

    /**
     * Navigasi ke halaman PilihTiketActivity.
     * Nantinya bisa membawa data asal, tujuan, dan tanggal.
     */
    private fun kePilihTiket() {
        val intent = Intent(this, PilihTiketActivity::class.java)
        startActivity(intent)
    }

    /**
     * Kembali ke Halaman Utama (MenuActivity).
     * Membersihkan back stack agar tidak numpuk.
     */
    private fun keBeranda() {
        val intent = Intent(this, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    /**
     * Menampilkan DatePickerFragment.
     */
    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    /**
     * Callback saat tanggal dipilih di dialog.
     * Mengubah teks pada EditText sesuai tanggal yang dipilih.
     */
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        // Format tanggal ke "dd/MM/yyyy" (misal 23/08/2025)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        etTanggalBerangkat.setText(dateFormat.format(calendar.time))
    }

    /**
     * Fragment Dialog untuk pemilih tanggal (Date Picker).
     */
    class DatePickerFragment : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            // Gunakan tanggal saat ini sebagai default
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // Kembalikan instance DatePickerDialog
            // 'activity' di-cast ke OnDateSetListener agar activity induk menerima hasilnya
            return DatePickerDialog(requireContext(), activity as DatePickerDialog.OnDateSetListener, year, month, day)
        }
    }
}