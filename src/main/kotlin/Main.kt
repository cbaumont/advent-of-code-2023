package org.example

fun main() {
    val calibrationValues = fetchListOfStringsFromFile("day1_input.txt")
    val calibrationValuesSum = sumAllFirstAndLastDigitsFromList(calibrationValues)

    println("Sum of all of the calibration values is $calibrationValuesSum")

    val games = fetchListOfStringsFromFile("day2_input.txt")
    val possibleGamesSum = games.sumOfPossibleGames()
    val powerOfGamesSum = games.sumPowerOfAllGames()

    println("Sum of all of the possible games is $possibleGamesSum")
    println("Sum of all of the power from games is $powerOfGamesSum")

    val engineSchematic = fetchListOfStringsFromFile("day3_input.txt")
    val sumEnginePartNumbers = sumOfNumbersAdjacentToSymbols(
        symbolsPositions = engineSchematic.map(String::symbolsPositions),
        numbersPositions = engineSchematic.map(String::numbersPositionsRange)
    )

    println("Sum of all engine part numbers is $sumEnginePartNumbers")
}
