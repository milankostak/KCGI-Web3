package edu.kcg.web3.lecture09.entity

import javax.persistence.*


@Entity(name = "customer")
class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_customer")
    val id: Long? = null

    @Column(name = "email", nullable = false, unique = true)
    val email: String = ""

    @Column(name = "password", nullable = false)
    val password: String = ""

    @OneToMany(mappedBy = "customer", cascade = [CascadeType.ALL])
    val shopOrders: Set<ShopOrder> = setOf()

}