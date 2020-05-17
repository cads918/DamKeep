package com.example.damkeep.api.response

data class CreateUserDTO(
    var username : String,
    var fullName : String,
    var password : String,
    var password2 : String
) {
}