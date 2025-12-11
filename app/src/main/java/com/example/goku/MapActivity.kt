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
                
                // --- UPDATE: Kirim Text Judul Lokasi ---
                val jemputText = markerJemput?.title ?: "Lokasi Jemput"
                val tujuanText = markerTujuan?.title ?: "Lokasi Tujuan"
                bundle.putString("LOKASI_A", jemputText)
                bundle.putString("LOKASI_B", tujuanText)
                
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
                routeOverlay?.outlinePaint?.color = Color.parseColor("#00C853")
                routeOverlay?.outlinePaint?.strokeWidth = 15f

                mapView.overlays.add(routeOverlay)
                mapView.invalidate()

                // Zoom pas ke tengah rute
                mapView.zoomToBoundingBox(road.mBoundingBox, true, 100)
            }
        }
    }

    // Variabel Simulasi Motor
    private var markerMotor: Marker? = null
    private var driverRouteOverlay: Polyline? = null // Line khusus buat driver jemput

    // Helper untuk membuat marker motor jika belum ada
    private fun ensureMotorKey() {
        if (markerMotor == null) {
            markerMotor = Marker(mapView)
            markerMotor?.icon = androidx.core.content.ContextCompat.getDrawable(this, R.drawable.motor_otw)
            markerMotor?.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
            markerMotor?.title = "Driver GoKu"
            mapView.overlays.add(markerMotor)
        }
    }

    // --- FUNGSI 4: SIMULASI GRAB LIKE (Menjemput dengan Rute) ---
    fun startSimulationJemput(onArrived: () -> Unit) {
        val jemput = markerJemput?.position ?: GeoPoint(-7.6298, 111.5239)
        // Driver start dari lokasi agak jauh (misal 1 km)
        val startDriver = GeoPoint(jemput.latitude + 0.008, jemput.longitude + 0.008)

        ensureMotorKey()

        lifecycleScope.launch(Dispatchers.IO) {
            // 1. Cari jalan buat Driver -> Jemput
            val roadManager = OSRMRoadManager(this@MapActivity, "GoKuApp/1.0")
            val waypoints = ArrayList<GeoPoint>()
            waypoints.add(startDriver)
            waypoints.add(jemput)
            
            val road = roadManager.getRoad(waypoints)
            val nodes = road.mNodes

            withContext(Dispatchers.Main) {
                // 2. Gambar Garis Rute Driver
                if (driverRouteOverlay != null) mapView.overlays.remove(driverRouteOverlay)
                
                driverRouteOverlay = RoadManager.buildRoadOverlay(road)
                driverRouteOverlay?.outlinePaint?.color = Color.parseColor("#4FC3F7") // Biru muda
                driverRouteOverlay?.outlinePaint?.strokeWidth = 10f
                mapView.overlays.add(driverRouteOverlay)
                mapView.invalidate()

                // 3. Animasi ikuti jalan (12 detik biar lebih real)
                animateAlongTrack(nodes, 12000L) {
                    // Hapus garis driver kalau sudah sampai (opsional)
                    if (driverRouteOverlay != null) {
                        mapView.overlays.remove(driverRouteOverlay)
                        mapView.invalidate()
                    }
                    onArrived()
                }
            }
        }
    }

    // --- FUNGSI 5: SIMULASI GRAB LIKE (Mengantar sesuai Rute Utama) ---
    private var mainRoadNodes: ArrayList<GeoPoint>? = null

    fun startSimulationAntar(onArrived: () -> Unit) {
        val jemput = markerJemput?.position ?: return
        val tujuan = markerTujuan?.position ?: return

        ensureMotorKey()

        lifecycleScope.launch(Dispatchers.IO) {
            // Ambil rute ulang atau pakai yang sudah ada jika tersimpan
            // Kita request ulang biar aman dan dapat nodes-nya
            val roadManager = OSRMRoadManager(this@MapActivity, "GoKuApp/1.0")
            val waypoints = ArrayList<GeoPoint>()
            waypoints.add(jemput)
            waypoints.add(tujuan)

            val road = roadManager.getRoad(waypoints)
            val nodes = road.mNodes

            withContext(Dispatchers.Main) {
                 // Pastikan garis rute utama (biru) ada
                 if (routeOverlay == null) {
                     routeOverlay = RoadManager.buildRoadOverlay(road)
                     routeOverlay?.outlinePaint?.color = Color.parseColor("#00C853") // Hijau GoKu
                     routeOverlay?.outlinePaint?.strokeWidth = 15f
                     mapView.overlays.add(routeOverlay)
                 }

                 // Animasi (15 detik biar lebih real)
                 animateAlongTrack(nodes, 15000L) {
                     onArrived()
                 }
            }
        }
    }

    // Fungsi Animasi mengikuti titik-titik jalan (Nodes)
    private fun animateAlongTrack(nodes: List<org.osmdroid.bonuspack.routing.RoadNode>, duration: Long, onFinish: () -> Unit) {
        if (nodes.isEmpty()) {
            onFinish()
            return
        }

        // Konversi RoadNode ke GeoPoint biasa biar ringan
        val points = nodes.map { it.mLocation }
        val totalPoints = points.size
        
        // Kita jalan frame by frame.
        // Total waktu = duration.
        // Pergerakan: kita interpolasi index.
        
        val handler = android.os.Handler(android.os.Looper.getMainLooper())
        val startTime = System.currentTimeMillis()

        val runnable = object : Runnable {
            override fun run() {
                val elapsed = System.currentTimeMillis() - startTime
                val t = elapsed.toFloat() / duration

                if (t >= 1.0f) {
                    markerMotor?.position = points.last()
                    markerMotor?.rotation = 0f
                    mapView.invalidate()
                    onFinish()
                    return
                }

                // Hitung posisi saat ini di index mana
                // Panjang array = totalPoints
                // Index virtual = t * (totalPoints - 1)
                val indexFloat = t * (totalPoints - 1)
                val indexInt = indexFloat.toInt()
                val fraction = indexFloat - indexInt

                if (indexInt < totalPoints - 1) {
                    val p1 = points[indexInt]
                    val p2 = points[indexInt + 1]

                    val lat = p1.latitude + (p2.latitude - p1.latitude) * fraction
                    val lon = p1.longitude + (p2.longitude - p1.longitude) * fraction
                    
                    markerMotor?.position = GeoPoint(lat, lon)
                    
                    // Hitung Rotasi biar motor menghadap jalan
                    val bearing = p1.bearingTo(p2).toFloat()
                    markerMotor?.rotation = -bearing // OSMDroid rotasi kadang perlu minus atau setup anchor
                    
                    mapView.invalidate()
                    handler.postDelayed(this, 16)
                }
            }
        }
        handler.post(runnable)
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