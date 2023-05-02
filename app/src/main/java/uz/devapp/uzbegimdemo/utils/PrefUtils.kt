package uz.devapp.uzbegimdemo.utils

import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import uz.devapp.uzbegimdemo.MyApp
import uz.devapp.uzbegimdemo.data.model.ProductListModel
import uz.devapp.uzbegimdemo.data.model.UserModel

object PrefUtils {
    const val PREF_TOKEN = "token"
    const val PREF_USER = "user"
    const val PREF_FAVORITE = "favorite"

    fun init() {
        Hawk.init(MyApp.app).build()
    }

    fun setToken(value: String?) {
        Hawk.put(PREF_TOKEN, value)
    }

    fun getToken(): String {
        return Hawk.get(PREF_TOKEN, "")
    }

    fun setUser(value: UserModel?) {
        Hawk.put(PREF_USER, value)
    }

    fun getUser(): UserModel {
        return Hawk.get(PREF_USER)
    }

    fun setFavorites(item: ProductListModel) {
        val items = Hawk.get(PREF_FAVORITE, arrayListOf<Int>())

        if (items.filter { it == item.id }.firstOrNull() != null) {
            items.remove(item.id)
        } else {
            items.add(item.id)
        }

        Hawk.put(PREF_FAVORITE, items)
    }

    fun checkFavorite(item: ProductListModel):Boolean{
        val items = Hawk.get(PREF_FAVORITE, arrayListOf<Int>())
        return items.filter { it == item.id }.firstOrNull() != null
    }

    fun clear() {
        Hawk.deleteAll()
    }
}