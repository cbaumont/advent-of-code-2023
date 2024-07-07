package org.example

private val mapSpelledDigits = mapOf(
    "one" to '1',
    "two" to '2',
    "three" to '3',
    "four" to '4',
    "five" to '5',
    "six" to '6',
    "seven" to '7',
    "eight" to '8',
    "nine" to '9',
)

fun findFirstAndLastDigits(input: String): Int {
    var firstDigit: Char? = input.firstOrNull { it in '0'..'9' }
    var lastDigit: Char? = input.findLast { it in '0'..'9' }
    val (firstSpelled, lastSpelled) = input.findSpelledDigitsInString()

    if (firstSpelled != null && firstSpelled.second.isNotEmpty()) {
        if (firstDigit == null || firstSpelled.second.first() < input.indexOfFirst { it == firstDigit }) {
            firstDigit = mapSpelledDigits[firstSpelled.first]
        }
    }
    if (lastSpelled != null && lastSpelled.second.isNotEmpty()) {
        if (lastDigit == null || lastSpelled.second.last() > input.indexOfLast { it == lastDigit }) {
            lastDigit = mapSpelledDigits[lastSpelled.first]
        }
    }
    return "$firstDigit$lastDigit".toInt()
}

fun sumAllFirstAndLastDigitsFromList(list: List<String>) = list.sumOf { findFirstAndLastDigits(it) }

private fun String.findSpelledDigitsInString(): Pair<Pair<String, List<Int>>?, Pair<String, List<Int>>?> {
    val listPairs = mapSpelledDigits.keys.map { spelledDigit ->
        spelledDigit to spelledDigit.toRegex().findAll(this).map { it.range.first }.toList()
    }.filterNot { it.second.isEmpty() }

    return listPairs.minByOrNull { it.second.min() } to listPairs.maxByOrNull { it.second.max() }
}
