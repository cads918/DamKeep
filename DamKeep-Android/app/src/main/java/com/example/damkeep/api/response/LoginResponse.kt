package com.example.damkeep.api.response

data class LoginResponse (
    val token : String,
    val user : User
)