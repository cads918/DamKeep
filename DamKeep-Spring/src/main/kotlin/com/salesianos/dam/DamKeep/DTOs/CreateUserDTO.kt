package com.salesianos.dam.DamKeep.DTOs

data class CreateUserDTO(
        var username: String,
        var fullName: String,
        val password: String,
        val password2: String
)