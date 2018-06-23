package com.inc.thamsanqa.findplaces.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.inc.thamsanqa.findplaces.model.Location

class Geometry {

    @SerializedName("location")
    @Expose
    var location: Location? = null
}
