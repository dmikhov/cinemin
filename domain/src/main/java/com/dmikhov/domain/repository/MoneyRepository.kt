package com.dmikhov.domain.repository

import com.dmikhov.entities.money.Inflation

interface MoneyRepository {
    fun getUSDInflation(): List<Inflation>
}