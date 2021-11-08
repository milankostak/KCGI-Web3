package edu.kcg.web3.lecture06.graphql

import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class GraphQLProvider {

    private val logger = LoggerFactory.getLogger(GraphQLProvider::class.java)

    private var graphQL: GraphQL? = null

    @Autowired
    private var graphQLDataFetchers: GraphQLDataFetchers? = null

    @Bean
    fun graphQL(): GraphQL? {
        return graphQL
    }

    @PostConstruct
    fun init() {
        val sdl = this::class.java.classLoader.getResource("schema.graphqls")?.readText()
        if (sdl == null) {
            logger.error("Error while loading GraphQL schema!")
        } else {
            val graphQLSchema = buildSchema(sdl)
            graphQL = GraphQL.newGraphQL(graphQLSchema).build()
        }
    }

    private fun buildSchema(sdl: String): GraphQLSchema? {
        val typeRegistry = SchemaParser().parse(sdl)
        val runtimeWiring = buildWiring()
        val schemaGenerator = SchemaGenerator()
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
    }

    private fun buildWiring(): RuntimeWiring {
        return RuntimeWiring.newRuntimeWiring()
            .type(newTypeWiring("Query").dataFetcher("book", graphQLDataFetchers?.getBookDataFetcher()))
            .type(newTypeWiring("Query").dataFetcher("books", graphQLDataFetchers?.getBooksDataFetcher()))
            .type(newTypeWiring("Query").dataFetcher("author", graphQLDataFetchers?.getAuthorDataFetcher()))
            .type(newTypeWiring("Query").dataFetcher("authors", graphQLDataFetchers?.getAuthorsDataFetcher()))
            .build()
    }
}