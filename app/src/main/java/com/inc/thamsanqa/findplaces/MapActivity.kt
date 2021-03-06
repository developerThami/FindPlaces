package com.inc.thamsanqa.findplaces

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.inc.thamsanqa.findplaces.model.Place
import com.inc.thamsanqa.findplaces.ui.PlacesContract
import com.inc.thamsanqa.findplaces.ui.PlacesPresenter

import android.provider.Settings
import android.support.v7.app.AlertDialog
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationRequest
import android.location.LocationManager
import android.content.Context.LOCATION_SERVICE



class MapActivity : AppCompatActivity(), OnMapReadyCallback, PlacesContract.PlacesView {

    private lateinit var mMap: GoogleMap
    private lateinit var presenter: PlacesPresenter
    private val locationRequestCode: Int = 200
    private lateinit var mFusedLocationClient:FusedLocationProviderClient
    private lateinit var locationCallback:LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        presenter = PlacesPresenter()
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            presenter.requestLocationPermission(this)
        } else {
            getUserLocation()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    @SuppressLint("MissingPermission")
    override fun showPlacesOnMap(places: List<Place>) {

        for (place in places) {
            plotPlace(place)
        }

        mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f))
        mMap.setMyLocationEnabled(true)
    }

    private fun plotPlace(place: Place) {

        val placeLocation = place.geometry!!.location
        val name = place.name

        val latLng = LatLng(placeLocation!!.latitude, placeLocation.longitude)
        val markerOptions = MarkerOptions().position(latLng).title(name).snippet(getString(R.string.click_message))

        val marker = mMap.addMarker(markerOptions)
        marker.tag = place.id

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.setOnInfoWindowClickListener { showImages(it.tag as String) }

    }

    private fun showImages(tag: String): Boolean {
        val intent = Intent(this, PhotosActivity::class.java)
        intent.putExtra(Intent.EXTRA_TEXT, tag)
        startActivity(intent)
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            locationRequestCode ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkLocationEnabled()
                } else {
                    presenter.requestLocationPermission(this)
                }
        }
    }

    private fun showDialog() {
        val alertDialog = AlertDialog.Builder(this@MapActivity)
        alertDialog.setTitle(getString(R.string.enable))
        alertDialog.setMessage(getString(R.string.message))
        alertDialog.setPositiveButton(getString(R.string.settings), { _, _ ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        })
        alertDialog.setNegativeButton(getString(R.string.cancel), { dialog, _ -> dialog.cancel() })
        val alert = alertDialog.create()
        alert.show()
    }

    private fun switchOffLocationListener() {
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation() {

        val locationRequest = LocationRequest()

        locationRequest.interval = 120000 // two minute interval
        locationRequest.fastestInterval = 120000
        locationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY

         locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {

                for (location in locationResult!!.locations){
                    getNearPlaces(location)
                    switchOffLocationListener()
                }
            }
        }
        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)

    }

    private fun checkLocationEnabled() {

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            getUserLocation()
        }else{
            showDialog()
        }
    }

    private fun getNearPlaces(location: Location) {

        val latitude = location.latitude
        val longitude = location.longitude

        val userLocation = String.format("%s,%s", latitude, longitude)
        presenter.getNearByPlaces(getString(R.string.key) ,userLocation, this)
    }

    override fun onResume() {
        super.onResume()
        getUserLocation()
    }
}
