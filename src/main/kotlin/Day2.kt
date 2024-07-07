package org.example

private val bagContents = mapOf("red" to 12, "green" to 13, "blue" to 14)

fun List<String>.sumOfPossibleGames() =
    partition { game ->
        game.listOfGrabs()
            .listOfCubes()
            .filterImpossible().isNotEmpty()
    }.second.sumOf { it.gameNumber() }

fun List<String>.sumPowerOfAllGames() =
    sumOf {
        it.listOfGrabs()
            .listOfCubes()
            .powerOfGame()
    }

fun List<String>.filterImpossible(): List<String> =
    partition { cubesByColor ->
        val cubes = cubesByColor.filter { it.isDigit() }.toInt()
        val color = cubesByColor.filter { it.isLetter() }
        cubes > bagContents[color]!!
    }.first

fun List<String>.powerOfGame(): Int {
    val max = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
    forEach { cubesByColor ->
        val cubes = cubesByColor.filter { it.isDigit() }.toInt()
        val color = cubesByColor.filter { it.isLetter() }
        if (cubes > max[color]!!) {
            max[color] = cubes
        }
    }
    return max.map { it.value }.reduce { power, it -> power * it }
}

fun List<String>.listOfCubes() =
    flatMap { it.split(',') }
        .map { it.trim() }

fun String.listOfGrabs() =
    split(':')[1]
        .split(';')
        .map { it.trim() }

fun String.gameNumber() =
    split(':')
        .first()
        .filter { it.isDigit() }.toInt()
