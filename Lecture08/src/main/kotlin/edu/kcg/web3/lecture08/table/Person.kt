package edu.kcg.web3.lecture08.table

import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "person")
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "email", nullable = false, unique = true)
    var email: String = ""

    @Column(name = "password", nullable = false)
    var passwordHash: String = ""

    @Column(name = "age", nullable = false)
    var age: Long = 0

    @Column(name = "deleted", nullable = false)
    var deleted: Boolean = false

    @Column(name = "registration_time", nullable = false)
    var registrationTime: Instant = Instant.now()

}
