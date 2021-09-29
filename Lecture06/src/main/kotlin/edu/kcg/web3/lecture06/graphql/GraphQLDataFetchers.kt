package edu.kcg.web3.lecture06.graphql

import edu.kcg.web3.lecture06.model.Author
import edu.kcg.web3.lecture06.model.Book
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component


@Component
class GraphQLDataFetchers {

    val bookDataFetcher
        get() = DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val bookId: String = dataFetchingEnvironment.getArgument("id")
            books.firstOrNull { it.id.toString() == bookId }
        }

    val booksDataFetcher
        get() = DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val bookIds: List<String>? = dataFetchingEnvironment.getArgument("ids")
            if (bookIds == null || bookIds.isEmpty()) {
                books
            } else {
                books.filter { bookIds.contains(it.id.toString()) }
            }
        }

    val authorDataFetcher
        get() = DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val authorId: String = dataFetchingEnvironment.getArgument("id")
            authors.firstOrNull { it.id.toString() == authorId }
        }

    val authorsDataFetcher
        get() = DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val authorIds: List<String>? = dataFetchingEnvironment.getArgument("ids")
            if (authorIds == null || authorIds.isEmpty()) {
                authors
            } else {
                authors.filter { authorIds.contains(it.id.toString()) }
            }
        }

    companion object {
        private val author1 = Author("Joanne", "Rowling", id = 1)
        private val author2 = Author("Herman", "Melville", id = 2)
        private val author3 = Author("Anne", "Rice", id = 3)

        private val books = listOf(
            Book("Harry Potter and the Philosopher's Stone", 223, author1, 1),
            Book("Moby Dick", 635, author2, 2),
            Book("Interview with the vampire", 371, author3, 3),
        )

        private val authors = listOf(
            author1, author2, author3
        )

        init {
            author1.books.add(books[0])
            author2.books.add(books[1])
            author3.books.add(books[2])
        }
    }

}