package com.inc.thamsanqa.findplaces.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.inc.thamsanqa.findplaces.api.PlacesEndPoint

class PlacesPresenter : PlacesContract.Presenter {

    private val locationRequestCode: Int = 200

    override fun requestLocationPermission(context: Context) {
        ActivityCompat.requestPermissions(context as Activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationRequestCode)
    }

    @SuppressLint("MissingPermission")
    override fun requestUserLocation(context: Context, locationManager: LocationManager, locationListener: LocationListener) {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0.0f, locationListener)
    }

    override fun getNearByPlaces(location: String, listener: PlacesContract.PlacesView) {
        Thread {
            PlacesEndPoint().getNearByPlaces("AIzaSyBnk9KVptIvESwmX7bTPHtwIK86MrZSOzY", location, listener)
        }.start()
    }

    override fun getPlacePhotos(placeId: String, listener: PlacesContract.PhotosView) {
        Thread {
            PlacesEndPoint().getPlacePhotos("AIzaSyBnk9KVptIvESwmX7bTPHtwIK86MrZSOzY", placeId, listener)
        }.start()
    }

}
