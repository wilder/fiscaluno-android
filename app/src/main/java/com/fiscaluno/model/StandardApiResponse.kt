package com.fiscaluno.model

data class StandardApiResponse<E> (
        val status: String,
        val code: Int,
        val messages: List<String>,
        val result: E
)