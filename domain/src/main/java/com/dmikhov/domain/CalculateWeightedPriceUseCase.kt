package com.dmikhov.domain

import com.dmikhov.domain.repository.IMoneyRepository

/**
 * Calculates weighted price due to inflation data. Show how the price has changed
 * since startYear until endYear.
 */
class CalculateWeightedPriceUseCase(
    private val moneyRepository: IMoneyRepository
) {
    fun calculateWeightedPrice(price: Double?, startYear: Int?, endYear: Int?): Double? {
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