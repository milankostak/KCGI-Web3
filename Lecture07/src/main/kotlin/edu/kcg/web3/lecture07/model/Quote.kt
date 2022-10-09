package edu.kcg.web3.lecture07.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Quote(
    var fact: String,
    var length: Long,
)
