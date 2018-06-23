package com.inc.thamsanqa.findplaces

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.inc.thamsanqa.findplaces.model.Place
import com.inc.thamsanqa.findplaces.ui.PlacesContract
import com.inc.thamsanqa.findplaces.ui.PlacesPresenter

class MapActivity : AppCompatActivity(), OnMapReadyCallback, PlacesContract.PlacesView {

    private lateinit var mMap: GoogleMap
    private lateinit var presenter: PlacesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        presenter = PlacesPresenter()
        presenter.getNearByPlaces(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override fun showPlacesOnMap(places: List<Place>) {

        for (place in places) {
            plotPlace(place)
        }

        mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f))
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
}
