package uz.devapp.uzbegimdemo.data.model


import com.google.gson.annotations.SerializedName

data class ProductDetailModel(
    @SerializedName("cashback")
    val cashback: Int,
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("count_paid_delivery")
    val countPaidDelivery: Int,
    @SerializedName("currency")
    val currency: Currency,
    @SerializedName("currency_id")
    val currencyId: Int,
    @SerializedName("delivery_price")
    val deliveryPrice: Int,
    @SerializedName("description_en")
    val descriptionEn: String,
    @SerializedName("description_ru")
    val descriptionRu: String,
    @SerializedName("description_uz")
    val descriptionUz: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Any>,
    @SerializedName("in_stock")
    val inStock: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("is_top")
    val isTop: Boolean,
    @SerializedName("is_wholesale")
    val isWholesale: Boolean,
    @SerializedName("is_wishlist")
    val isWishlist: Boolean,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("main_image")
    val mainImage: String,
    @SerializedName("name_en")
    val nameEn: String,
    @SerializedName("name_ru")
    val nameRu: String,
    @SerializedName("name_uz")
    val nameUz: String,
    @SerializedName("old_price")
    val oldPrice: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("tag")
    val tag: String,
    @SerializedName("unity")
    val unity: Unity,
    @SerializedName("unity_id")
    val unityId: Int,
    @SerializedName("weight")
    val weight: Int
)