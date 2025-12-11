package com.example.goku

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentMencari : Fragment(R.layout.fragment_mencari) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SIMULASI MENCARI DRIVER (Timer)
        viewLifecycleOwner.lifecycleScope.launch {
            // 1. Tunggu 4 detik (Loading...)
            delay(4000)

            // 2. Cek apakah fragment masih nempel di layar (biar gak crash kalau user back)
            if (isAdded) {
                // 3. Pindah ke Status "Driver Menjemput"
                (activity as? MapActivity)?.gantiStatus(RideState.MENJEMPUT)
            }
        }
    }
}