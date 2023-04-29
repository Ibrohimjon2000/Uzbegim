package uz.devapp.uzbegimdemo.data.model.request


import com.google.gson.annotations.SerializedName

data class ConfirmRequest(
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("sms_code")
    val smsCode: String
)