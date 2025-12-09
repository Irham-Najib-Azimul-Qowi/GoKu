package com.example.goku

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.preference.PreferenceManager
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MapActivity : AppCompatActivity() {

    // Variabel Global untuk komponen UI
    private lateinit var mapView: MapView
    private lateinit var containerTop: FragmentContainerView
    private lateinit var containerBottom: FragmentContainerView

    // Variabel untuk menangkap data Voucher (Diskon)
    private var isVoucherUsed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().load(
            applicationContext,
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        )

        Configuration.getInstance().userAgentValue = packageName

        setContentView(R.layout.activity_map)

        isVoucherUsed = intent.getBooleanExtra("PAKAI_VOUCHER", false)

        mapView = findViewById(R.id.mapView)
        containerTop = findViewById(R.id.containerTop)
        containerBottom = findViewById(R.id.containerBottom)

        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        mapView.isTilesScaledToDpi = true

        val startPoint = GeoPoint(-7.6298, 111.5239)
        mapView.controller.setZoom(15.0)
        mapView.controller.setCenter(startPoint)

        gantiStatus(RideState.INPUT_LOKASI)

    }

    fun gantiStatus(State: RideState) {
        val isTopContainer = when (state) {
            RideState.INPUT_LOKASI, RideState.CEK_HARGA -> true
            else -> false
        }

        if (isTopContainer) {
            containerTop.visibility = View.VISIBLE
            containerBottom.visibility = View.GONE
        } else {
            containerTop.visibility = View.GONE
            containerBottom.visibility = View.VISIBLE
        }

        val fragmentBaru: Fragment = when (state) {
            RideState.INPUT_LOKASI -> FragmenInput()
            RideState.CEK_HARGA -> {
                val frag = FragmentHarga()
                val bundle = Bundle()
                bundle.putBoolean("PAKAI_VOUCHER", isVoucherUsed)
                frag.arguments = bundle
                frag
            )



        }

            RideState.MENCARI_DRIVER -> FragmentMencari()
            RideState.MENJEMPUT -> FragmentDriver(status = "Menjemput")
            RideState.PERJALANAN -> FragmentDriver(status = "Dalam Perjalanan")
            RideState.SAMPAI -> FragmentBayar()
        }

        val containerId = if (isTopContainer) R.id.containerTop else R.id.containerBottom

        supportFragmentManager.beginTransaction()
            .replace(containerId, fragmentBaru)
            .commit()
    }

    override fun onResumeFragments() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}

enum class RideState {
    INPUT_LOKASI,
    CEK_HARGA,
    MENCARI_DRIVER,
    MENJEMPUT,
    PERJALANAN,
    SAMPAI
}