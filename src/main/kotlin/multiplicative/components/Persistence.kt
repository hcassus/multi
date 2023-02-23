package multiplicative.components

import java.io.File
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun minPersistenceOfNumbers(maxPersist: Int): Map<Int, Pair<String, String>> {
    val start = System.currentTimeMillis()
    var currentPersistence = -1
    var numberArr = intArrayOf(0)
    val persistMap = mutableMapOf<Int, Pair<String, String>>()

//    var curNumLen = 0

    Runtime.getRuntime().addShutdownHook(object : Thread() {
        override fun run() {
            val message = """
                ${LocalDateTime.ofInstant(Instant.ofEpochMilli(start), ZoneId.systemDefault())} - ${LocalDateTime.now()}
                --------------------------------------------------------------------------------------------------------
                Max number reached: ${numberArr.joinToString("")} in ${System.currentTimeMillis() - start}ms
                $persistMap
                ========================================================================================================${"\n"}
                
             """.trimIndent()
            println(message)
            File("report.txt").appendText(message)
        }
    })

    while (currentPersistence < maxPersist) {
        val persistence = persistenceOfNumber(numberArr)
        if (persistence > currentPersistence) {
            currentPersistence = persistence
            persistMap.put(currentPersistence, Pair(numberArr.joinToString(""), "${System.currentTimeMillis() - start}ms"))
        }

//        if (numberArr.size > curNumLen){
//            curNumLen = numberArr.size
//            println("$curNumLen digits reached in ${System.currentTimeMillis() - start}ms")
//        }
        numberArr = incrementNumber(numberArr)
    }
    return persistMap
}

fun persistenceOfNumber(n: IntArray): Int {
    var number = n
    var rounds = 0

    while (number.size > 1) {
        number = multiplyDigits(number)
        rounds++
    }
    return rounds
}
