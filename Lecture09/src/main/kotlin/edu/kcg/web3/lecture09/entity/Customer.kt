package edu.kcg.web3.lecture09.entity

import javax.persistence.*

@Entity(name = "customer")
class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_customer", nullable = false)
    val id: Long? = null

    @Column(name = "email", nullable = false, unique = true)
    var email: String = ""

    @Column(name = "password", nullable = false)
    var password: String = ""

    @OneToMany(mappedBy = "customer")
    val shopOrders: Set<ShopOrder> = setOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Customer

        if (id != other.id) return false
        if (email != other.email) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }

}