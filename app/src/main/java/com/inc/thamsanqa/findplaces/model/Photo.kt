package com.inc.thamsanqa.findplaces.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo {

    @SerializedName("photo_reference")
    @Expose
    var photoReference: String? = null

    @SerializedName("length")
    @Expose
    var photoLength: Int = 0

    @SerializedName("width")
    @Expose
    var photoWidth: Int = 0
}
