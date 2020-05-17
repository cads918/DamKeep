package com.example.damkeep.api


import com.example.damkeep.api.response.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface DamKeepService {

    @POST("/auth/login")
    fun login(@Body loginDTO: LoginDTO): Call<LoginResponse>

    @POST("/user/register")
    fun register(@Body createUserDTO: CreateUserDTO) : Call<User>

    @GET("/notas/")
    fun listadoNotas() : Call<List<Nota>>

    @GET("/notas/{id}")
    fun notaById(@Path("id")id: String) : Call<Nota>

    @DELETE("/notas/{id}")
    fun borrarNota(@Path("id")id: String) : Call<Void>

    @PUT("/notas/{id}")
    fun editarNota(@Path("id")id: String,
                   @Body nota: Nota ) : Call<Nota>

    @POST("/notas/")
    fun nuevaNota(@Body nota : CreateNotaDTO) : Call<Nota>
}