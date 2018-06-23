package com.inc.thamsanqa.findplaces.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesAPI {
    @GET("/maps/api/place/nearbysearch/json?rankby=distance")
    fun getPlaces(@Query("key") key: String, @Query("location") location: String): Call<PlacesResponse>
}
