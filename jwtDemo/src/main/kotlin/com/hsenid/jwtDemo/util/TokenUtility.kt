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
    val algorithm = SignatureAlgorithm.HS512

    fun generateToken(userDetails: UserDetails): String? {
    val tokenClaims = HashMap<String,Any>()
        tokenClaims["enable"] = userDetails.isEnabled
        tokenClaims["created"] = Date()
        tokenClaims["audience"] = "mobile"
        tokenClaims["authorities"] = ""

        return Jwts.builder()
                .setSubject(userDetails.username)
                .setClaims(tokenClaims)
                .signWith(algorithm,tokenSecret )
                .compact()

    }

    fun getClaimsFromToken (token:String):Claims{
        return Jwts.parser()
                .setSigningKey(this.tokenSecret)
                .parseClaimsJws(token)
                .body?: throw RuntimeException("Failed to Retrieve Claims from the Token")
    }


    fun getUsernameFromToken(token: String) : String {
       return getClaimsFromToken(token).subject ?: throw RuntimeException("Username Not Found")
    }

    fun getExpirationDateFromToken(token: String): Date? {
        return getClaimsFromToken(token).expiration
    }

    fun getDetailsFromToken(token:String) :UserDetails {
        return  StudentDetailsDTO(
                getUsernameFromToken(token),
                "",
                getClaimsFromToken(token)["authorities"] as String
        )

    }

}
