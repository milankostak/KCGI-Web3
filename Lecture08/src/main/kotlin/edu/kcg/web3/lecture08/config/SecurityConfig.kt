package edu.kcg.web3.lecture08.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class SecurityConfig {

    private val logger = LoggerFactory.getLogger(SecurityConfig::class.java)

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        logger.info("Configuring Spring security")
        http.authorizeRequests().anyRequest().permitAll()
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//        return BCryptPasswordEncoder(13)
//        return SCryptPasswordEncoder()
        return Argon2PasswordEncoder()
        // please do not use anything else (applies in 2022, WILL change in the future)
    }

}
