package com.umc.history

data class User(
    var id : String?,
    var nickName : String?,
    var password : String?,
    val authority : String = "ROLE_USER"
)
data class Login(
    var id : String,
    var password: String
)
