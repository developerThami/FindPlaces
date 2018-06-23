package com.inc.thamsanqa.findplaces.ui

import com.inc.thamsanqa.findplaces.api.PlacesEndPoint

class PlacesPresenter : PlacesContract.Presenter {

    override fun getUserLocation() {

    }

    override fun getNearByPlaces(listener: PlacesContract.PlacesView) {
        Thread {
            PlacesEndPoint().getNearByPlaces("AIzaSyBnk9KVptIvESwmX7bTPHtwIK86MrZSOzY", "-33.8670522,151.1957362", listener)
        }.start()
    }

    override fun getPlacePhotos(placeId: String, listener: PlacesContract.PhotosView) {
        Thread {
            PlacesEndPoint().getPlacePhotos("AIzaSyBnk9KVptIvESwmX7bTPHtwIK86MrZSOzY", placeId, listener)
        }.start()
    }

}
