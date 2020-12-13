package com.dmikhov.repository

import android.content.Context
import com.dmikhov.entities.money.Inflation
import com.dmikhov.usecases.repository.IMoneyRepository
import java.io.BufferedReader
import java.io.InputStreamReader

class MoneyRepository(
    private val context: Context
) : IMoneyRepository {
    override fun getUSDInflation(): List<Inflation> {
        val inputStream = context.assets.open(INFLATION_DATA_FILE_NAME)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val linesWithTitle = reader.readLines()
        val lines = linesWithTitle.takeLast(linesWithTitle.size - 1)
        return lines.map { convertLineToInflation(it) }
    }

    private fun convertLineToInflation(line: String): Inflation {
        val columns = line.split(",")
        return Inflation(columns.getOrNull(0)?.toIntOrNull(), columns.getOrNull(2)?.toFloatOrNull())
    }

    companion object {
        const val INFLATION_DATA_FILE_NAME = "inflation_data.csv"
    }
}