import org.example.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertContains
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
            .listOfCubes()

        assertEquals(listOf("5 red", "3 blue", "2 red", "2 green", "6 blue", "7 red", "5 red"), result)
    }

    @Test
    fun `returns map of number to color`() {
        val result = "Game 30: 15 red, 3 blue; 2 red; 22 green, 16 blue, 7 red; 5 red"
            .listOfGrabs()
            .listOfCubes()
            .filterImpossible()
        assertContains(result, "15 red")
        assertContains(result, "16 blue")
        assertContains(result, "22 green")
    }

    @Test
    fun `returns sum of all possible games`() {
        val games = fetchListOfStringsFromFile("day2_input.txt")
        val possibleGames = findPossibleGames(games)

        assertEquals(2512, possibleGames.sum())
    }

}



