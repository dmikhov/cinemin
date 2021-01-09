package com.dmikhov.domain

import com.dmikhov.domain.repository.MoneyRepository
import com.dmikhov.entities.money.Inflation
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class CalculateWeightedPriceUseCaseTest {
    @Test
    fun `CalculateWeightedPrice must calculate price correctly`() {
        val moneyRepository = Mockito.mock(MoneyRepository::class.java)
        Mockito.`when`(moneyRepository.getUSDInflation()).thenReturn(
            arrayListOf(
                Inflation(2017, 0.05F),
                Inflation(2018, 0.2F),
                Inflation(2019, 0.1F),
                Inflation(2020, 0.15F),
                Inflation(2021, 0.13F),
            )
        )
        val calculateWeightedPriceUseCase = CalculateWeightedPriceUseCaseImpl(moneyRepository)
        val testPrice = 100.0
        val testStartYear = 2018
        val testEndYear = 2020
        val testCorrectResult = 126.5
        val calculatedPrice = calculateWeightedPriceUseCase
            .calculateWeightedPrice(testPrice, testStartYear, testEndYear)!!
        Assert.assertEquals(testCorrectResult, calculatedPrice, 0.01)
    }
}