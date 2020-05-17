package com.salesianos.dam.DamKeep.DTOs

import com.fasterxml.jackson.annotation.JsonFormat
import com.salesianos.dam.DamKeep.model.Nota
import com.salesianos.dam.DamKeep.model.User
import java.time.LocalDate
import java.util.*

data class NotaDTO (
        var titulo : String,
        var contenido : String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        val fecha_creacion: LocalDate? = LocalDate.now(),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        var fecha_modi : LocalDate? = null,
        val id: UUID? = null
)

fun Nota.toNotaDTO() = NotaDTO(titulo,contenido,fecha_creacion,fecha_modi,id)

fun NotaDTO.toNota(user : User) = Nota(titulo,contenido,user)