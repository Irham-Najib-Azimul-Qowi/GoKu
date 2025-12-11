package com.example.goku

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.bonuspack.routing.OSRMRoadManager
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline

class MapActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    // Variabel Marker
    private var markerJemput: Marker? = null
    private var markerTujuan: Marker? = null

    // Variabel Garis Rute (Ini yang tadi kurang)
    private var routeOverlay: Polyline? = null

    // Variabel Voucher
    private var isVoucherUsed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Konfigurasi OSM
        Configuration.getInstance().load(applicationContext, PreferenceManager.getDefaultSharedPreferences(applicationContext))
        Configuration.getInstance().userAgentValue = packageName

        setContentView(R.layout.activity_map)

        // Tangkap data voucher dari Home (jika ada)
        isVoucherUsed = intent.getBooleanExtra("PAKAI_VOUCHER", false)

        // 2. Setup Peta
        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        mapView.isTilesScaledToDpi = true

        // Fokus ke Madiun
        val startPoint = GeoPoint(-7.6298, 111.5239)
        mapView.controller.setZoom(15.0)
        mapView.controller.setCenter(startPoint)

        val madiunBox = BoundingBox(-7.5850, 111.5650, -7.6750, 111.4900)
        mapView.setScrollableAreaLimitDouble(madiunBox)
        mapView.minZoomLevel = 13.0
        mapView.maxZoomLevel = 19.0

        // 3. MULAI APLIKASI (PENTING: Jangan lupa ini!)
        gantiStatus(RideState.INPUT_LOKASI)
    }

    // --- FUNGSI 1: GANTI STATUS TAMPILAN ---
    fun gantiStatus(state: RideState) {
        // Tentukan mau pakai wadah atas atau bawah
        val isTopContainer = when (state) {
            RideState.INPUT_LOKASI, RideState.CEK_HARGA -> true
            else -> false
        }

        val containerTop = findViewById<View>(R.id.containerTop)
        val containerBottom = findViewById<View>(R.id.containerBottom)

        // Atur visibilitas
        if (isTopContainer) {
            containerTop.visibility = View.VISIBLE
            containerBottom.visibility = View.GONE
        } else {
            containerTop.visibility = View.GONE
            containerBottom.visibility = View.VISIBLE
        }

        // Pilih Fragment
        val fragmentBaru: Fragment = when (state) {
            RideState.INPUT_LOKASI -> InputFragment()
            RideState.CEK_HARGA -> {
                val frag = FragmentHarga()
                val bundle = Bundle()
                bundle.putBoolean("PAKAI_VOUCHER", isVoucherUsed)
                frag.arguments = bundle
                frag
            }

            // --- TAMBAHAN BARU ---
            RideState.MENCARI_DRIVER -> FragmentMencari() // Pastikan file FragmentMencari sudah ada
            // ---------------------

            RideState.MENJEMPUT -> {
                val frag = TripFragment() // Pastikan FragmentTrip sudah dibuat
                val bundle = Bundle()
                bundle.putString("STATUS_TEXT", "Driver Sedang Menuju Lokasimu")
                frag.arguments = bundle
                frag
            }

            RideState.PERJALANAN -> {
                val frag = TripFragment()
                val bundle = Bundle()
                bundle.putString("STATUS_NEXT", "Driver sedang Menuju Lokasimu")
                frag.arguments = bundle
                frag
            }

            // Placeholder untuk status lain
            else -> Fragment()
        }

        // Tempel Fragment
        val containerId = if (isTopContainer) R.id.containerTop else R.id.containerBottom
        supportFragmentManager.beginTransaction()
            .replace(containerId, fragmentBaru)
            .commit()
    }

    // --- FUNGSI 2: UPDATE MARKER ---
    fun updateMarker(isJemput: Boolean, lat: Double, lon: Double, title: String) {
        val point = GeoPoint(lat, lon)
        val targetMarker = if (isJemput) markerJemput else markerTujuan

        if (targetMarker != null) mapView.overlays.remove(targetMarker)

        val newMarker = Marker(mapView)
        newMarker.position = point
        newMarker.title = title
        newMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)

        // Pastikan gambar tanda_jemput dan pinmap ada di drawable
        val iconRes = if (isJemput) R.drawable.tanda_jemput else R.drawable.pinmap
        newMarker.icon = androidx.core.content.ContextCompat.getDrawable(this, iconRes)

        if (isJemput) markerJemput = newMarker else markerTujuan = newMarker
        mapView.overlays.add(newMarker)
        mapView.invalidate()
        mapView.controller.animateTo(point)
    }

    // --- FUNGSI 3: GAMBAR RUTE (ROUTING) ---
    fun gambarRuteJalan() {
        val start = markerJemput?.position
        val end = markerTujuan?.position

        if (start == null || end == null) return

        lifecycleScope.launch(Dispatchers.IO) {
            // 1. Minta Rute ke OSRM
            val roadManager = OSRMRoadManager(this@MapActivity, "GoKuApp/1.0")
            val waypoints = ArrayList<GeoPoint>()
            waypoints.add(start)
            waypoints.add(end)

            val road = roadManager.getRoad(waypoints)

            // 2. Gambar di Peta
            withContext(Dispatchers.Main) {
                // Hapus garis lama
                if (routeOverlay != null) mapView.overlays.remove(routeOverlay)

                // Buat garis baru
                routeOverlay = RoadManager.buildRoadOverlay(road)
                routeOverlay?.outlinePaint?.color = Color.BLUE
                routeOverlay?.outlinePaint?.strokeWidth = 15f

                mapView.overlays.add(routeOverlay)
                mapView.invalidate()

                // Zoom pas ke tengah rute
                mapView.zoomToBoundingBox(road.mBoundingBox, true, 100)
            }
        }
    }

    // Lifecycle
    override fun onResume() { super.onResume(); mapView.onResume() }
    override fun onPause() { super.onPause(); mapView.onPause() }
}

// DAFTAR STATUS (ENUM)
enum class RideState {
    INPUT_LOKASI,
    CEK_HARGA,
    MENCARI_DRIVER,
    MENJEMPUT,
    PERJALANAN,
    SAMPAI
}