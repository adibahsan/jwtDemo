package com.hsenid.jwtDemo

import com.hsenid.jwtDemo.model.Student
import com.hsenid.jwtDemo.repository.StudentRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class JwtDemoApplication{
	@Bean
	fun initializer(appUserRepository: StudentRepository) = CommandLineRunner {
		appUserRepository.save(Student(
				id = null,
				name = "adib",
				password = BCryptPasswordEncoder().encode("1212"),
				authorities = "ROLE_ADMIN, ROLE_EMPLOYEE, ROLE_MANAGER"))

		appUserRepository.save(Student(
				id = null,
				name = "sakib",
				password = BCryptPasswordEncoder().encode("1234"),
				authorities = "ROLE_ADMIN"))
	}
}


fun main(args: Array<String>) {
	runApplication<JwtDemoApplication>(*args)
}
