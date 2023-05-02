package uz.devapp.uzbegimdemo.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetImage(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("updated_at")
    val updatedAt: Any?
): Serializable