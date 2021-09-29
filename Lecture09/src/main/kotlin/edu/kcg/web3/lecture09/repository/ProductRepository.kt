package edu.kcg.web3.lecture09.repository

import edu.kcg.web3.lecture09.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long?>