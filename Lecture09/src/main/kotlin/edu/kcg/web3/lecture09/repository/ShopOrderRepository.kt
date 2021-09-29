package edu.kcg.web3.lecture09.repository

import edu.kcg.web3.lecture09.entity.ShopOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShopOrderRepository : JpaRepository<ShopOrder, Long?>