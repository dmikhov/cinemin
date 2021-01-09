package com.dmikhov.domain.abs

interface CalculateWeightedPriceUseCase {
    fun calculateWeightedPrice(price: Double?, startYear: Int?, endYear: Int?): Double?
}