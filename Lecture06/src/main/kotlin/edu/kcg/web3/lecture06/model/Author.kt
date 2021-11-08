package edu.kcg.web3.lecture06.model

data class Author(
    val firstName: String = "",
    val lastName: String = "",
    val books: MutableList<Book> = mutableListOf(),
    var id: Int = -1,
)
