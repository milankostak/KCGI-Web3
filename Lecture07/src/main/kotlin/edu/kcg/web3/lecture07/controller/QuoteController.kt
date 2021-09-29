package edu.kcg.web3.lecture07.controller

import edu.kcg.web3.lecture07.model.QuoteContainer
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@Controller
class QuoteController {

    private val logger = LoggerFactory.getLogger(QuoteController::class.java)

    @RequestMapping("/quote")
    fun getQuote(model: Model): String {
        try {
            val quoteResponse =
                RestTemplate().getForEntity("https://quoters.apps.pcfone.io/api/random", QuoteContainer::class.java)

            if (quoteResponse.statusCode == HttpStatus.OK) {
                val quoteContainer = quoteResponse.body
                val value = quoteContainer?.value?.quote ?: "error"
                model["quote"] = value
            } else {
                model["quote"] = "An error occurred. Status code was ${quoteResponse.statusCodeValue}"
            }
        } catch (e: RestClientException) {
            logger.error("An error occurred when getting a quote from the API", e)
            model["quote"] = ""
        }

        model["title"] = "Quote page"
        return "quote"
    }

}