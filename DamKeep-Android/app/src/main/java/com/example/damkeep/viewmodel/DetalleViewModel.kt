package com.example.damkeep.viewmodel

import androidx.lifecycle.LiveData
import com.example.damkeep.api.response.Nota
import com.example.damkeep.repository.DamKeepRepository
import javax.inject.Inject

class DetalleViewModel @Inject constructor(
    damKeepRepository: DamKeepRepository
) {

    private val repository = damKeepRepository
    private lateinit var nota : LiveData<Nota>

    fun notaById (id:String): LiveData<Nota>{
        nota = repository.notaById(id)
        return nota
    }

    fun borrarNota (id: String) {
        repository.borrarNota(id)
    }
}