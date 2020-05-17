package com.salesianos.dam.DamKeep.controller

import com.salesianos.dam.DamKeep.DTOs.CreateUserDTO
import com.salesianos.dam.DamKeep.DTOs.UserDTO
import com.salesianos.dam.DamKeep.DTOs.toUserDTO
import com.salesianos.dam.DamKeep.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping("/user")
class UserController(val userService: UserService) {

    @PostMapping("/register")
    fun nuevoUsuario(@RequestBody newUser : CreateUserDTO): ResponseEntity<UserDTO> =
            userService.create(newUser).map { ResponseEntity.status(HttpStatus.CREATED).body(it.toUserDTO()) }.orElseThrow {
                ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ${newUser.username} ya existe")
            }
}