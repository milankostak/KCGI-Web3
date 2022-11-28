package edu.kcg.web3.lecture09.entity

import java.time.Instant
import javax.persistence.*

@Entity(name = "shop_order")
class ShopOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_shop_order", nullable = false)
    val id: Long? = null

    @Column(name = "total_price", nullable = false)
    var totalPrice: Long = 0 // for money better use java.math.BigDecimal

    @Column(name = "created", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    val created: Instant = Instant.now()

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_customer")
    var customer: Customer? = null

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "shop_order_products",
        joinColumns = [JoinColumn(name = "id_shop_order")],
        inverseJoinColumns = [JoinColumn(name = "id_product")]
    )
    var products: MutableSet<Product> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ShopOrder

        if (id != other.id) return false
        if (totalPrice != other.totalPrice) return false
        if (created != other.created) return false
        if (customer != other.customer) return false
//        if (products != other.products) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + totalPrice.hashCode()
        result = 31 * result + created.hashCode()
        result = 31 * result + (customer?.hashCode() ?: 0)
//        result = 31 * result + products.hashCode()
        return result
    }

}