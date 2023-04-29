package uz.devapp.uzbegimdemo.data.model.request


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("phone")
    val phone: String
)