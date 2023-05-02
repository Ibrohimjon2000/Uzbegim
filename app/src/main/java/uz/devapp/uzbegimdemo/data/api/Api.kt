package uz.devapp.uzbegimdemo.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import uz.devapp.uzbegimdemo.data.model.*
import uz.devapp.uzbegimdemo.data.model.request.ConfirmRequest
import uz.devapp.uzbegimdemo.data.model.request.LoginRequest
import uz.devapp.uzbegimdemo.data.model.request.ProductRequest
import uz.devapp.uzbegimdemo.data.model.response.BaseResponse
import uz.devapp.uzbegimdemo.data.model.response.ConfirmResponse

interface Api {

    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<BaseResponse<Any>>

    @POST("api/auth/confirm")
    suspend fun confirm(
        @Body request: ConfirmRequest
    ): Response<BaseResponse<ConfirmResponse>>

    @GET("api/offer/list")
    suspend fun getOffers(): Response<BaseResponse<List<OffersModel>>>

    @GET("api/category/list")
    suspend fun getCategory(): Response<BaseResponse<List<CategoryModel>>>

    @POST("api/product/list")
    suspend fun productList(
        @Body request:ProductRequest
    ):Response<BaseResponse<List<ProductListModel>>>

    @GET("api/get/user")
    suspend fun getUser():Response<BaseResponse<UserModel>>

    @GET("api/favorite/set/{product_id}")
    suspend fun setFavorite(
        @Path("product_id") id:Int
    ):Response<BaseResponse<Any>>

    @GET("api/favorite/list")
    suspend fun getFavorite():Response<BaseResponse<List<ProductListModel>>>

    @GET("api/product/{product_id}/detail")
    suspend fun getProductDetail(
        @Path("product_id") id:Int
    ):Response<BaseResponse<ProductDetailModel>>

    @GET("api/news/list")
    suspend fun getNews():Response<BaseResponse<List<NewsModel>>>

    @GET("api/news/{product_id}/detail")
    suspend fun getNewsDetail(
        @Path("product_id")id:Int
    ):Response<BaseResponse<NewsModel>>
}