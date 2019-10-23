package com.hsenid.jwtDemo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class PrivateController {
    @GetMapping("/private")
    fun getPublicString(): String {
        return "This is Private"
    }
}