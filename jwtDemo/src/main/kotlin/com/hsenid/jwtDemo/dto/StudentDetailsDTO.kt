package com.hsenid.jwtDemo.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class StudentDetailsDTO(
        val studentName : String,
        val studentPassword : String,
        val studentAuthorities : String
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return  AuthorityUtils.commaSeparatedStringToAuthorityList(studentAuthorities)
    }

    override fun isEnabled(): Boolean {
        return true;
    }

    override fun getUsername(): String {
       return studentName
    }

    override fun isCredentialsNonExpired(): Boolean {
       return true
    }

    override fun getPassword(): String {
       return studentPassword
    }

    override fun isAccountNonExpired(): Boolean {
      return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}