package com.inc.thamsanqa.findplaces.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Place {

    @SerializedName("place_id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String?? = null

    @SerializedName("photos")
    @Expose
    var photos: List<Photo>? = null

    @SerializedName("geometry")
    @Expose
    var geometry: Geometry? = null

    @SerializedName("icon")
    @Expose
    var iconUrl: String? = null
}
