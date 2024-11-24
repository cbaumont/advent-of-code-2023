package org.example

internal fun sumOfNumbersAdjacentToSymbols(
    symbolsPositions: List<List<Int>>,
    numbersPositions: List<List<Pair<Int, IntRange>>>
): Int {

    val adjacent = mutableListOf<Int>()

    if (numbersPositions[0].isNotEmpty()) {
        for (number in numbersPositions[0]) {
            if (symbolsPositions[0].isNotEmpty()) {
                adjacent += symbolsPositions[0].mapAdjacentNumbers(number)
            }
            if (symbolsPositions[1].isNotEmpty()) {
                adjacent += symbolsPositions[1].mapAdjacentNumbers(number)
            }
        }
    }

    if (numbersPositions[symbolsPositions.lastIndex].isNotEmpty()) {
        for (number in numbersPositions[symbolsPositions.lastIndex]) {
            if (symbolsPositions[symbolsPositions.lastIndex].isNotEmpty()) {
                adjacent += symbolsPositions[symbolsPositions.lastIndex].mapAdjacentNumbers(number)
            }
            if (symbolsPositions[symbolsPositions.lastIndex - 1].isNotEmpty()) {
                adjacent += symbolsPositions[symbolsPositions.lastIndex - 1].mapAdjacentNumbers(number)
            }
        }
    }

    for (i in 1..<numbersPositions.size - 1) {
        if (numbersPositions[i].isNotEmpty()) {
            for (number in numbersPositions[i]) {
                if (symbolsPositions[i].isNotEmpty()) {
                    adjacent += symbolsPositions[i].mapAdjacentNumbers(number)
                }
                if (symbolsPositions[i - 1].isNotEmpty()) {
                    adjacent += symbolsPositions[i - 1].mapAdjacentNumbers(number)
                }
                if (symbolsPositions[i + 1].isNotEmpty()) {
                    adjacent += symbolsPositions[i + 1].mapAdjacentNumbers(number)
                }
            }
        }
    }
    return adjacent.sum()
}

internal fun String.numbersPositionsRange(): List<Pair<Int, IntRange>> {
    val digitsPositions = mapIndexedNotNull { index, char ->
        if (char.isDigit()) {
            char to index
        } else null
    }

    if (digitsPositions.isEmpty()) return emptyList()

    var buffer: String = digitsPositions[0].first.toString()
    var range = digitsPositions[0].second..digitsPositions[0].second
    val result = mutableListOf<Pair<Int, IntRange>>()

    for (i in 1..<digitsPositions.size) {
        if ((digitsPositions[i].second - digitsPositions[i - 1].second) == 1) {
            buffer += digitsPositions[i].first
            range = range.first..digitsPositions[i].second
        } else {
            result.add(buffer.toInt() to range)
            buffer = digitsPositions[i].first.toString()
            range = digitsPositions[i].second..digitsPositions[i].second
        }
    }
    result.add(buffer.toInt() to range)
    return result
}

internal fun String.symbolsPositions(): List<Int> =
    mapIndexedNotNull { index, char ->
        if (!char.isDotOrDigit()) index else null
    }

private fun Char.isDotOrDigit() = isDigit() || this == '.'

private fun List<Int>.mapAdjacentNumbers(number: Pair<Int, IntRange>): List<Int> =
    mapNotNull {
        if (number.second adjacentRange it) number.first else null
    }

private infix fun IntRange.adjacentRange(value: Int): Boolean = value in first - 1..last + 1
