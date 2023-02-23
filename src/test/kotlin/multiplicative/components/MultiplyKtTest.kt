package multiplicative.components

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.comparesEqualTo
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

import java.math.BigInteger

internal class MultiplyKtTest {

    @Test
    fun shouldSplitLastDigit() {
        val long = 154L
        val bigInt =
            BigInteger.valueOf(long).toString().toCharArray().map { Integer.parseInt(it.toString()) }.toIntArray()
        val (rest, digit) = splitLastDigit(bigInt)

        assertThat(rest, equalTo(intArrayOf(1, 5)))
        assertThat(digit, equalTo(4))
    }

    @Test
    fun shouldMultiplyDigits() {
        val long = 328L
        val bigInt =
            BigInteger.valueOf(long).toString().toCharArray().map { Integer.parseInt(it.toString()) }.toIntArray()
        val result = multiplyDigits(bigInt)

        val expected = intArrayOf(4, 8)
        assertThat(result, equalTo(expected))
    }
}
