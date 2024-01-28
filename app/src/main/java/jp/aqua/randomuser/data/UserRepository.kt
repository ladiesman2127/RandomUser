package jp.aqua.randomuser.data

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import jp.aqua.randomuser.network.RandomUserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.io.IOException

class UserRepository(
    private val context: Context,
    private val userDao: UserDao
) {
    private suspend fun addUser(user: User) = userDao.insert(user)

    suspend fun updateUser(user: User) = userDao.update(user)

    suspend fun deleteUser(user: User) = userDao.delete(user)

    fun getUser(id: Int): Flow<User> = userDao.getItem(id)

    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

    suspend fun updateUserList(): User {
        val apiResponse = RandomUserApi.retrofitService.get()
        val newUser = User(data = apiResponse.results.first())
        addUser(newUser)
        return newUser
    }
}
