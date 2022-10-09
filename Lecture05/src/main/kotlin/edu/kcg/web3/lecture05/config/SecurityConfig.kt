package edu.kcg.web3.lecture05.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class SecurityConfig {

    private val logger = LoggerFactory.getLogger(SecurityConfig::class.java)

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder, passwordEncoder: PasswordEncoder) {
        logger.info("Configuring in-memory authentication")
        auth.inMemoryAuthentication()
            .withUser("user")
            .password(passwordEncoder.encode("password"))
            .roles("USER")
            .and()
            .withUser("admin")
            .password(passwordEncoder.encode("password"))
            .roles("ADMIN")
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
            // /home can be accessed by anyone with a role USER or ADMIN
            .antMatchers("/home").hasAnyRole("USER", "ADMIN")
            // /admin and all its subpages can be accessed by anyone with a role ADMIN
            .antMatchers("/admin/**").hasRole("ADMIN")
            // root page (index) can be access by anyone even without logging in
            .antMatchers("/").permitAll()
            // ending configuring authorization and continuing
            .and()
            // allowing default login page at /login URL
            // with automatic redirection
            .formLogin()
        return http.build()
    }

}
