package com.example.goku

import android.annotation.SuppressLint
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
class TiketBusActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var etTanggalBerangkat: EditText // Deklarasi properti untuk EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tiket_bus)

        val btnCariTiket = findViewById<MaterialButton>(R.id.btnCariTiketBus)
        val btnKembali = findViewById<AppCompatButton>(R.id.btnKembaliBus)
        // Inisialisasi EditText Tanggal Berangkat
        etTanggalBerangkat = findViewById(R.id.etTanggalBerangkatBus)

        // 1. Listener untuk tombol "Cari Tiket"
        btnCariTiket.setOnClickListener {
            kePilihTiket()
        }

        // 2. Listener untuk tombol "Kembali" (ke Beranda/MenuActivity)
        btnKembali.setOnClickListener {
            keBeranda()
        }

        // 3. Listener untuk EditText Tanggal Berangkat -> menampilkan DatePicker Fragment
        etTanggalBerangkat.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun kePilihTiket() {
        val intent = Intent(this, PilihTiketActivity::class.java)
        startActivity(intent)
    }

    private fun keBeranda() {
        val intent = Intent(this, MenuActivity::class.java)
        // Menambahkan flag untuk membersihkan stack aktivitas
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish() // Menutup TiketBusActivity
    }

    private fun showDatePickerDialog() {
        // Menampilkan DatePickerFragment
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    // Metode dari DatePickerDialog.OnDateSetListener yang dipanggil saat tanggal dipilih
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        // Buat objek Calendar dan atur tanggal yang dipilih
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        // Format tanggal ke format "dd/MM/yyyy" dan set ke EditText
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        etTanggalBerangkat.setText(dateFormat.format(calendar.time))
    }

    /**
     * Kelas bersarang (Nested Class) untuk DatePickerDialog yang di-host dalam DialogFragment.
     * Ini sesuai dengan panduan Android.
     */
    class DatePickerFragment : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            // Gunakan tanggal saat ini sebagai default untuk dialog
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // Buat instance baru dari DatePickerDialog
            // `activity as DatePickerDialog.OnDateSetListener` akan memastikan host activity
            // (TiketBusActivity) menerima callback `onDateSet`
            return DatePickerDialog(requireContext(), activity as DatePickerDialog.OnDateSetListener, year, month, day)
        }
    }
}