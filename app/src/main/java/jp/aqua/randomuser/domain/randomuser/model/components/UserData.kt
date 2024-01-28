package jp.aqua.randomuser.domain.randomuser.model.components

import com.squareup.moshi.Json

data class UserData(
    @field:Json(name = GENDER)
    val gender: String,
    @field:Json(name = NAME)
    val name: Name,
    @field:Json(name = LOCATION)
    val location: Location,
    @field:Json(name = EMAIL)
    val email: String,
    @field:Json(name = LOGIN)
    val login: Login,
    @field:Json(name = DOB)
    val dob: DateAndAge,
    @field:Json(name = REGISTERED)
    val registered: DateAndAge,
    @field:Json(name = PHONE)
    val phone: String,
    @field:Json(name = CELL)
    val cell: String,
    @field:Json(name = USER_ID)
    val userId: ID,
    @field:Json(name = PICTURE)
    val picture: Picture,
    @field:Json(name = NAT)
    val nat: String
) {
    companion object {
        const val GENDER = "gender"
        const val NAME = "name"
        const val LOCATION = "location"
        const val EMAIL = "email"
        const val LOGIN = "login"
        const val DOB = "dob"
        const val REGISTERED = "registered"
        const val PHONE = "phone"
        const val CELL = "cell"
        const val USER_ID = "id"
        const val PICTURE = "picture"
        const val NAT = "nat"
    }
}

fun UserData.fullName(): String {
    return "${name.title} ${name.first} ${name.last}"
}

fun UserData.coordinatesToString(): String {
    return location.coordinates.latitude + "," + location.coordinates.longitude
}

fun UserData.addressToString(): String {
    return location.postcode + " " + location.country + " " +
            location.state + " " + location.city + "\n" +
            location.street.name + " " + location.street.number.toString()
}

fun UserData.loginToString(): String {
    return login.uuid + "\n" +
            login.username + "\n" +
            login.password + "\n" +
            login.salt + "\n" +
            login.md5 + "\n" +
            login.sha1 + "\n" +
            login.sha256 + "\n"
}

fun UserData.dobToString(): String {
    return dob.date + "\n" + dob.age
}

fun UserData.registeredToString(): String {
    return registered.date + "\n" + registered.age
}

fun UserData.idToString(): String {
    return userId.name + " " + userId.value
}

