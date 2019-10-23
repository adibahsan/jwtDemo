package com.hsenid.jwtDemo.model


import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Student (
        @Id
        @GeneratedValue
        val id : Long?,
        val name : String,
        val password : String,
        val authorities: String
)
