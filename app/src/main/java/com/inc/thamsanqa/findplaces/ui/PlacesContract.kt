package com.inc.thamsanqa.findplaces.ui

import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import com.inc.thamsanqa.findplaces.model.Photo
import com.inc.thamsanqa.findplaces.model.Place

interface PlacesContract {

    interface Presenter {
        fun requestLocationPermission(context: Context)
        fun getNearByPlaces(key: String, location: String, view: PlacesView)
        fun getPlacePhotos(key: String, placeId: String, view: PhotosView)

    }

    interface PlacesView {
        fun showPlacesOnMap(places: List<Place>)
    }

    interface PhotosView {
        fun showPhotos(photos: List<Photo>)
        fun showNoPicsAvailable()
    }
}
