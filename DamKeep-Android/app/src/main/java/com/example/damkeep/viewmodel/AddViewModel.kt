package com.example.damkeep.viewmodel

import androidx.lifecycle.LiveData
import com.example.damkeep.api.response.CreateNotaDTO
import com.example.damkeep.api.response.Nota
import com.example.damkeep.repository.DamKeepRepository
import javax.inject.Inject

class AddViewModel@Inject constructor(
    damKeepRepository: DamKeepRepository
) {

    private val repository = damKeepRepository
    private lateinit var nota : LiveData<Nota>

    fun nuevaNota (notaAdd : CreateNotaDTO) : LiveData<Nota>{
        nota = repository.nuevaNota(notaAdd)
        return nota
    }
}