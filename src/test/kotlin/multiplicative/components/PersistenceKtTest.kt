package multiplicative.components

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

import java.math.BigInteger

internal class PersistenceKtTest {

    @Test
    fun shouldCalculateMinPersistenceOfNumbers() {
        val result = minPersistenceOfNumbers(11)
        val expected = mapOf(
            0 to Pair("0", ""),
            1 to Pair("10", ""),
            2 to Pair("25", ""),
            3 to Pair("39", ""),
            4 to Pair("77", ""),
            5 to Pair("679", ""),
            6 to Pair("6788", ""),
            7 to Pair("68889", ""),
            8 to Pair("2677889", ""),
            9 to Pair("26888999", ""),
            10 to Pair("3778888999", ""),
            11 to Pair("277777788888899", ""),
        )
        assertThat(result.keys, equalTo(expected.keys))
        assertThat(result.values.map { it.first }, equalTo(expected.values.map { it.first }))
    }

    @Test
    fun shouldCalculatePersistence() {
        val bigInt = BigInteger.valueOf(277777788888899).toString().toCharArray().map {
            Integer.parseInt(
                it.toString()
            )
        }.toIntArray()
        val persist = persistenceOfNumber(bigInt)

        assertThat(persist, equalTo(11))
    }

}
