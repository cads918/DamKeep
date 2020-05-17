package com.salesianos.dam.DamKeep.repositories

import com.salesianos.dam.DamKeep.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {

    fun findByUsername(username : String) : Optional<User>

}