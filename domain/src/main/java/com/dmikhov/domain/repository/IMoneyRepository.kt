package com.dmikhov.domain.repository

import com.dmikhov.entities.money.Inflation

interface IMoneyRepository {
    fun getUSDInflation(): List<Inflation>
}