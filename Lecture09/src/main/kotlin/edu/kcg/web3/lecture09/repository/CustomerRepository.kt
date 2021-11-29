package edu.kcg.web3.lecture09.repository

import edu.kcg.web3.lecture09.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long?> {

    @Query("SELECT c FROM customer c WHERE c.email LIKE :email")
    fun findByEmail(@Param("email") email: String): Customer?

}
