package com.inc.thamsanqa.findplaces.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Location {

    @SerializedName("lat")
    @Expose
    var latitude: Double = 0.0

    @SerializedName("lng")
    @Expose
    var longitude: Double = 0.0
}
