package uz.devapp.uzbegimdemo.utils

import com.orhanobut.hawk.Hawk
import uz.devapp.uzbegimdemo.MyApp

object PrefUtils {
    const val PREF_TOKEN = "token"
    const val PREF_USER = "user"

    fun init() {
        Hawk.init(MyApp.app).build()
    }

    fun setToken(value: String?) {
        Hawk.put(PREF_TOKEN, value)
    }

    fun getToken(): String {
        return Hawk.get(PREF_TOKEN, "")
    }

//    fun setUser(value: UserModel?) {
//        Hawk.put(PREF_USER, value)
//    }
//
//    fun getUser(): UserModel {
//        return Hawk.get(PREF_USER)
//    }

    fun clear() {
        Hawk.deleteAll()
    }
}