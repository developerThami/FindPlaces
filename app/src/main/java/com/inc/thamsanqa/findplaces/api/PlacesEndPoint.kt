package com.inc.thamsanqa.findplaces.api

import android.provider.ContactsContract
import com.inc.thamsanqa.findplaces.model.Photo
import com.inc.thamsanqa.findplaces.ui.PlacesContract

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlacesEndPoint {

    fun getNearByPlaces(key: String, location: String, listener: PlacesContract.PlacesView) {

        val placesAPI = RetrofitClient.getInstance().create(PlacesAPI::class.java)
        val call = placesAPI.getPlaces(key, location)

        call.enqueue(object : Callback<PlacesResponse> {
            override fun onResponse(call: Call<PlacesResponse>, response: Response<PlacesResponse>) {
                if (response.isSuccessful) {
                    listener.showPlacesOnMap(response.body()!!.results!!)
                } else {
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: Call<PlacesResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    fun getPlacePhotos(key: String, placeId: String, listener: PlacesContract.PhotosView) {

        val photoAPI = RetrofitClient.getInstance().create(PhotosAPI::class.java)
        val call = photoAPI.getPlacePhotos(key, placeId)

        call.enqueue(object : Callback<PhotosResponse> {
            override fun onResponse(call: Call<PhotosResponse>, response: Response<PhotosResponse>) {
                if (response.isSuccessful) {

                    val photos = response.body()!!.result!!.photos

                    if (photos != null) {
                        listener.showPhotos(photos)
                    }

                } else {
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: Call<PhotosResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }
}
