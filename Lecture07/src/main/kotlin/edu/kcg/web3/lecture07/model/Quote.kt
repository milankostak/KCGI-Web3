package edu.kcg.web3.lecture07.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class QuoteContainer(
    var type: String,
    var value: Quote,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Quote(
    var id: Long,
    var quote: String,
)
