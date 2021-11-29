package edu.kcg.web3.lecture09.entity

import java.time.Instant
import javax.persistence.*

@Entity(name = "shop_order")
class ShopOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_shop_order")
    val id: Long? = null

    @Column(name = "total_price", nullable = false)
    var totalPrice: Long = 0

    @Column(name = "created", nullable = false)
    val created: Instant = Instant.now()

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_customer")
    var customer: Customer? = null

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "shoporder_product",
        joinColumns = [JoinColumn(name = "id_shop_order")],
        inverseJoinColumns = [JoinColumn(name = "id_product")]
    )
    val products: MutableSet<Product> = mutableSetOf()

}
