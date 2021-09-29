package edu.kcg.web3.lecture08.config

import edu.kcg.web3.lecture08.Lecture08Application
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

class ServletInitializer : SpringBootServletInitializer() {

	override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(Lecture08Application::class.java)
	}

}
