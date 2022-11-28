package edu.kcg.web3.lecture09.entity

import javax.persistence.*

@Entity(name = "product")
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product", nullable = false)
    val id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String = ""

    @Column(name = "price", nullable = false)
    var price: Long = 0

    @ManyToMany(mappedBy = "products")
    var shopOrders: MutableSet<ShopOrder> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false
        if (name != other.name) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }

}