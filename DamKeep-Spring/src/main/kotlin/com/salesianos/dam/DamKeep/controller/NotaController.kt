package com.salesianos.dam.DamKeep.controller

import com.salesianos.dam.DamKeep.DTOs.NotaDTO
import com.salesianos.dam.DamKeep.DTOs.toNota
import com.salesianos.dam.DamKeep.DTOs.toNotaDTO
import com.salesianos.dam.DamKeep.model.Nota
import com.salesianos.dam.DamKeep.model.User
import com.salesianos.dam.DamKeep.repositories.NotaRepository
import com.salesianos.dam.DamKeep.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/notas")
class NotaController(
        val notaRepository: NotaRepository,
        val userRepository: UserRepository
) {
    @GetMapping("/")
    fun todas(@AuthenticationPrincipal user : User): List<NotaDTO> {
        var notas : List<Nota>
        notas = notaRepository.findByUsuario(user)
        if (notas.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No hay notas guardadas pra ese usuario.")
        return notas.map { it ->it.toNotaDTO() }
    }

    @GetMapping("/{id}")
    fun notaId(@PathVariable id : UUID): NotaDTO {
        return notaRepository.findById(id).map { nota -> nota.toNotaDTO() }.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND,"Nota no encontrada")
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarNota(@PathVariable id : UUID) : ResponseEntity<Void> {
        notaRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
    @PostMapping("/")
    fun nuevaNota(@RequestBody nuevaNota: NotaDTO,
                   @AuthenticationPrincipal user : User) =
            ResponseEntity.status(HttpStatus.CREATED).body(notaRepository.save(nuevaNota.toNota(user)).toNotaDTO())


    @PutMapping("/{id}")
    fun editarNota(@RequestBody editarNota: NotaDTO, @PathVariable id : UUID): NotaDTO {
        return notaRepository.findById(id)
                .map { nuevaNota  ->
                    val notaActualizada : Nota =
                            nuevaNota.copy(titulo = editarNota.titulo,
                                    fecha_modi = LocalDate.now(),
                                    contenido = editarNota.contenido)
                    notaRepository.save(notaActualizada).toNotaDTO()
                }.orElseThrow {
                    ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el identificador $id")
                }
    }
}