package com.hsenid.jwtDemo.service

import com.hsenid.jwtDemo.dto.StudentDetailsDTO
import com.hsenid.jwtDemo.repository.StudentRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("userDetailService")
class UserDetailServiceImpl(val studentRepository: StudentRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        studentRepository.findStudentByName(username)?.let {
            print("Authenticating $it")
            return StudentDetailsDTO(
                    studentName = it.name,
                    studentPassword = it.password,
                    studentAuthorities = it.authorities
            )
        } ?: throw UsernameNotFoundException(String.format("No User found with username: $username"))
    }

}

