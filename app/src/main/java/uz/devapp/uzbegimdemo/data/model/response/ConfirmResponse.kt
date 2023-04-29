package uz.devapp.uzbegimdemo.data.model.response


import com.google.gson.annotations.SerializedName

data class ConfirmResponse(
    @SerializedName("avatar")
    val avatar: Any,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("token")
    val token: String
)