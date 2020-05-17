package com.example.damkeep.api.response

data class Nota(
    var titulo: String,
    var contenido: String,
    val fecha_creacion: String,
    val fecha_modi: String,
    val id: String
)