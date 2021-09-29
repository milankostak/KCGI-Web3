package edu.kcg.web3.lecture05.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class HomeController {

    private val logger = LoggerFactory.getLogger(HomeController::class.java)

    @RequestMapping("/")
    fun index(model: Model): String {
        logger.trace("TRACE message")
        logger.debug("DEBUG message")
        logger.info("INFO message")
        logger.warn("WARN message")
        logger.error("ERROR message")

        try {
            throw RuntimeException("test exception")
        } catch (e: Exception) {
            logger.error("Error when accessing index page", e)
        }

        model["title"] = "Index page"
        return "index"
    }

    @RequestMapping("/home")
    fun home(model: Model): String {
        model["title"] = "Home page"
        return "home"
    }

    @RequestMapping("/admin")
    fun admin(model: Model): String {
        model["title"] = "Admin page"
        return "admin"
    }

    @GetMapping("/greeting")
    fun greeting(
        model: Model,
        @RequestParam(name = "name", required = false, defaultValue = "World") name: String?
    ): String {
        logger.info("name parameter is $name")
        model["title"] = "Greeting page"
        model.addAttribute("name", name)
        return "greeting"
    }

    @PostMapping("/405-test")
    fun test405(model: Model): String {
        model["title"] = "Greeting page"
        return "index"
    }

    @GetMapping("/500-test")
    fun test500(model: Model): String {
        // uncomment to solve the intentional 500 error
//        model["title"] = "Greeting page"
        return "index"
    }

}
