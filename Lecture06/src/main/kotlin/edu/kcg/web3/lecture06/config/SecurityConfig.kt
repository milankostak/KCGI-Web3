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
        http.logout() // configure logout for the system
            .logoutUrl("/logout") // for logout the URl will be /logout
            .logoutSuccessUrl("/") // after logging out, redirect user to URL / (which means to index page)
            .invalidateHttpSession(true) // also invalidate the logged-out user's session
            .and() // everything done for logout, going to another configuration section
            .authorizeRequests()// configure authorizations
            .antMatchers(HttpMethod.GET, "/people/**").permitAll()
            .antMatchers(HttpMethod.POST, "/people/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.PUT, "/people/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/people/**").hasRole("ADMIN")
            .antMatchers("/graphql").permitAll()
            .antMatchers("/graphiql").permitAll()
            .and().formLogin() // configure special login page with a login form
            .and().httpBasic() // configure a way to authorize with BASIC authorization (used for REST endpoints)
            .and().cors()
            .and().csrf().disable() // disabled CSRF for this project
        return http.build()
    }

}
