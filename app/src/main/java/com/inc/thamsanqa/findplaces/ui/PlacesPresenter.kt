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

    override fun getNearByPlaces(key: String, location: String, listener: PlacesContract.PlacesView) {
        Thread {
            PlacesEndPoint().getNearByPlaces(key, location, listener)
        }.start()
    }

    override fun getPlacePhotos(key: String, placeId: String, listener: PlacesContract.PhotosView) {
        Thread {
            PlacesEndPoint().getPlacePhotos(key, placeId, listener)
        }.start()
    }

}
