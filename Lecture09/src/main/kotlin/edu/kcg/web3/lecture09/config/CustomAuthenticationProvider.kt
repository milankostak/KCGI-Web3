package edu.kcg.web3.lecture09.config

import edu.kcg.web3.lecture09.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider(
    @Autowired private val customerRepository: CustomerRepository,
    @Autowired private val passwordEncoder: PasswordEncoder,
) : AuthenticationProvider {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val email = authentication.name
        val password = authentication.credentials.toString()
        val customer = customerRepository.findByEmail(email)

        if (customer?.password?.isNotEmpty() == true && passwordEncoder.matches(password, customer.password)) {
            val authorities = mutableListOf<SimpleGrantedAuthority>()
            authorities.add(SimpleGrantedAuthority("ADMIN"))
            return UsernamePasswordAuthenticationToken(email, password, authorities)
        } else {
            throw BadCredentialsException("Bad credentials. Please try again.")
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }

}