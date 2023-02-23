package multiplicative

import multiplicative.components.minPersistenceOfNumbers

fun main() {
    val start = System.currentTimeMillis()
    println(minPersistenceOfNumbers(12))
    val elapsed = System.currentTimeMillis() - start
    println("Time: ${elapsed}ms")
}





