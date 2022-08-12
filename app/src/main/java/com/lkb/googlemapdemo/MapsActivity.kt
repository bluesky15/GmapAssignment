package com.lkb.googlemapdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.lkb.googlemapdemo.databinding.ActivityMapsBinding


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var markerCenter: Marker? = null
    private val viewModel: MapViewModel by viewModels {
        MapViewModelFactory((application as MapApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.floatingActionButton.setOnClickListener {
            mMap.addMarker(MarkerOptions().position(mMap.cameraPosition.target))
            Log.d(">>>", "${mMap.cameraPosition.target}")
            val bundle = Bundle()
            bundle.putString("lat", mMap.cameraPosition.target?.latitude.toString())
            bundle.putString("long", mMap.cameraPosition.target?.longitude.toString())
            val bottomModal = BottomModal.newInstance(bundle)
            bottomModal.show(supportFragmentManager, "Bottom-modal")
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val markerOptions = MarkerOptions()
        markerOptions.position(mMap.cameraPosition.target)
        markerCenter = mMap.addMarker(markerOptions)

        mMap.setOnCameraMoveListener {
            markerCenter?.position = mMap.cameraPosition.target
        }

        viewModel.getData().observe(this) {
            it.forEach { place ->
                mMap.addMarker(
                    MarkerOptions().position(
                        LatLng(
                            place.latitude.toDouble(),
                            place.longitude.toDouble()
                        )
                    )
                )
            }
        }
    }

    override fun onMapClick(p0: LatLng) {
    }
}