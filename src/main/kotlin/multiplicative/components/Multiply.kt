package multiplicative.components

import java.math.BigInteger

fun splitLastDigit(n: IntArray): Pair<IntArray, Int> {
    val lastIdx = n.size - 1
    return Pair(
        n.copyOfRange(0, lastIdx),
        n[lastIdx],
    )
}

fun multiplyDigits(n: IntArray): IntArray {
    var result = BigInteger.ONE
    var (rest, digit) = splitLastDigit(n)

    while (rest.isEmpty().not()) {
        if (digit == 0 || result == BigInteger.ZERO) {
            return intArrayOf(0)
        }
        if (digit == 1) {
            val pair = splitLastDigit(rest)
            rest = pair.first
            digit = pair.second
            continue
        }

        result = result.times(BigInteger.valueOf(digit.toLong()))

        val pair = splitLastDigit(rest)
        rest = pair.first
        digit = pair.second

    }

    val finalResult = result.times(BigInteger.valueOf(digit.toLong()))
    val finalResultArr = finalResult.toString().toCharArray().map { Integer.parseInt(it.toString()) }.toIntArray()
    return finalResultArr
}
