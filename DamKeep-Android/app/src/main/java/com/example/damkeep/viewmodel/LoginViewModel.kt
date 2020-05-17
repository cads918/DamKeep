package com.example.damkeep.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.damkeep.api.response.CreateUserDTO
import com.example.damkeep.api.response.LoginDTO
import com.example.damkeep.api.response.LoginResponse
import com.example.damkeep.api.response.User
import com.example.damkeep.repository.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(loginRepository: LoginRepository) : ViewModel(){

    private val repository = loginRepository
    private lateinit var usuarioLogueado : LiveData<LoginResponse>
    private lateinit var usuarioRegistrado : LiveData<User>

    fun login(loginDTO: LoginDTO) : LiveData<LoginResponse>{
        usuarioLogueado = repository.login(loginDTO)
        return usuarioLogueado
    }

    fun register(createUserDTO: CreateUserDTO) : LiveData<User>{
        usuarioRegistrado = repository.register(createUserDTO)
        return usuarioRegistrado
    }
}