package com.example.damkeep.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.damkeep.api.DamKeepService
import com.example.damkeep.api.response.CreateNotaDTO
import com.example.damkeep.api.response.Nota
import com.example.damkeep.di.MyApp
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DamKeepRepository @Inject constructor(var damKeepService: DamKeepService) {

    fun listadoNotas() : MutableLiveData<List<Nota>>{
        var listado : MutableLiveData<List<Nota>> = MutableLiveData<List<Nota>>()
        val call : Call<List<Nota>>? = damKeepService.listadoNotas()
        call?.enqueue(object : Callback<List<Nota>>{
            override fun onResponse(call: Call<List<Nota>>, response: Response<List<Nota>>) {
                if(response.isSuccessful) {
                    listado.value = response.body()
                }else{
                    Log.i("listado","Error al recibir el listado de notas / "+ response.body())
                }
            }
            override fun onFailure(call: Call<List<Nota>>, t: Throwable) {
                Log.e("login","Error en la petición de listado de notas / " + t.message)
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_LONG).show()
            }
        })
        return listado
    }

    fun notaById (id: String) : MutableLiveData<Nota>{
        var nota : MutableLiveData<Nota> = MutableLiveData<Nota>()
        val call : Call<Nota>? = damKeepService.notaById(id)
        call?.enqueue(object : Callback<Nota>{
            override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
                if(response.isSuccessful) {
                    nota.value = response.body()
                }else{
                    Log.i("notaID","Error al recibir la nota / "+ response.body())
                }
            }
            override fun onFailure(call: Call<Nota>, t: Throwable) {
                Log.e("notaID","Error en la petición de nota / " + t.message)
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_LONG).show()
            }
        })
        return nota
    }

    fun borrarNota (id:String) {
        val call : Call<Void> = damKeepService.borrarNota(id)
        call.enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful) {
                    Log.i("Borrado nota","Nota borrada correctamente / ")
                }else{
                    Log.e("Borrado nota","Error al borrara la nota / "+ response.body())
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("Borrado nota","Error en la petición de borrado de nota / " + t.message)
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun editarNota (id : String, nota: Nota) : MutableLiveData<Nota>{
        var notaEditada : MutableLiveData<Nota> = MutableLiveData<Nota>()
        val call : Call<Nota>? = damKeepService.editarNota(id,nota)
        call?.enqueue(object :Callback<Nota>{
            override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
                if(response.isSuccessful) {
                    notaEditada.value = response.body()
                }else{
                    Log.e("Editado nota","Error al editar la nota / "+ response.body())
                }
            }
            override fun onFailure(call: Call<Nota>, t: Throwable) {
                Log.e("Editado nota","Error en la petición de editado de nota / " + t.message)
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_LONG).show()
            }
        })
        return notaEditada
    }

    fun nuevaNota (nota : CreateNotaDTO) : MutableLiveData<Nota>{
        var notaNueva : MutableLiveData<Nota> = MutableLiveData<Nota>()
        val call : Call<Nota>? = damKeepService.nuevaNota(nota)
        call?.enqueue(object : Callback<Nota>{
            override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
                if(response.isSuccessful) {
                    notaNueva.value = response.body()
                }else{
                    Log.e("Nueva nota","Error al crear la nota / "+ response.body())
                }
            }
            override fun onFailure(call: Call<Nota>, t: Throwable) {
                Log.e("Nueva nota","Error en la petición de creado de nota / " + t.message)
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_LONG).show()
            }
        })
        return notaNueva
    }
}