package org.example

fun main() {
    val calibrationValues = fetchListOfStringsFromFile("day1_input.txt")

    val sum = sumAllFirstAndLastDigitsFromList(calibrationValues)

    println("Sum of all of the calibration values is $sum")
}
