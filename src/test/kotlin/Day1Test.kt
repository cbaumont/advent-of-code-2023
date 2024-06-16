import org.example.fetchListOfStringsFromFile
import org.example.findFirstAndLastDigits
import org.example.sumAllFirstAndLastDigitsFromList
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class Day1Test {

    @ParameterizedTest
    @MethodSource("provideParameters")
    fun `return first and last digits of a string`(input: String, result: Int) {
        val output = findFirstAndLastDigits(input)

        assertEquals(result, output)
    }

    @Test
    fun `return list of strings from file`() {
        val strings = fetchListOfStringsFromFile("day1_input.txt")

        assertEquals("dssmtmrkonedbbhdhjbf9hq", strings.first())
        assertEquals("449three45three", strings.last())
    }

    @Test
    fun `returns sum of all first and last digits from list`() {
        val strings = listOf(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet",
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen",
        )

        val sum = sumAllFirstAndLastDigitsFromList(strings)

        assertEquals(423, sum)
    }

    @Test
    fun `return first and last spelled digits of a string`() {
        val output = findFirstAndLastDigits("67mcmfive1sixonefive")

        assertEquals(65, output)
    }

    companion object {
        @JvmStatic
        fun provideParameters(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("1abc2", 12),
                Arguments.of("pqr3stu8vwx", 38),
                Arguments.of("a1b2c3d4e5f", 15),
                Arguments.of("treb7uchet", 77),
                Arguments.of("two1nine", 29),
                Arguments.of("eightwothree", 83),
                Arguments.of("abcone2threexyz", 13),
                Arguments.of("xtwone3four", 24),
                Arguments.of("4nineeightseven2", 42),
                Arguments.of("zoneight234", 14),
                Arguments.of("7pqrstsixteen", 76),
            )
        }
    }
}
