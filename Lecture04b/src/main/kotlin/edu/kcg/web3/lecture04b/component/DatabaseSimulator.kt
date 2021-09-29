package edu.kcg.web3.lecture04b.component

import edu.kcg.web3.lecture04b.model.Person
import org.springframework.stereotype.Component

@Component
class DatabaseSimulator {

    private val people = mutableListOf<Person>()
    private var idCounter = 0

    fun getById(id: Int): Person? {
        return people.find { it.id == id }
    }

    fun getAll(): List<Person> {
        return people
    }

    fun insert(person: Person): Int {
        if (person.id <= 0) {
            person.id = ++idCounter
            people.add(person)
        } else {
            update(person)
        }
        return person.id
    }

    fun delete(person: Person) {
        people.remove(person)
    }

    fun update(person: Person) {
        if (person.id <= 0) {
            insert(person)
        } else {
//            val personDB = getById(person.id)
//            if (personDB != null) {
//                personDB.age = person.age
//                personDB.favouriteLanguage = person.favouriteLanguage
//            }
            people.indexOfFirst { it.id == person.id }.let { index ->
                if (index >= 0) {
                    people[index] = person
                }
            }
        }
    }

}