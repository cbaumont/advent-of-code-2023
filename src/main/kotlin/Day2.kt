package org.example

private val bagContents = mapOf("red" to 12, "green" to 13, "blue" to 14)

fun findPossibleGames(games: List<String>) =
    games.partition { game ->
        game.listOfGrabs()
            .listOfCubes()
            .filterImpossible().isNotEmpty()
    }.second.map { it.gameNumber() }

fun List<String>.filterImpossible(): List<String> =
    this.partition { cubesByColor ->
        val cubes = cubesByColor.filter { it.isDigit() }.toInt()
        val color = cubesByColor.filter { it.isLetter() }
        cubes > bagContents[color]!!
    }.first

fun List<String>.listOfCubes() =
    this.flatMap { it.split(',') }
        .map { it.trim() }

fun String.listOfGrabs() =
    this.split(':')[1]
        .split(';')
        .map { it.trim() }

fun String.gameNumber() =
    this.split(':')
        .first()
        .filter { it.isDigit() }.toInt()