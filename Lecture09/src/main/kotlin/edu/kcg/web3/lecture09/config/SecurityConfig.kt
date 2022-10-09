package edu.kcg.web3.lecture09.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class SecurityConfig {

    private val logger = LoggerFactory.getLogger(SecurityConfig::class.java)

    @Autowired
    private val authProvider: CustomAuthenticationProvider? = null

    @Autowired
    fun configAuthentication(auth: AuthenticationManagerBuilder) {
        logger.info("Registering AuthenticationProvider")
        auth.authenticationProvider(authProvider)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        logger.info("Configuring Spring security")
        http.logout() // configuring logout page
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            // ending configuring logout page and continuing
            .and()
            // configuring authorization
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            // ending configuring authorization and continuing
            .and()
            // allowing default login page at /login URL
            // with automatic redirection
            .formLogin()
        return http.build()
    }

}
