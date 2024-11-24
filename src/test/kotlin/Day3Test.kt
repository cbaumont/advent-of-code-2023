import org.example.numbersPositionsRange
import org.example.sumOfNumbersAdjacentToSymbols
import org.example.symbolsPositions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun `returns all numbers with positions from string`() {
        val string = "467.....12...1000.1"

        val result = string.numbersPositionsRange()

        assertEquals(listOf(467 to 0..2, 12 to 8..9, 1000 to 13..16, 1 to 18..18), result)
    }

    @Test
    fun `returns list of symbols positions from string`() {
        val string = "...*.123..&....-"
        val result = string.symbolsPositions()

        assertEquals(listOf(3, 10, 15), result)
    }

    @Test
    fun `returns sum of numbers adjacent to symbols`() {
        val input = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )
        val symbolsPositions = input.map(String::symbolsPositions)
        val numbersPositions = input.map(String::numbersPositionsRange)

        val result = sumOfNumbersAdjacentToSymbols(symbolsPositions, numbersPositions)

        assertEquals(4361, result)
    }

}
