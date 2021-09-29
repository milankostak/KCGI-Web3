package edu.kcg.web3.lecture07.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate


@Configuration
class RestTemplateConfig {

    @Bean(name = ["restTemplate"])
    fun prepareRestTemplate(): RestTemplate {
        return RestTemplate()
    }

}