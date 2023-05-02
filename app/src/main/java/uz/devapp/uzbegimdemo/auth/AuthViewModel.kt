package uz.devapp.uzbegimdemo.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.devapp.uzbegimdemo.data.model.UserModel
import uz.devapp.uzbegimdemo.data.model.response.ConfirmResponse
import uz.devapp.uzbegimdemo.data.repository.AuthRepository
import uz.devapp.uzbegimdemo.data.repository.BaseRepository
import uz.devapp.uzbegimdemo.data.repository.sealed.DataResult
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private var _authLiveData = MutableLiveData<DataResult<Any?>>()
    var authLiveData: LiveData<DataResult<Any?>> = _authLiveData

    private var _confirmLiveData = MutableLiveData<DataResult<ConfirmResponse>>()
    var confirmLiveData: LiveData<DataResult<ConfirmResponse>> = _confirmLiveData

    private var _userLiveData = MutableLiveData<DataResult<UserModel>>()
    var userLiveData: LiveData<DataResult<UserModel>> = _userLiveData

    fun login(phone: String) {
        viewModelScope.launch {
            repository.login(phone).collect {
                _authLiveData.value = it
            }
        }
    }

    fun confirm(fullname: String, phone: String, smsCode: String) {
        viewModelScope.launch {
            repository.confirm(fullname, phone, smsCode).collect {
                _confirmLiveData.value =it
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            repository.getUser().collect {
                _userLiveData.value = it
            }
        }
    }
}