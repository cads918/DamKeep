package com.example.damkeep.viewmodel

import androidx.lifecycle.LiveData
import com.example.damkeep.api.response.Nota
import com.example.damkeep.repository.DamKeepRepository
import javax.inject.Inject

class EditViewModel@Inject constructor(
    damKeepRepository: DamKeepRepository
) {
    private val repository = damKeepRepository
    private lateinit var notaEditada : LiveData<Nota>

    fun editarNota (id : String, nota : Nota) : LiveData<Nota>{
        notaEditada = repository.editarNota(id,nota)
        return notaEditada
    }
}