package jp.aqua.randomuser.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import jp.aqua.randomuser.data.User.Companion.TABLE_NAME
import jp.aqua.randomuser.domain.randomuser.model.components.UserData

@Entity(tableName = TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Int = 0,
    @ColumnInfo(name = USER_DATA)
    val data: UserData? = null
) {
    companion object {
        const val TABLE_NAME = "users"
        const val ID = "id"
        const val USER_DATA = "userData"
    }
}