package com.hsenid.jwtDemo.repository

import com.hsenid.jwtDemo.model.Student
import org.springframework.data.jpa.repository.JpaRepository



interface StudentRepository : JpaRepository<Student,Long>{
    fun findStudentByName(username: String) : Student?
}