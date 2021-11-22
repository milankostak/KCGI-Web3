package edu.kcg.web3.lecture08.controller

import edu.kcg.web3.lecture08.repository.PersonRepository
import edu.kcg.web3.lecture08.table.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/person")
class PeopleController(
    @Autowired val personRepository: PersonRepository,
    @Autowired val passwordEncoder: PasswordEncoder,
) {

    @GetMapping("/show")
    fun showAll(model: Model, @RequestParam showDeleted: Boolean? = null): String {
        model["title"] = "All people"

        val people = if (showDeleted == null || showDeleted == true) {
            personRepository.findAll()
        } else {
            personRepository.findAllValid()
        }
        model["people"] = people

        if (!model.containsAttribute("message")) {
            model["message"] = ""
        }
        return "show"
    }

    @GetMapping("/insert")
    fun insertGet(model: Model): String {
        model["title"] = "Inserting person"
        model["message"] = ""
        return "insert"
    }

    @PostMapping("/insert")
    fun insertPost(
        model: Model, @RequestParam email: String?, @RequestParam password: String?, @RequestParam age: String?
    ): String {
        return if (email.isNullOrBlank() || password.isNullOrBlank() || age.isNullOrBlank()) {
            model["message"] = "Some properties were empty or blank!"
            model["title"] = "Inserting person"
            "insert"
        } else {
            val person = Person().also {
                it.email = email
                it.passwordHash = passwordEncoder.encode(password)
                it.age = age.toLongOrNull() ?: 0
            }
            personRepository.save(person)
            model["message"] = "Changes saved"
            showAll(model)
        }
    }

    @GetMapping("/permanent-delete/{id}")
    fun permanentDelete(model: Model, @PathVariable id: Long): String {
        personRepository.deleteById(id)
        model["title"] = "Deleted"
        return "delete"
    }

    @GetMapping("/delete/{id}")
    fun delete(model: Model, @PathVariable id: Long): String {
        personRepository.markAsDeleted(id)
        model["title"] = "Deleted"
        return "delete"
    }

    @GetMapping("/update/{id}")
    fun updateGet(model: Model, @PathVariable id: Long): String {
        model["title"] = "Updating person"

        val personOptional = personRepository.findById(id)
        if (personOptional.isEmpty) {
            model["person"] = Person()
            model["message"] = "Person not found"
        } else {
            model["person"] = personOptional.get()
            model["message"] = ""
        }
        return "update"
    }

    @PostMapping("/update")
    fun updatePost(
        model: Model,
        @RequestParam id: Long?,
        @RequestParam email: String?,
        @RequestParam password: String?,
        @RequestParam age: String?
    ): String {

        if (id == null || email.isNullOrBlank() || age.isNullOrBlank()) {
            model["message"] = "Update failed. Some properties were empty or blank!"
        } else {
            personRepository.findByIdOrNull(id)?.let {
                it.email = email
                if (password?.isNotBlank() == true) {
                    it.passwordHash = passwordEncoder.encode(password)
                }
                it.age = age.toLongOrNull() ?: 0
                personRepository.save(it)
                model["message"] = "Changes saved"
            } ?: let {
                model["message"] = "Person with id=$id was not found"
            }
        }
        return showAll(model)
    }

}