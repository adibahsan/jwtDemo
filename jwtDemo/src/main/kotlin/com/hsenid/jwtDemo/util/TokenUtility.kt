package com.hsenid.jwtDemo.util

import com.hsenid.jwtDemo.dto.StudentDetailsDTO
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.util.*
import kotlin.collections.HashMap

@Component
class TokenUtility {
    val tokenSecret : String ="hsenidMobile"

    fun generateToken(userDetails: UserDetails): String? {
    val tokenClaims = HashMap<String,Any>()
        tokenClaims["enable"] = userDetails.isEnabled
        tokenClaims["created"] = Date()
        tokenClaims["audience"] = "mobile"
        tokenClaims["authorities"] = ""

        return Jwts.builder()
                .setSubject(userDetails.username)
                .setClaims(tokenClaims)
                .signWith(SignatureAlgorithm.HS512,tokenSecret )
                .compact()

    }


}
