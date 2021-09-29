package edu.kcg.web3.lecture07.model

data class MovieSearchResult(
    val Search: List<Movie>,
    val totalResults: String,
    val Response: String
)

data class Movie(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
)
