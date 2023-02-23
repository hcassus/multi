package multiplicative.components

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

internal class IncrementKtTest {

    @Test
    fun shouldSpreadDigit() {
        val chArr = intArrayOf(1, 0, 0, 3, 0)
        val firstDigitIdx = 1
        val originalDigit = 0
        val replaceDigit = 2

        val result = spreadDigit(chArr, firstDigitIdx, originalDigit, replaceDigit)

        val expected = intArrayOf(1, 2, 2, 2, 2)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldReplaceDoubleDigits() {
        val chArr = intArrayOf(1, 2, 0, 2, 0)
        val firstDoubleIdx = 1
        val lastDoubleIdx = 3
        val originalDigit = 2
        val replaceDigit = 3

        val result =
            replaceDoubleDigits(chArr, firstDoubleIdx, lastDoubleIdx, originalDigit, replaceDigit)

        val expected = intArrayOf(1, 2, 0, 3, 0)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldReplaceInvalidDigitCombination() {
        val chArr = intArrayOf(1, 4, 0, 6, 0)
        val firstCharIdx = 1
        val secondCharIdx = 3
        val originalDigit = 4
        val replaceDigit = 6

        val result = replaceInvalidDigitCombination(
            chArr,
            firstCharIdx,
            secondCharIdx,
            originalDigit,
            replaceDigit
        )

        val expected = intArrayOf(1, 4, 0, 7, 0)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldIncrementPairAndFive() {
        val chArr = intArrayOf(1, 4, 0, 5, 0)
        val resolvedChArr = mapOf(
            0 to Pair(2, 4),
            1 to Pair(0, 0),
            4 to Pair(1, 1),
            5 to Pair(3, 3),
        )

        val result = incrementPairAndFive(chArr, resolvedChArr)

        val expected = intArrayOf(1, 4, 0, 6, 0)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldIncrementUnsorted() {
        val chArr = intArrayOf(1, 4, 0, 5, 0)
        val resolvedChArr = mapOf(
            0 to Pair(2, 4),
            1 to Pair(0, 0),
            4 to Pair(1, 1),
            5 to Pair(3, 3),
        )

        val result = incrementUnsorted(chArr, resolvedChArr)

        val expected = intArrayOf(1, 4, 4, 4, 4)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldResolveCharIdx() {
        val chArr = intArrayOf(1, 4, 0, 5, 0)

        val result = resolveIntIdx(chArr)

        val expected = mapOf(
            0 to Pair(2, 4),
            1 to Pair(0, 0),
            4 to Pair(1, 1),
            5 to Pair(3, 3),
        )
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldIncrementNumbersWithZeroes() {
        val chArr = intArrayOf(1, 0)

        val result = incrementNumber(chArr)

        val expected = intArrayOf(1, 2)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldNotSmartIncrementNumbersLowerThatTwentyWithOnes() {
        val chArr = intArrayOf(1, 1)

        val result = incrementNumber(chArr)

        val expected = intArrayOf(1, 2)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldIncrementNumbersWithMultipleTwos(){
        val chArr = intArrayOf(2, 2)

        val result = incrementNumber(chArr)

        val expected = intArrayOf(2, 4)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldIncrementNumbersWithTwoAndThree(){
        val chArr = intArrayOf(2, 3)

        val result = incrementNumber(chArr)

        val expected = intArrayOf(2, 4)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldIncrementNumbersWithMultipleThrees(){
        val chArr = intArrayOf(3, 3)

        val result = incrementNumber(chArr)

        val expected = intArrayOf(3, 4)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldIncrementNumbersWithMultipleFours(){
        val chArr = intArrayOf(4, 4)

        val result = incrementNumber(chArr)

        val expected = intArrayOf(4, 7)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldIncrementNumbersWithFourAndSix(){
        val chArr = intArrayOf(4, 6)

        val result = incrementNumber(chArr)

        val expected = intArrayOf(4, 7)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldIncrementNumbersWithMultipleSixes(){
        val chArr = intArrayOf(6, 6)

        val result = incrementNumber(chArr)

        val expected = intArrayOf(6, 7)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldIncrementNumbersHigherThanTwentyNineWithPairsAndFive(){
        val chArr = intArrayOf(6, 5)

        val result = incrementNumber(chArr)

        val expected = intArrayOf(6, 7)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldNotSmartIncrementNumbersLowerThanTwentyFiveWithPairsAndFive(){
        val chArr = intArrayOf(2, 5)

        val result = incrementNumber(chArr)

        val expected = intArrayOf(2, 6)
        assertThat(result, equalTo(expected))
    }

    @Test
    fun shouldIncrementUnsortedNumbers(){
        val chArr = intArrayOf(7, 3)

        val result = incrementNumber(chArr)

        val expected = intArrayOf(7, 7)
        assertThat(result, equalTo(expected))
    }
}
