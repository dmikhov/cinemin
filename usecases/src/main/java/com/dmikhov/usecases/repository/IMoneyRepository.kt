package com.dmikhov.usecases.repository

import com.dmikhov.entities.money.Inflation

interface IMoneyRepository {
    fun getUSDInflation(): List<Inflation>
}