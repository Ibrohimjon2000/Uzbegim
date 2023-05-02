package uz.devapp.uzbegimdemo.data.model


import com.google.gson.annotations.SerializedName

data class OffersModel(
    @SerializedName("data")
    val `data`: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_en")
    val imageEn: String,
    @SerializedName("image_ru")
    val imageRu: String,
    @SerializedName("image_uz")
    val imageUz: String,
    @SerializedName("name_en")
    val nameEn: String,
    @SerializedName("name_ru")
    val nameRu: String,
    @SerializedName("name_uz")
    val nameUz: String,
    @SerializedName("type")
    val type: String
)