package com.inc.thamsanqa.findplaces.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result{
    @SerializedName("photos")
    @Expose
    var photos: List<Photo>? = null
}
