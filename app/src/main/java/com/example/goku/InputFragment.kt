package com.example.goku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.goku.databinding.FragmentInputBinding
import android.text.Editable
import android.text.TextWatcher // Alat untuk memantau perubahan teks di EditText
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope // Alat untuk menjalankan tugas background (Coroutines) sesuai siklus hidup Fragment
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.* // Alat untuk Multitasking (biar aplikasi gak macet saat loading internet)
import org.json.JSONArray // Alat untuk membaca data balasan server
import java.net.URL // Alat untuk koneksi internet

class InputFragment : Fragment(R.layout.fragment_input) {
    // Job pencarian agar server tidak meload terlalu banyak kata
    private var searchJob: Job? = null
    // variabel untuk menyimpan input lokasi dari dropdown
    private var latJemput: Double = 0.0
    private var lonJemput: Double = 0.0
    private var latTujuan: Double = 0.0
    private var lonTujuan: Double = 0.0
    // fungsi penghubung antara input dan logika
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // mengenalkan komponen pada layout dengan logika
        val etDari = view.findViewById<AutoCompleteTextView>(R.id.etDari)
        val etKe = view.findViewById<AutoCompleteTextView>(R.id.etKe)
        val btnKonfirmasi = view.findViewById<MaterialButton>(R.id.btnKonfirmasi)
        val btnBatal = view.findViewById<View>( R.id.btnBatal)
        // logika pencarian kolom dari
        setupSearchInput(etDari) { lat, lon, name ->
            // variabel untuk emnyimpn koordinatnya
            latJemput = lat
            lonJemput = lon
            // membuat laporan ke mapactivity untuk memasang pinmap
            (activity as? MapActivity)?.updateMarker(true, lat, lon, name)
        }
        // memasang logika pencarian tujuan
        setupSearchInput(etKe) { lat, lon, name ->
            // membaut variabel untuk emnyimpan koordinat
            latTujuan = lat
            lonTujuan = lon
            // membuat laporan ke mapactivity untuk memasang pinmap
            (activity as? MapActivity)?.updateMarker(false, lat, lon, name)

        }
        // logika tombol konfirmasi
        btnKonfirmasi.setOnClickListener {
            // cek apakah user sudah menginput keduanya
            if ( latJemput == 0.0 || latTujuan == 0.0 ) {
                Toast.makeText(requireContext(), "Pilih lokasi dari dropdown!", Toast.LENGTH_SHORT).show()
            } else {
            // kalau sudah terisi lapor ke mapactivity untuk mengubah status ke cek harga
                (activity as? MapActivity)?.gantiStatus(RideState.CEK_HARGA)
            }
        }
        // logika tombol batal
        btnBatal.setOnClickListener {
            activity?.finish()
        }


    }
    // fungsi yang mengatur saat user mulai menginput lokasi
    private fun setupSearchInput(input: AutoCompleteTextView, onSelect: (Double, Double, String) -> Unit) {
        input.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                // untuk emnghemat kuota jangan mencari sebelum user mengetik 3 kata
                if (query.length < 3 ) return
//                batalkan pencarian sebelumnya kalo user masih mengetik
                searchJob?.cancel()
                // mulai pencarian baru dengan menunggu 0.8 detik
                searchJob = viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    delay(800)
                    // memanggil fungsi mendownload data
                    val results = fetchNominatim(query)
                    // kembali ke jalur utama untuk mengupdate layar
                    withContext(Dispatchers.Main) {
                        if (isAdded && results.isNotEmpty()) {
                            // menyiapkan daftar nama untuk dropdown
                            val names = results.map { it.name }
                            // memasang adapter pintar
                            val adapter = PlaceAdapter(requireContext(), names)
                            input.setAdapter(adapter)
                            input.showDropDown()
                            // kalau salah satu diklik hilangkan keyboard
                            input.setOnItemClickListener { _, _, pos, _ ->
                                val selected = results[pos]
                                onSelect(selected.lat, selected.lon, selected.name)
                                input.clearFocus()
                            }
                        }
                    }
                }
            }
            // fungsi textwatcher dan biarkan kosong saja
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun fetchNominatim(query: String): List<PlaceResult> {
        return try {
            // URL Rahasia kita (Sudah dikunci ke Madiun pakai Viewbox)
            val url = "https://nominatim.openstreetmap.org/search?" +
                    "q=${query.replace(" ", "+")}" + // Ganti spasi jadi +
                    "&format=json&limit=5" +
                    "&viewbox=111.4900,-7.5850,111.5650,-7.6750" + // Kotak Madiun
                    "&bounded=1" // Wajib di dalam kotak

            val conn = (URL(url).openConnection() as java.net.HttpURLConnection).apply {
                setRequestProperty("User-Agent", "GoKuApp/1.0") // KTP Aplikasi
            }

            // Baca teks balasan server
            val json = conn.inputStream.bufferedReader().readText()
            val array = JSONArray(json)

            // Masukkan ke dalam list
            val list = mutableListOf<PlaceResult>()
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                list.add(PlaceResult(
                    obj.getString("display_name"),
                    obj.getDouble("lat"),
                    obj.getDouble("lon")
                ))
            }
            list
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList() // Kalau error/internet mati, kembalikan list kosong
        }
    }
    // wadah dari data
    data class PlaceResult(val name: String, val lat: Double, val lon: Double)

    class PlaceAdapter(context: android.content.Context, private val items: List<String>) :
            ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, items) {

        override fun getFilter() = object : android.widget.Filter() {
            override fun performFiltering(c: CharSequence?) = FilterResults().apply { values = items; count = items.size }
            override fun publishResults(c: CharSequence?, r: FilterResults?) = notifyDataSetChanged()
        }
    }

}