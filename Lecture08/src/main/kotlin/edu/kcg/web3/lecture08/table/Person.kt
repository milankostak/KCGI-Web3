package edu.kcg.web3.lecture08.table

import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "person")
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "email")
    var email: String = ""

    @Column(name = "password")
    var passwordHash: String = ""

    @Column(name = "age")
    var age: Long = 0

    @Column(name = "deleted")
    var deleted: Boolean = false

    @Column(name = "registration_time")
    var registrationTime: Instant = Instant.now()

}
