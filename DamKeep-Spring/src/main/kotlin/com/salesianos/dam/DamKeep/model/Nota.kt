package com.salesianos.dam.DamKeep.model

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Nota(
        var titulo : String,
        var contenido : String,
        @JsonBackReference @ManyToOne
        val usuario : User? = null,
        val fecha_creacion: LocalDate? = LocalDate.now(),
        var fecha_modi : LocalDate? = null,
        @Id @GeneratedValue val id: UUID? = null
)