package edu.kcg.web3.lecture06.component

import edu.kcg.web3.lecture06.model.Person
import org.springframework.stereotype.Component

@Component
class PeopleDatabaseSimulator {

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

    fun delete(person: Person): Boolean {
        return people.remove(person)
    }

    fun update(person: Person) {
        if (person.id <= 0) {
            insert(person)
        } else {
//            // very Kotlin-like way
//            people.indexOfFirst { it.id == person.id }.let { index ->
//                if (index >= 0) {
//                    people[index] = person
//                }
//            }
//            // very Kotlin-like way without if
//            people.indexOfFirst { it.id == person.id }.takeIf { it >= 0 }?.let { people[it] = person }
//            // very Java-like way
//            val personDB = getById(person.id)
//            if (personDB != null) {
//                personDB.age = person.age
//                personDB.favouriteLanguage = person.favouriteLanguage
//            }
            // somewhere in the middle - readable Kotlin-like way
            getById(person.id)?.let {
                it.age = person.age
                it.favouriteLanguage = person.favouriteLanguage
            }
        }
    }

}