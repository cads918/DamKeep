package com.example.damkeep.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.damkeep.api.response.Nota
import com.example.damkeep.repository.DamKeepRepository
import javax.inject.Inject

class ListadoNotasViewModel @Inject constructor(
    damKeepRepository: DamKeepRepository
) : ViewModel(){

    private val repository = damKeepRepository
    private lateinit var listado : LiveData<List<Nota>>

    fun listadoNotas() : LiveData<List<Nota>>{
        listado = repository.listadoNotas()
        return listado
    }
}