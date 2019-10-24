package com.hsenid.jwtDemo.controller

import com.hsenid.jwtDemo.dto.StudentRequest
import com.hsenid.jwtDemo.model.Student
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class PublicController {
    @GetMapping("/public")
    fun getPublicString(): String {
        return "This is Public"
    }

    @GetMapping("/body")
    fun getBody(): String {
        return "studentRequest"
    }


}