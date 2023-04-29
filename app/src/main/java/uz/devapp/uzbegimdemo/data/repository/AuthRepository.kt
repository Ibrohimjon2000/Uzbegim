package uz.devapp.uzbegimdemo.data.repository

import kotlinx.coroutines.flow.flow
import uz.devapp.uzbegimdemo.data.api.Api
import uz.devapp.uzbegimdemo.data.model.request.ConfirmRequest
import uz.devapp.uzbegimdemo.data.model.request.LoginRequest
import uz.devapp.uzbegimdemo.data.repository.sealed.DataResult

class AuthRepository(private val api: Api) : BaseRepository() {

    suspend fun login(phone: String) = flow {
        emit(DataResult.LoadingShow())
        val result = api.login(LoginRequest(phone))
        if (result.isSuccessful) {
            emit(DataResult.LoadingHide())
            emit(DataResult.Success(result.body()?.data))
        } else {
            emit(DataResult.Error(result.message()))
        }
    }

    suspend fun confirm(fullname: String, phone: String, smsCode: String) = flow {
        emit(DataResult.LoadingShow())
        val result = api.confirm(ConfirmRequest(fullname, phone, smsCode))
        if (result.isSuccessful) {
            emit(DataResult.LoadingHide())
            emit(DataResult.Success(result.body()?.data!!))
        } else {
            emit(DataResult.Error(result.message()))
        }
    }
}