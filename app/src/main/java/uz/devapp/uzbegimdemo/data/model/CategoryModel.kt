package uz.devapp.uzbegimdemo.data.model


import com.google.gson.annotations.SerializedName

data class CategoryModel(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name_en")
    val nameEn: String,
    @SerializedName("name_ru")
    val nameRu: String,
    @SerializedName("name_uz")
    val nameUz: String
)