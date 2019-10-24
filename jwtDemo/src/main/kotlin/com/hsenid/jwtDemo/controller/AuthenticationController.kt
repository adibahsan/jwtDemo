package com.hsenid.jwtDemo.controller

import com.hsenid.jwtDemo.dto.StudentRequest
import com.hsenid.jwtDemo.util.TokenUtility
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthenticationController(
        val authenticationManager: AuthenticationManager,
        val tokenUtility: TokenUtility,
        val userDetailsService: UserDetailsService
) {
    @PostMapping
    fun authenticationRequest(@RequestBody studentRequest: StudentRequest): String {
        val thisAuthenticate =  authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        studentRequest.name,
                        studentRequest.password
                )
        )
        SecurityContextHolder.getContext().authentication = thisAuthenticate
        val token = tokenUtility.generateToken(userDetailsService.loadUserByUsername(studentRequest.name))
       return "$thisAuthenticate\ntoken : $token\n"
    }

}