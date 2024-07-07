package org.example

fun findPossibleGames(games: List<String>): List<Int> {
    val bagContents = mapOf("red" to 12, "green" to 13, "blue" to 14)
    val impossibleGames = mutableListOf<Int>()
    games.forEach { game ->
        val gameNumber = game.gameNumber()
        game.listOfGrabs()
            .flatMap { it.listOfCubes() }
            .toCubesColorMap()
            .forEach { (color, cubes) ->
                if (cubes > bagContents[color]!! && gameNumber !in impossibleGames) {
                    impossibleGames += gameNumber
                }
            }
    }
    val possibleGames = games
        .map { it.gameNumber() }
        .filterNot { it in impossibleGames }
    return possibleGames
}

fun List<String>.toCubesColorMap(): Map<String, Int> {
    val result = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
    this.forEach { cubesByColor ->
        val cubes = cubesByColor.filter { it.isDigit() }.toInt()
        val color = cubesByColor.filter { it.isLetter() }
        if (cubes > result[color]!!) {
            result[color] = cubes
        }
    }
    return result
}

fun String.listOfCubes() =
    this.split(',')
        .map { it.trim() }

fun String.listOfGrabs() =
    this.split(':')[1]
        .split(';')
        .map { it.trim() }

fun String.gameNumber() =
    this.split(':')
        .first()
        .filter { it.isDigit() }.toInt()