package org.example

fun fetchListOfStringsFromFile(fileName: String): List<String> =
    getResourceAsText(fileName)
        .lines()
        .filterNot { it.isEmpty() }


private fun getResourceAsText(fileName: String): String =
    object {}.javaClass.getResource("/$fileName")
        ?.readText()
        ?: error("File not found: $fileName")
