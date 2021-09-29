package edu.kcg.web3.lecture08.repository

import edu.kcg.web3.lecture08.table.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PersonRepository : JpaRepository<Person, Long?> {

    @Query("SELECT p FROM Person p WHERE p.deleted = false")
    fun findAllValid(): List<Person>

    @Query("SELECT p FROM Person p WHERE p.email LIKE :email")
    fun findByEmail(@Param("email") email: String): Person?

    @Modifying
    @Transactional
    @Query("UPDATE Person SET deleted = true WHERE id = :id")
    fun markAsDeleted(@Param("id") id: Long)

}
