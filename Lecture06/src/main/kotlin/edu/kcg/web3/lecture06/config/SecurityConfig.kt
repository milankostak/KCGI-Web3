package edu.kcg.web3.lecture06.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
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
        logger.info("Configuring security")
        http.logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/people/**").permitAll()
            .antMatchers(HttpMethod.POST, "/people/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.PUT, "/people/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/people/**").hasRole("ADMIN")
            .antMatchers("/graphql").permitAll()
            .antMatchers("/graphiql").permitAll()
            .and().formLogin()
            .and().httpBasic()
            .and().cors()
            .and().csrf().disable()
        return http.build()
    }

}
