package edu.kcg.web3.lecture07.controller

import edu.kcg.web3.lecture07.model.Person
import org.apache.tomcat.util.codec.binary.Base64
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.client.RestTemplate


@Controller
@RequestMapping("/person")
class PeopleController(@Autowired private val restTemplate: RestTemplate) {

    private val logger = LoggerFactory.getLogger(PeopleController::class.java)

    private val serverBaseName = "http://127.0.0.1:8080"

    @RequestMapping("/{id}")
    fun getAll(model: Model, @PathVariable id: Long): String {
        try {
            val personResponse =
                restTemplate.getForEntity("$serverBaseName/people/$id", Person::class.java)
            if (personResponse.statusCode == HttpStatus.OK) {
                model["person"] = personResponse.body.toString()
            } else {
                model["person"] = "An error occurred. Status code was ${personResponse.statusCodeValue}"
                logger.warn("An error occurred while getting a person with id=$id. Status code was ${personResponse.statusCodeValue}")
            }
        } catch (e: Exception) {
            logger.error("An error occurred while getting a person with id=$id", e)
            model["person"] = "Error while getting the person"
        }
        model["title"] = "Person page"
        return "person"
    }

    @RequestMapping("/insert/{name}/{age}/{language}")
    fun insertPerson(
        model: Model, @PathVariable name: String,
        @PathVariable age: Int, @PathVariable language: String
    ): String {
        val headers = getHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val personJSON = "{\"name\":\"$name\",\"age\":$age,\"favouriteLanguage\":\"$language\"}"

        val request = HttpEntity(personJSON, headers)
        val response = restTemplate.exchange("$serverBaseName/people", HttpMethod.POST, request, Void::class.java)

        if (response.statusCode == HttpStatus.OK) {
            model["person"] = "Person inserted."
        } else {
            model["person"] = "An error occurred."
            logger.warn("An error occurred while inserting a person with name=$name, age=$age, language=$language. Status code was ${response.statusCodeValue}")
        }

        model["title"] = "Create a person"
        return "person"
    }

    @RequestMapping("/update/{id}/{name}/{age}/{language}")
    fun updatePerson(
        model: Model, @PathVariable id: String, @PathVariable name: String,
        @PathVariable age: String, @PathVariable language: String
    ): String {
        val headers = getHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val personJSON = "{\"name\":\"$name\",\"age\":$age,\"favouriteLanguage\":\"$language\",\"id\":$id}"

        val request = HttpEntity(personJSON, headers)
        val response = restTemplate.exchange("$serverBaseName/people", HttpMethod.PUT, request, Void::class.java)

        if (response.statusCode == HttpStatus.OK) {
            model["person"] = "Person updated."
        } else {
            model["person"] = "An error occurred."
            logger.warn("An error occurred while updating the person with id=$id, name=$name, age=$age, language=$language. Status code was ${response.statusCodeValue}")
        }

        model["title"] = "Update person"
        return "person"
    }

    @RequestMapping("/delete/{id}")
    fun deletePerson(model: Model, @PathVariable id: Long): String {
        val request = HttpEntity<Void>(getHeaders())
        val response = restTemplate.exchange("$serverBaseName/people/$id", HttpMethod.DELETE, request, Void::class.java)

        if (response.statusCode == HttpStatus.OK) {
            model["person"] = "Person deleted."
        } else {
            model["person"] = "An error occurred."
            logger.warn("An error occurred while deleting the person with id=$id. Status code was ${response.statusCodeValue}")
        }

        model["title"] = "Delete person"
        return "person"
    }

    private fun getHeaders(): HttpHeaders {
        val credentials = String(Base64.encodeBase64("admin:password".toByteArray()))
        val headers = HttpHeaders()
        headers.add("Authorization", "Basic $credentials")
        return headers
    }

}