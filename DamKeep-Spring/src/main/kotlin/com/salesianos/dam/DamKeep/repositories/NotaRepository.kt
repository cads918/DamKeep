package com.salesianos.dam.DamKeep.repositories

import com.salesianos.dam.DamKeep.model.Nota
import com.salesianos.dam.DamKeep.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

interface NotaRepository : JpaRepository<Nota, UUID> {

    @Query("select distinct n from Nota n right join fetch n.usuario where n.usuario.id = :id")
    fun findAllNotasUsuario(id : UUID) : List<Nota>

    fun findByUsuario(user: User) : List<Nota>
}

@Component
class InitDataComponent(
        val notaRepository: NotaRepository,
        val userRepository: UserRepository,
        val encoder: PasswordEncoder
){
    @PostConstruct
    fun initData(){
        val user = User("c@gmail.com",encoder.encode("123456"),"Jesus Ceacero Jimeno","USER")
        userRepository.save(user)

        val nota1 = Nota("Compra","Hay que comprar muchas cosas para casa, tanto para la limpieza como para la comida." +
                "No hay que olvidarse de las cosas para la limpieza",user)
        notaRepository.save(nota1)

        val nota2 = Nota("Tareas","Mucho que hacer",user)
        notaRepository.save(nota2)

        val  nota3 = Nota("Cosas de casa", "limpiar y cocinar",user)
        notaRepository.save(nota3)



    }
}