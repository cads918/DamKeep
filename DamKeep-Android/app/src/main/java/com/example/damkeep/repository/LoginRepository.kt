package com.example.damkeep.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.damkeep.api.DamKeepService
import com.example.damkeep.api.response.CreateUserDTO
import com.example.damkeep.api.response.LoginDTO
import com.example.damkeep.api.response.LoginResponse
import com.example.damkeep.api.response.User
import com.example.damkeep.di.MyApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(var damKeepService: DamKeepService){

    fun login(loginDTO: LoginDTO) : MutableLiveData<LoginResponse>{
        var usuario : MutableLiveData<LoginResponse> = MutableLiveData<LoginResponse>()
        val call : Call<LoginResponse>? = damKeepService.login(loginDTO)
        call?.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful) {
                    usuario.value = response.body()
                }else{
                    Log.i("login","Error al recibir el usuario logueado / "+ response.body())
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("login","Error en la petición de login / " + t.message)
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_LONG).show()
            }
        })
        return usuario
    }

    fun register (createUserDTO: CreateUserDTO) : MutableLiveData<User>{
        var usuario : MutableLiveData<User> = MutableLiveData<User>()
        val call : Call<User>? = damKeepService.register(createUserDTO)
        call?.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful) {
                    usuario.value = response.body()
                }else{
                    Log.i("login","Error al recibir el usuario registro / "+ response.body())
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("login", "Error en la petición de registro / " + t.message)
            }
        })
        return usuario
    }
}