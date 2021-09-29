package edu.kcg.web3.lecture06.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    private val logger = LoggerFactory.getLogger(SecurityConfig::class.java)

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        logger.info("Configuring in-memory authentication")
        auth.inMemoryAuthentication()
            .withUser("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .and()
            .withUser("admin")
            .password(passwordEncoder().encode("password"))
            .roles("ADMIN")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//        return BCryptPasswordEncoder(13)
//        return SCryptPasswordEncoder()
        return Argon2PasswordEncoder()
        // please do not use anything else (applies in 2021, WILL change in the future)
    }

    override fun configure(http: HttpSecurity) {
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
    }

}
