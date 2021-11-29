package edu.kcg.web3.lecture09.entity

import javax.persistence.*

@Entity(name = "product")
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product")
    val id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String = ""

    @Column(name = "price", nullable = false)
    var price: Long = 0

    @ManyToMany(mappedBy = "products")
    val shopOrders: Set<ShopOrder> = setOf()

}
