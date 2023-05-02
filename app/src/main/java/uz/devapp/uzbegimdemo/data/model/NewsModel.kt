package uz.devapp.uzbegimdemo.data.model


import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("content_en")
    val contentEn: String,
    @SerializedName("content_ru")
    val contentRu: String,
    @SerializedName("content_uz")
    val contentUz: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("send_fcm")
    val sendFcm: Boolean,
    @SerializedName("title_en")
    val titleEn: String,
    @SerializedName("title_ru")
    val titleRu: String,
    @SerializedName("title_uz")
    val titleUz: String
)