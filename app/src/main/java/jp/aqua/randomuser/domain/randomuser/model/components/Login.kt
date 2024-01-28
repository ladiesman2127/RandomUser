package jp.aqua.randomuser.domain.randomuser.model.components

import com.squareup.moshi.Json

data class Login(
    @field:Json(name = UUID)
    val uuid: String,
    @field:Json(name = USERNAME)
    val username: String,
    @field:Json(name = PASSWORD)
    val password: String,
    @field:Json(name = SALT)
    val salt: String,
    @field:Json(name = MD5)
    val md5: String,
    @field:Json(name = SHA1)
    val sha1: String,
    @field:Json(name = SHA256)
    val sha256: String
) {
    companion object {
        const val UUID = "uuid"
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val SALT = "salt"
        const val MD5 = "md5"
        const val SHA1 = "sha1"
        const val SHA256 = "sha256"
    }
}