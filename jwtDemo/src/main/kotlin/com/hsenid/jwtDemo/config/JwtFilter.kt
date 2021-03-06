package com.hsenid.jwtDemo.config

import com.hsenid.jwtDemo.util.TokenUtility
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import java.io.PrintWriter
import java.lang.Exception
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter : UsernamePasswordAuthenticationFilter() {


    @Autowired
    lateinit var tokenUtility : TokenUtility

    private  val tokenHeader = "Authorization"


    override fun doFilter(req: ServletRequest?, res: ServletResponse?, chain: FilterChain?) {

        val httpRequest = req as HttpServletRequest
        val httpResponse = res as HttpServletResponse

        httpRequest.getHeader(tokenHeader)?.let {
            println("Header is Found")
            try {
                val studentDetails = tokenUtility.getDetailsFromToken(it)
                val authentications = UsernamePasswordAuthenticationToken(studentDetails,
                        null, studentDetails.authorities)
                authentications.details = WebAuthenticationDetailsSource().buildDetails(httpRequest)
                SecurityContextHolder.getContext().authentication = authentications
            } catch(e:Exception)
            {
                e.printStackTrace()
               // val out : PrintWriter = res.writer


                httpResponse.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,"Token Is Not Found")
//                httpResponse.contentType = "application/json"
//                out.println("Awesome")
//                out.flush()
               // res.sendRedirect("http://localhost:8181/body")
            }

        }
      //  super.doFilter(req, res, chain)



        chain?.doFilter(req,httpResponse)
    }
}