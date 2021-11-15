package edu.kcg.web3.lecture07.config

import org.slf4j.LoggerFactory
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    private val logger = LoggerFactory.getLogger(SecurityConfig::class.java)

    override fun configure(http: HttpSecurity) {
        logger.info("Configuring Spring security")
        http.authorizeRequests().anyRequest().permitAll()
    }

}
