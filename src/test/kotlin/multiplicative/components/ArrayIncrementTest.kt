package multiplicative.components

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ArrayIncrementTest {

    @ParameterizedTest
    @MethodSource("items")
    fun shouldIncrement(input: IntArray, expected: IntArray) {
        val result = incrementArray(input)
        assertThat(result, equalTo(expected))
    }

    companion object {

        @JvmStatic
        fun items(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(0), intArrayOf(1)),
                Arguments.of(intArrayOf(8), intArrayOf(9)),
                Arguments.of(intArrayOf(9), intArrayOf(1, 0)),
                Arguments.of(intArrayOf(1, 8), intArrayOf(1, 9)),
                Arguments.of(intArrayOf(1, 9), intArrayOf(2, 2)),
                Arguments.of(intArrayOf(2, 8), intArrayOf(2, 9)),
                Arguments.of(intArrayOf(9, 8), intArrayOf(9, 9)),
                Arguments.of(intArrayOf(9, 9), intArrayOf(2, 2, 2)),
                Arguments.of(intArrayOf(2, 8, 9), intArrayOf(2, 9, 2)),
                Arguments.of(intArrayOf(9, 9, 9), intArrayOf(2, 2, 2, 2)),
            )
        }
    }

}
