package edu.kcg.web3.lecture04b.controller

import edu.kcg.web3.lecture04b.component.DatabaseSimulator
import edu.kcg.web3.lecture04b.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/person")
class PeopleController(@Autowired val databaseSimulator: DatabaseSimulator) {

    @RequestMapping("/show")
    fun showAll(model: Model): String {
        model["title"] = "All people"

        if (databaseSimulator.getAll().isEmpty()) {
            databaseSimulator.insert(Person("John", 35, "Java"))
        }
        model["people"] = databaseSimulator.getAll().sortedBy { it.age }

        if (!model.containsAttribute("error")) {
            model["error"] = ""
        }
        return "show"
    }

    @GetMapping("/insert")
    fun insertGet(model: Model): String {
        model["title"] = "Inserting person"
        model["error"] = ""
        return "insert"
    }

    @PostMapping("/insert")
    fun insertPost(
        model: Model, @RequestParam name: String?, @RequestParam age: String?, @RequestParam language: String?
    ): String {
        model["title"] = "Inserting a person"

        if (name.isNullOrBlank() || age.isNullOrBlank() || language.isNullOrBlank()) {
            model["error"] = "Some parameters were empty or blank!"
        } else {
            databaseSimulator.insert(Person(name, age.toIntOrNull() ?: -1, language))
            model["error"] = "Changes saved"
        }

        return "insert"
    }

    @GetMapping("/delete/{id}")
    fun delete(model: Model, @PathVariable id: Int): String {
//        val person = databaseSimulator.getById(id)
//        if (person != null) {
//            databaseSimulator.delete(person)
//            model["error"] = "Person ${person.name} deleted"
//        } else {
//            model["error"] = "Person not found"
//        }

        databaseSimulator.getById(id)
            ?.let {
                databaseSimulator.delete(it)
                model["error"] = "Person ${it.name} was deleted"
            }
            ?: let {
                model["error"] = "Person with id=$id not found"
            }

        model["title"] = "Delete"
        return "delete"
    }

    @GetMapping("/update/{id}")
    fun updateGet(model: Model, @PathVariable id: Int): String {
        model["title"] = "Updating person"

        databaseSimulator.getById(id)
            ?.let {
                model["person"] = it
                model["error"] = ""
            }
            ?: let {
                model["person"] = Person()
                model["error"] = "Person not found"
            }
        return "update"
    }

    @PostMapping("/update")
    fun updatePost(
        model: Model, @RequestParam id: Int?, @RequestParam age: String?, @RequestParam language: String?
    ): String {

        if (id == null || age.isNullOrBlank() || language.isNullOrBlank()) {
            model["error"] = "Update failed. Some properties were empty"
        } else {
            databaseSimulator.getById(id)?.let {
//                it.apply {
//                    this.age = age.toIntOrNull() ?: 0
//                    this.favouriteLanguage = language
//                }
                it.age = age.toIntOrNull() ?: -1
                it.favouriteLanguage = language
                databaseSimulator.update(it)
                model["error"] = "Changes saved"
            } ?: let {
                model["error"] = "Person with id=$id was not found"
            }
        }
        return showAll(model)
    }

}