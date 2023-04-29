package uz.devapp.uzbegimdemo.data.repository

import org.greenrobot.eventbus.EventBus
import retrofit2.Response
import uz.devapp.uzbegimdemo.data.model.EventModel
import uz.devapp.uzbegimdemo.data.model.response.BaseResponse
import uz.devapp.uzbegimdemo.data.repository.sealed.DataResult
import uz.devapp.uzbegimdemo.utils.Constants
import uz.devapp.uzbegimdemo.utils.PrefUtils

open class BaseRepository {
    fun <T> wrapResponse(response: Response<BaseResponse<T>>): DataResult<T> {
        return if (response.isSuccessful) {
            if (response.body()?.success == true) {
                DataResult.Success(response.body()!!.data!!)
            } else {
                if (response.body()?.errorCode == 401) {
                    PrefUtils.clear()
                    EventBus.getDefault().post(EventModel(Constants.EVENT_CLEAR_TOKEN, 1))
                }
                DataResult.Error(response.body()?.message ?: "")
            }
        } else {
            DataResult.Error(response.message())
        }
    }
}