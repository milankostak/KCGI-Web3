package edu.kcg.web3.lecture09.controller

import edu.kcg.web3.lecture09.entity.ShopOrder
import edu.kcg.web3.lecture09.repository.CustomerRepository
import edu.kcg.web3.lecture09.repository.ProductRepository
import edu.kcg.web3.lecture09.repository.ShopOrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/orders")
class OrderController(
    @Autowired private val shopOrderRepository: ShopOrderRepository,
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val customerRepository: CustomerRepository,
) {

    @RequestMapping
    fun showAllOrders(model: Model): String {
        model["title"] = "Products page"
        model["orders"] = shopOrderRepository.findAll().sortedBy { it.id }
        return "orders"
    }

    @RequestMapping("/new")
    fun newOrder(model: Model): String {
        val shopOrder = ShopOrder()

        val email = SecurityContextHolder.getContext().authentication.principal as String
        val customer = customerRepository.findByEmail(email) ?: throw RuntimeException() // only a quick solution
        shopOrder.customer = customer

        var totalPrice = 0L
        val products = productRepository.findAll()
        repeat(2) {
            products.randomOrNull()?.let {
                shopOrder.products.add(it)
                totalPrice += it.price
            }
        }
        shopOrder.totalPrice = totalPrice

        shopOrderRepository.save(shopOrder)
        return showAllOrders(model)
    }

}
