package com.inc.thamsanqa.findplaces.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosAPI {
    @GET("/maps/api/place/details/json?fields=photos")
    fun getPlacePhotos(@Query("key") key: String, @Query("place_id") placeId: String): Call<PhotosResponse>
}