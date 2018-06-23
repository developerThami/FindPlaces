package com.inc.thamsanqa.findplaces.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.inc.thamsanqa.findplaces.model.Place

class PlacesResponse {
    @SerializedName("results")
    @Expose
    var results: List<Place>? = null
}
