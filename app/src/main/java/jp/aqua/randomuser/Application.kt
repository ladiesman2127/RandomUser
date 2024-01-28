package jp.aqua.randomuser

import android.app.Application
import jp.aqua.randomuser.data.UserRepository
import jp.aqua.randomuser.db.UserDatabase

class Application : Application() {

    lateinit var userRepository: UserRepository
        private set

    override fun onCreate() {
        super.onCreate()
        userRepository = UserRepository(
            applicationContext,
            UserDatabase.getDatabase(this).usersDao()
        )
    }
}