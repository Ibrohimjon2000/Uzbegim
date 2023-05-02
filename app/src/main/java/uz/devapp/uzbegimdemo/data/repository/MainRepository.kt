package uz.devapp.uzbegimdemo.data.repository

import kotlinx.coroutines.flow.flow
import uz.devapp.uzbegimdemo.data.api.Api
import uz.devapp.uzbegimdemo.data.model.request.LoginRequest
import uz.devapp.uzbegimdemo.data.model.request.ProductRequest
import uz.devapp.uzbegimdemo.data.repository.sealed.DataResult

class MainRepository(private val api: Api) : BaseRepository() {

    suspend fun getOffers() = flow {
        emit(DataResult.LoadingShow())
        val result = api.getOffers()
        if (result.isSuccessful) {
            emit(DataResult.LoadingHide())
            emit(DataResult.Success(result.body()?.data!!))
        } else {
            emit(DataResult.Error(result.message()))
        }
    }

    suspend fun getCategory() = flow {
        emit(DataResult.LoadingShow())
        val result = api.getCategory()
        if (result.isSuccessful) {
            emit(DataResult.LoadingHide())
            emit(DataResult.Success(result.body()?.data!!))
        } else {
            emit(DataResult.Error(result.message()))
        }
    }

    suspend fun productList(request: ProductRequest)= flow {
       emit(DataResult.LoadingShow())
        val result=api.productList(request)
        if (result.isSuccessful){
            emit(DataResult.LoadingHide())
            emit(DataResult.Success(result.body()?.data!!))
        }else{
            emit(DataResult.Error(result.message()))
        }
    }

    suspend fun setFavorite(id:Int) = flow {
        emit(DataResult.LoadingShow())
        val result = api.setFavorite(id)
        if (result.isSuccessful) {
            emit(DataResult.LoadingHide())
            emit(DataResult.Success(result.body()?.data))
        } else {
            emit(DataResult.Error(result.message()))
        }
    }

    suspend fun getFavorite()= flow {
        emit(DataResult.LoadingShow())
        val result=api.getFavorite()
        if (result.isSuccessful){
            emit(DataResult.LoadingHide())
            emit(DataResult.Success(result.body()?.data!!))
        }else{
            emit(DataResult.Error(result.message()))
        }
    }

    suspend fun getProductDetail(id:Int)= flow {
        emit(DataResult.LoadingShow())
        val result=api.getProductDetail(id)
        if (result.isSuccessful){
            emit(DataResult.LoadingHide())
            emit(DataResult.Success(result.body()?.data!!))
        }else{
            emit(DataResult.Error(result.message()))
        }
    }

    suspend fun getNews()= flow {
        emit(DataResult.LoadingShow())
        val result=api.getNews()
        if (result.isSuccessful){
            emit(DataResult.LoadingHide())
            emit(DataResult.Success(result.body()?.data!!))
        }else{
            emit(DataResult.Error(result.message()))
        }
    }

    suspend fun getNewsDetail(id:Int)= flow {
        emit(DataResult.LoadingShow())
        val result=api.getNewsDetail(id)
        if (result.isSuccessful){
            emit(DataResult.LoadingHide())
            emit(DataResult.Success(result.body()?.data!!))
        }else{
            emit(DataResult.Error(result.message()))
        }
    }

}