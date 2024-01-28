package jp.aqua.randomuser.db

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import jp.aqua.randomuser.domain.randomuser.model.components.UserData

class Converters {
    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromStringToResult(userDataString: String): UserData {
        return moshi.adapter(UserData::class.java).fromJson(userDataString)!!
    }

    @TypeConverter
    fun fromResultToString(userData: UserData): String {
        return moshi.adapter(UserData::class.java).toJson(userData)
    }
}