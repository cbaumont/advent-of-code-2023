import org.example.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

class Day2Test {

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 88, 100])
    fun `returns number of the game`(value: Int) {
        val result = "Game $value: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red".gameNumber()
        assertEquals(value, result)
    }

    @Test
    fun `returns list of all grabs from game`() {
        val result = "Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red".listOfGrabs()

        assertEquals(listOf("5 red, 3 blue", "2 red", "2 green, 6 blue, 7 red", "5 red"), result)
    }

    @Test
    fun `returns list of cubes by color`() {
        val result = "Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red"
            .listOfGrabs()
            .flatMap { it.listOfCubes() }

        assertEquals(listOf("5 red", "3 blue", "2 red", "2 green", "6 blue", "7 red", "5 red"), result)
    }

    @Test
    fun `returns map of number to color`() {
        val result = "Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red"
            .listOfGrabs()
            .flatMap { it.listOfCubes() }
            .toCubesColorMap()
        assertEquals(7, result["red"])
        assertEquals(6, result["blue"])
        assertEquals(2, result["green"])
    }

    @Test
    fun `returns sum of all possible games`() {
        val games = fetchListOfStringsFromFile("day2_input.txt")
        val possibleGames = findPossibleGames(games)

        assertEquals(2512, possibleGames.sum())
    }

}



