package jp.aqua.randomuser.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import jp.aqua.randomuser.data.User.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM $TABLE_NAME WHERE ${User.ID} = :id")
    fun getItem(id: Int): Flow<User>

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllUsers(): Flow<List<User>>

}
