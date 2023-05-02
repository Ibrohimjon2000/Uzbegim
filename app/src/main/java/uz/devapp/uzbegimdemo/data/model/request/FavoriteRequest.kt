package uz.devapp.uzbegimdemo.data.model.request

import com.google.gson.annotations.SerializedName

data class FavoriteRequest(
    @SerializedName("id")
    var id: Int=0
)