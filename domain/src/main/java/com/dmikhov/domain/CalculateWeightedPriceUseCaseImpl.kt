package com.dmikhov.domain

import com.dmikhov.domain.abs.CalculateWeightedPriceUseCase
import com.dmikhov.domain.repository.MoneyRepository

/**
 * Calculates weighted price due to inflation data. Show how the price has changed
 * since startYear until endYear. Calculation skips inflation for first year.
 */
class CalculateWeightedPriceUseCaseImpl(
    private val moneyRepository: MoneyRepository
): CalculateWeightedPriceUseCase {
    override fun calculateWeightedPrice(price: Double?, startYear: Int?, endYear: Int?): Double? {
        if (price == null || startYear == null || endYear == null) {
            return null
        }
        val inflationList = moneyRepository.getUSDInflation()
            .filter { it.year != null && it.percent != null }
            .filter { it.year in (startYear + 1)..endYear }
            .sortedBy { it.year }
        var weightedPrice = price
        inflationList.forEach { inflation ->
            weightedPrice *= 1 + (inflation.percent ?: 0F)
        }
        return weightedPrice
    }
}