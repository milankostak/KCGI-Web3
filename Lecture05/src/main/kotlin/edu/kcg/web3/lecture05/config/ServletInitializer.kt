package edu.kcg.web3.lecture05.config

import edu.kcg.web3.lecture05.Lecture05Application
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

class ServletInitializer : SpringBootServletInitializer() {

	override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(Lecture05Application::class.java)
	}

}
