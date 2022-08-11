package com.lkb.googlemapdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
    var latlong: LatLng? = null

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
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//        mMap.setOnMapClickListener(this)
//        mMap.uiSettings.isZoomControlsEnabled = true
////        // Add a marker in Sydney and move the camera
////        val sydney = LatLng(-34.0, 151.0)
////        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap = googleMap

        val markerOptions = MarkerOptions()
        markerOptions.position(mMap.cameraPosition.target)
        latlong = mMap.cameraPosition.target
        markerCenter = mMap.addMarker(markerOptions)

        mMap.setOnCameraMoveListener { markerCenter?.position = mMap.cameraPosition.target }
    }

    override fun onMapClick(p0: LatLng) {
        Log.d("Mapss", p0.toString())
        mMap.addMarker(MarkerOptions().position(p0).title("Marker in Sydney"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(p0))
    }
}