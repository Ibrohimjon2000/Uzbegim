package uz.devapp.uzbegimdemo.data.model.request

import com.google.gson.annotations.SerializedName

data class NewsRequest(
    @SerializedName("id")
    var id: Int=0
)