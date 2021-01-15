package com.dmikhov.domain

import com.dmikhov.domain.repository.MoneyRepository
import com.dmikhov.entities.money.Inflation
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class CalculateWeightedPriceUseCaseTest {
    private val inflationList = arrayListOf(
        Inflation(2017, 0.05F),
        Inflation(2018, 0.2F),
        Inflation(2019, 0.1F),
        Inflation(2020, 0.15F),
        Inflation(2021, 0.13F),
    )

    @Test
    fun `must calculate price correctly`() {
        val moneyRepository = Mockito.mock(MoneyRepository::class.java)
        Mockito.`when`(moneyRepository.getUSDInflation()).thenReturn(inflationList)
        val calculateWeightedPriceUseCase = CalculateWeightedPriceUseCaseImpl(moneyRepository)
        val testPrice = 100.0
        val testStartYear = 2018
        val testEndYear = 2020
        val testCorrectResult = 126.5
        val calculatedPrice = calculateWeightedPriceUseCase
            .calculateWeightedPrice(testPrice, testStartYear, testEndYear)!!
        Assert.assertEquals(testCorrectResult, calculatedPrice, 0.01)
    }

    @Test
    fun `must fetch inflation from repository`() {
        val moneyRepository = Mockito.mock(MoneyRepository::class.java)
        Mockito.`when`(moneyRepository.getUSDInflation()).thenReturn(inflationList)
        val calculateWeightedPriceUseCase = CalculateWeightedPriceUseCaseImpl(moneyRepository)
        val testPrice = 100.0
        val testStartYear = 2018
        val testEndYear = 2020
        calculateWeightedPriceUseCase
            .calculateWeightedPrice(testPrice, testStartYear, testEndYear)!!
        Mockito.verify(moneyRepository, Mockito.times(1)).getUSDInflation()
    }

    @Test
    fun `must return same price is startYear and endYear are the same`() {
        val moneyRepository = Mockito.mock(MoneyRepository::class.java)
        Mockito.`when`(moneyRepository.getUSDInflation()).thenReturn(inflationList)
        val calculateWeightedPriceUseCase = CalculateWeightedPriceUseCaseImpl(moneyRepository)
        val testPrice = 100.0
        val testStartYear = 2020
        val testEndYear = 2020
        val calculatedPrice = calculateWeightedPriceUseCase
            .calculateWeightedPrice(testPrice, testStartYear, testEndYear)!!
        Assert.assertEquals(testPrice, calculatedPrice, 0.01)
    }

    @Test
    fun `must return last possible price if endYear is in future`() {
        val moneyRepository = Mockito.mock(MoneyRepository::class.java)
        Mockito.`when`(moneyRepository.getUSDInflation()).thenReturn(inflationList)
        val calculateWeightedPriceUseCase = CalculateWeightedPriceUseCaseImpl(moneyRepository)
        val testPrice = 100.0
        val testStartYear = 2018
        val testEndYear = 2030
        val testCorrectResult = 142.945
        val calculatedPrice = calculateWeightedPriceUseCase
            .calculateWeightedPrice(testPrice, testStartYear, testEndYear)!!
        Assert.assertEquals(testCorrectResult, calculatedPrice, 0.01)
    }

    @Test
    fun `must return same price if startYear is larger than endYear`() {
        val moneyRepository = Mockito.mock(MoneyRepository::class.java)
        Mockito.`when`(moneyRepository.getUSDInflation()).thenReturn(inflationList)
        val calculateWeightedPriceUseCase = CalculateWeightedPriceUseCaseImpl(moneyRepository)
        val testPrice = 100.0
        val testStartYear = 2020
        val testEndYear = 2010
        val calculatedPrice = calculateWeightedPriceUseCase
            .calculateWeightedPrice(testPrice, testStartYear, testEndYear)!!
        Assert.assertEquals(testPrice, calculatedPrice, 0.01)
    }

    @Test
    fun `must return null if price is null`() {
        val moneyRepository = Mockito.mock(MoneyRepository::class.java)
        Mockito.`when`(moneyRepository.getUSDInflation()).thenReturn(inflationList)
        val calculateWeightedPriceUseCase = CalculateWeightedPriceUseCaseImpl(moneyRepository)
        val testPrice: Double? = null
        val testStartYear = 2010
        val testEndYear = 2020
        val calculatedPrice = calculateWeightedPriceUseCase
            .calculateWeightedPrice(testPrice, testStartYear, testEndYear)
        Assert.assertNull(calculatedPrice)
    }
}