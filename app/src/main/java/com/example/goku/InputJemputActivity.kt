package com.example.goku

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
// --- IMPORT BARU UNTUK ROUTING (JALAN RAYA) ---
import org.osmdroid.bonuspack.routing.OSRMRoadManager
import org.osmdroid.bonuspack.routing.RoadManager
// ----------------------------------------------
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import java.net.URL

class InputJemputActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    // Variabel Marker
    private var markerJemput: Marker? = null
    private var markerTujuan: Marker? = null

    // Variabel Jalur (Diganti namanya biar jelas bedanya dengan garis lurus)
    private var roadOverlay: Polyline? = null

    private var latJemput: Double = 0.0
    private var lonJemput: Double = 0.0
    private var latTujuan: Double = 0.0
    private var lonTujuan: Double = 0.0

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_jemput)

        // Konfigurasi OSM
        Configuration.getInstance().load(
            applicationContext,
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        )
        Configuration.getInstance().userAgentValue = packageName

        // Setup Map
        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        mapView.isTilesScaledToDpi = true

        // Fokus Madiun
        val madiunCenter = GeoPoint(-7.6298, 111.5239)
        mapView.controller.setZoom(15.0)
        mapView.controller.setCenter(madiunCenter)

        val madiunBox = BoundingBox(-7.5850, 111.5650, -7.6750, 111.4900)
        mapView.setScrollableAreaLimitDouble(madiunBox)
        mapView.minZoomLevel = 13.0
        mapView.maxZoomLevel = 19.0

        // Komponen UI
        val etDari = findViewById<AutoCompleteTextView>(R.id.etDari)
        val etKe = findViewById<AutoCompleteTextView>(R.id.etKe)
        val btnBatal = findViewById<AppCompatButton>(R.id.btnBatal)
        val btnKonfirmasi = findViewById<MaterialButton>(R.id.btnKonfirmasi)

        val tipeKendaraan = intent.getStringExtra("TIPE_KENDARAAN") ?: "motor"

        if (tipeKendaraan == "mobil") {
            btnKonfirmasi.setIconResource(R.drawable.mobil)
            btnKonfirmasi.text = "Pesan Mobil"
        } else {
            btnKonfirmasi.setIconResource(R.drawable.motor)
            btnKonfirmasi.text = "Pesan Motor"
        }

        // --- SETUP AUTOCOMPLETE ---

        // 1. Kolom Jemput
        setupAutocomplete(etDari) { lat, lon, namaTempat ->
            latJemput = lat
            lonJemput = lon

            // Pasang Marker User
            tambahMarker(lat, lon, "Jemput: $namaTempat", R.drawable.tanda_jemput, isJemput = true)

            // Cari Rute Jalan Raya
            getRouteAndDraw()
        }

        // 2. Kolom Tujuan
        setupAutocomplete(etKe) { lat, lon, namaTempat ->
            latTujuan = lat
            lonTujuan = lon

            // Pasang Marker Tujuan
            tambahMarker(lat, lon, "Tujuan: $namaTempat", R.drawable.pinmap, isJemput = false)

            // Cari Rute Jalan Raya
            getRouteAndDraw()
        }

        btnBatal.setOnClickListener { finish() }

        btnKonfirmasi.setOnClickListener {
            val lokasiDari = etDari.text.toString()
            val lokasiKe = etKe.text.toString()

            if (lokasiDari.isEmpty() || lokasiKe.isEmpty()) {
                Toast.makeText(this, "Mohon isi lokasi jemput dan tujuan", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, KonfirmasiJemputActivity::class.java)
                intent.putExtra("TIPE_KENDARAAN", tipeKendaraan)
                intent.putExtra("LOKASI_DARI", lokasiDari)
                intent.putExtra("LOKASI_KE", lokasiKe)
                intent.putExtra("LAT_DARI", latJemput)
                intent.putExtra("LON_DARI", lonJemput)
                intent.putExtra("LAT_KE", latTujuan)
                intent.putExtra("LON_KE", lonTujuan)
                startActivity(intent)
            }
        }
    }

    // --- FUNGSI BARU: MENGHITUNG RUTE JALAN RAYA (ROUTING) ---
    private fun getRouteAndDraw() {
        // Hanya jalankan jika KEDUA titik sudah terisi
        if (latJemput != 0.0 && latTujuan != 0.0) {

            // Gunakan Coroutine (Background Thread) karena butuh internet
            lifecycleScope.launch(Dispatchers.IO) {

                // 1. Siapkan Navigator (OSRM Road Manager)
                val roadManager = OSRMRoadManager(this@InputJemputActivity, "GoKuApp/1.0")

                // 2. Tentukan Titik Awal & Akhir
                val waypoints = ArrayList<GeoPoint>()
                waypoints.add(GeoPoint(latJemput, lonJemput))
                waypoints.add(GeoPoint(latTujuan, lonTujuan))

                // 3. Minta Rute ke Server
                val road = roadManager.getRoad(waypoints)

                // 4. Kembali ke UI Thread untuk menggambar
                withContext(Dispatchers.Main) {
                    if (road.mStatus != 0) {
                        Toast.makeText(this@InputJemputActivity, "Gagal memuat rute jalan", Toast.LENGTH_SHORT).show()
                    } else {
                        // Hapus jalur lama jika ada
                        if (roadOverlay != null) {
                            mapView.overlays.remove(roadOverlay)
                        }

                        // Buat Garis Baru dari hasil Road
                        roadOverlay = RoadManager.buildRoadOverlay(road)
                        roadOverlay?.outlinePaint?.color = Color.BLUE // Warna Biru
                        roadOverlay?.outlinePaint?.strokeWidth = 15f  // Ketebalan

                        // Tambahkan ke Peta
                        mapView.overlays.add(roadOverlay)
                        mapView.invalidate()

                        // Zoom otomatis agar seluruh rute terlihat
                        mapView.zoomToBoundingBox(road.mBoundingBox, true, 100)

                        // (Opsional) Tampilkan info jarak
                        // val jarakKm = road.mLength
                        // Toast.makeText(this@InputJemputActivity, "Jarak: ${"%.1f".format(jarakKm)} km", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    // --- FUNGSI MARKER ---
    private fun tambahMarker(lat: Double, lon: Double, judul: String, iconResId: Int, isJemput: Boolean) {
        val point = GeoPoint(lat, lon)

        if (isJemput) {
            if (markerJemput != null) mapView.overlays.remove(markerJemput)
            markerJemput = Marker(mapView)
            markerJemput?.position = point
            markerJemput?.title = judul
            markerJemput?.icon = ContextCompat.getDrawable(this, iconResId)
            markerJemput?.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            mapView.overlays.add(markerJemput)
        } else {
            if (markerTujuan != null) mapView.overlays.remove(markerTujuan)
            markerTujuan = Marker(mapView)
            markerTujuan?.position = point
            markerTujuan?.title = judul
            markerTujuan?.icon = ContextCompat.getDrawable(this, iconResId)
            markerTujuan?.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            mapView.overlays.add(markerTujuan)
        }

        mapView.invalidate()

        // Jika rute belum terbentuk (baru 1 titik), zoom ke titik itu
        if (latTujuan == 0.0 || latJemput == 0.0) {
            mapView.controller.animateTo(point)
            mapView.controller.setZoom(18.0)
        }
    }

    // --- AUTOCOMPLETE (PlaceAdapter & TextWatcher) ---
    private fun setupAutocomplete(
        autoCompleteTextView: AutoCompleteTextView,
        onLocationSelected: (Double, Double, String) -> Unit
    ) {
        var currentSuggestions = listOf<Map<String, String>>()

        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                if (query.length < 3) return

                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    delay(800)
                    val hasil = cariLokasiDiMadiun(query)
                    if (hasil.isNotEmpty()) {
                        currentSuggestions = hasil
                        val namaTempat = hasil.map { it["display_name"] ?: "" }

                        val newAdapter = ArrayAdapter(
                            this@InputJemputActivity,
                            android.R.layout.simple_dropdown_item_1line,
                            namaTempat
                        )
                        autoCompleteTextView.setAdapter(newAdapter)
                        newAdapter.notifyDataSetChanged()
                        autoCompleteTextView.showDropDown()
                    }
                }
            }
        })

        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val selection = parent.getItemAtPosition(position) as String
            val dataPilih = currentSuggestions.find { it["display_name"] == selection }

            if (dataPilih != null) {
                val lat = dataPilih["lat"]?.toDoubleOrNull() ?: 0.0
                val lon = dataPilih["lon"]?.toDoubleOrNull() ?: 0.0

                onLocationSelected(lat, lon, selection)

                val imm = getSystemService(INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
                imm.hideSoftInputFromWindow(autoCompleteTextView.windowToken, 0)
                autoCompleteTextView.clearFocus()
            }
        }
    }

    private suspend fun cariLokasiDiMadiun(query: String): List<Map<String, String>> {
        return withContext(Dispatchers.IO) {
            val resultList = mutableListOf<Map<String, String>>()
            try {
                val viewbox = "111.4900,-7.5850,111.5650,-7.6750"
                val cleanQuery = query.replace(" ", "+")

                val urlString = "https://nominatim.openstreetmap.org/search?" +
                        "q=$cleanQuery" +
                        "&format=json" +
                        "&limit=5" +
                        "&viewbox=$viewbox" +
                        "&bounded=1"

                val url = URL(urlString)
                val connection = url.openConnection() as java.net.HttpURLConnection
                connection.setRequestProperty("User-Agent", "GoKuApp/1.0")

                val dataJson = connection.inputStream.bufferedReader().readText()
                val jsonArray = JSONArray(dataJson)

                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val map = mapOf(
                        "display_name" to obj.getString("display_name"),
                        "lat" to obj.getString("lat"),
                        "lon" to obj.getString("lon")
                    )
                    resultList.add(map)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            resultList
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}