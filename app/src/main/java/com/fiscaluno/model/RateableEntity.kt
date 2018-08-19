package com.fiscaluno.model

abstract class RateableEntity {
    abstract var name: String
    abstract var averageRating: Float
    abstract var ratedByCount: Int
}
