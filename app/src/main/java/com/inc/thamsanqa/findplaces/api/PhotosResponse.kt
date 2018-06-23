package com.inc.thamsanqa.findplaces.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.inc.thamsanqa.findplaces.model.Result

class PhotosResponse {

    @SerializedName("result")
    @Expose
    var result: Result? = null
}
