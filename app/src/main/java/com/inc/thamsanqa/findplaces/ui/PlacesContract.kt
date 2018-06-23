package com.inc.thamsanqa.findplaces.ui

import com.inc.thamsanqa.findplaces.model.Photo
import com.inc.thamsanqa.findplaces.model.Place

interface PlacesContract {

    interface Presenter {
        fun getUserLocation()
        fun getNearByPlaces(view: PlacesView)
        fun getPlacePhotos(placeId:String , view: PhotosView)
    }

    interface PlacesView {
        fun showPlacesOnMap(places: List<Place>)
    }

    interface PhotosView {
        fun showPhotos(photos: List<Photo>)
    }
}
