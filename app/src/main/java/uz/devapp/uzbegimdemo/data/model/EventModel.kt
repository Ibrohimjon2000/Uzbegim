package uz.devapp.uzbegimdemo.data.model

data class EventModel<T>(
    val command:Int,
    val data:T
)
