package uz.devapp.uzbegimdemo.data.model.request


import com.google.gson.annotations.SerializedName

data class ProductRequest(
    @SerializedName("category_id")
    val categoryId: Int = 0,
    @SerializedName("ids")
    val ids: List<Any> = listOf(),
    @SerializedName("is_new")
    val isNew: Boolean = false,
    @SerializedName("is_sale")
    val isSale: Boolean = false,
    @SerializedName("is_top")
    val isTop: Boolean = false,
    @SerializedName("is_wholesale")
    val isWholesale: Boolean = false,
    @SerializedName("keyword")
    val keyword: String = "",
    @SerializedName("tag")
    val tag: String = ""
)