package com.salesianos.dam.DamKeep.DTOs

import com.salesianos.dam.DamKeep.model.User
import java.util.*

data class UserDTO (
        var username : String,
        var fullName: String,
        var roles: String,
        val id: UUID? = null
)

fun User.toUserDTO() = UserDTO(username, fullName, roles.joinToString(), id)
