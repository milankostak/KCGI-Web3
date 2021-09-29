package edu.kcg.web3.lecture04b.config

import edu.kcg.web3.lecture04b.Lecture04bApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

class ServletInitializer : SpringBootServletInitializer() {

	override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(Lecture04bApplication::class.java)
	}

}
