package uz.devapp.uzbegimdemo.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.devapp.uzbegimdemo.data.model.request.ConfirmRequest
import uz.devapp.uzbegimdemo.data.model.request.LoginRequest
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
}