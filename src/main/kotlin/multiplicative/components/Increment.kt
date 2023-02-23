package multiplicative.components

//var skips = 0L
//var incs = 0L

fun incrementNumber(numChArr: IntArray): IntArray {
    var numberChArr = numChArr

    var resolvedChArr = resolveIntIdx(numberChArr)
    var zeroIdx = resolvedChArr[0]?.first ?: -1
    var oneIdx = resolvedChArr[1]?.first ?: -1
    var firstTwoIdx = resolvedChArr[2]?.first ?: -1
    var lastTwoIdx = resolvedChArr[2]?.second ?: -1
    var firstThreeIdx = resolvedChArr[3]?.first ?: -1
    var lastThreeIdx = resolvedChArr[3]?.second ?: -1
    var firstFourIdx = resolvedChArr[4]?.first ?: -1
    var lastFourIdx = resolvedChArr[4]?.second ?: -1
    var fiveIdx = resolvedChArr[5]?.first ?: -1
    var firstSixIdx = resolvedChArr[6]?.first ?: -1
    var lastSixIdx = resolvedChArr[6]?.second ?: -1
    var eightIdx = resolvedChArr[8]?.first ?: -1

    var hasPairAndFive = fiveIdx >= 0 && (
        firstTwoIdx >= 0
            || firstFourIdx >= 0
            || firstSixIdx >= 0
            || eightIdx >= 0
        )

    var isSorted = numberChArr.asSequence().zipWithNext { a, b -> a <= b }.all { it }
    do {
        numberChArr = when {
            zeroIdx >= 0 -> spreadDigit(numberChArr, zeroIdx, 0, 2)

            (numberChArr.size > 2 || (numberChArr.size == 2 && numberChArr[0] > 1)) && oneIdx >= 0 -> spreadDigit(
                numberChArr,
                oneIdx,
                1,
                2
            )

            firstTwoIdx != lastTwoIdx -> replaceDoubleDigits(numberChArr, firstTwoIdx, lastTwoIdx, 2, 4)

            firstTwoIdx >= 0 && firstThreeIdx >= 0 -> replaceInvalidDigitCombination(
                numberChArr,
                firstTwoIdx,
                firstThreeIdx,
                2,
                3
            )

            firstThreeIdx != lastThreeIdx -> replaceDoubleDigits(numberChArr, firstThreeIdx, lastThreeIdx, 3, 4)

            firstFourIdx != lastFourIdx -> replaceDoubleDigits(numberChArr, firstFourIdx, lastFourIdx, 4, 7)

            firstFourIdx >= 0 && firstSixIdx >= 0 -> replaceInvalidDigitCombination(
                numberChArr,
                firstFourIdx,
                firstSixIdx,
                4,
                6
            )

            firstSixIdx != lastSixIdx -> replaceDoubleDigits(numberChArr, firstSixIdx, lastSixIdx, 6, 7)

            hasPairAndFive -> incrementPairAndFive(numberChArr, resolvedChArr)

            !isSorted -> incrementUnsorted(numberChArr, resolvedChArr)

            else -> {
//                incs++
                incrementArray(numberChArr)
            }
        }
        resolvedChArr = resolveIntIdx(numberChArr)
        zeroIdx = resolvedChArr[0]?.first ?: -1
        oneIdx = resolvedChArr[1]?.first ?: -1
        firstTwoIdx = resolvedChArr[2]?.first ?: -1
        lastTwoIdx = resolvedChArr[2]?.second ?: -1
        firstThreeIdx = resolvedChArr[3]?.first ?: -1
        lastThreeIdx = resolvedChArr[3]?.second ?: -1
        firstFourIdx = resolvedChArr[4]?.first ?: -1
        lastFourIdx = resolvedChArr[4]?.second ?: -1
        fiveIdx = resolvedChArr[5]?.first ?: -1
        firstSixIdx = resolvedChArr[6]?.first ?: -1
        lastSixIdx = resolvedChArr[6]?.second ?: -1
        eightIdx = resolvedChArr[8]?.first ?: -1
        hasPairAndFive = fiveIdx >= 0 && (
            firstTwoIdx >= 0
                || firstFourIdx >= 0
                || firstSixIdx >= 0
                || eightIdx >= 0
            )
        isSorted = numberChArr
            .asSequence()
            .zipWithNext { a, b -> a <= b }
            .all { it }
    } while (
        (numberChArr.size > 2 || (numberChArr.size == 2 && numberChArr[0] > 1))
        && (
            zeroIdx >= 0
                || oneIdx >= 0
                || firstTwoIdx != lastTwoIdx
                || firstTwoIdx >= 0 && firstThreeIdx >= 0
                || firstThreeIdx != lastThreeIdx
                || firstFourIdx != lastFourIdx
                || firstFourIdx >= 0 && firstSixIdx >= 0
                || firstSixIdx != lastSixIdx
                || ((numberChArr.size > 2 || (numberChArr.size == 2 && numberChArr[0] > 2)) && (fiveIdx >= 0 && (firstTwoIdx >= 0 || firstFourIdx >= 0 || firstSixIdx >= 0 || eightIdx >= 0)))
                || !isSorted
            )
    )

//    println("$number") // debugging increment
//    val number = BigInteger(numberChArr.joinToString(""))
//    println("Skips: $skips, Incs: $incs") // debugging skip ratio
    return numberChArr
}

fun spreadDigit(
    nArr: IntArray,
    firstDigitIdx: Int,
    originalDigit: Int,
    replaceDigit: Int
): IntArray {
    val numberChArr = nArr
    var indexDigit = firstDigitIdx
    while (indexDigit > -1) {
        for (i in indexDigit until numberChArr.size) {
            numberChArr[i] = replaceDigit
//            skips++
        }
        indexDigit = numberChArr.indexOf(originalDigit)
    }

    return numberChArr
}

fun replaceDoubleDigits(
    nArr: IntArray,
    firstDoubleIdx: Int,
    lastDoubleIdx: Int,
    originalDigit: Int,
    replaceDigit: Int
): IntArray {
    val numberChArr = nArr
    var firstDuplicateIndex = firstDoubleIdx
    var lastDuplicateIndex = lastDoubleIdx
    while (firstDuplicateIndex > -1 && firstDuplicateIndex != lastDuplicateIndex) {
        for (i in firstDuplicateIndex + 1 until numberChArr.size) {
            if (numberChArr[i] == originalDigit) {
                numberChArr[i] = replaceDigit
            }
//                    skips++
        }
        firstDuplicateIndex = numberChArr.indexOf(originalDigit)
        lastDuplicateIndex = numberChArr.lastIndexOf(originalDigit)
    }
    return numberChArr
}

fun replaceInvalidDigitCombination(
    nArr: IntArray,
    firstCharIdx: Int,
    secondCharIdx: Int,
    firstChar: Int,
    secondChar: Int
): IntArray {
    val numberChArr = nArr
    var firstInvalidIndex = firstCharIdx
    var lastInvalidIndex = secondCharIdx
    while (firstInvalidIndex > -1 && lastInvalidIndex > -1) {
        val baseIndex = minOf(firstInvalidIndex, lastInvalidIndex)
        val referenceIndex = maxOf(firstInvalidIndex, lastInvalidIndex)
        for (i in baseIndex + 1 until numberChArr.size) {
            if (numberChArr[i] == numberChArr[referenceIndex]) {
                numberChArr[referenceIndex] += 1
            }
        }
        firstInvalidIndex = numberChArr.indexOf(firstChar)
        lastInvalidIndex = numberChArr.indexOf(secondChar)
//        skips++
    }
    return numberChArr
}

fun incrementPairAndFive(
    nArr: IntArray,
    resolvedChArr: Map<Int, Pair<Int, Int>>
): IntArray {
    val numberChArr = nArr
    var firstIndexFive = resolvedChArr[5]?.first ?: -1
    var firstIndexPair = arrayOf(
        resolvedChArr[2]?.first,
        resolvedChArr[4]?.first,
        resolvedChArr[6]?.first,
        resolvedChArr[8]?.first,
    ).filterNotNull().min()
    while (firstIndexFive > -1 && firstIndexPair > -1) {
        val baseIndex = minOf(firstIndexFive, firstIndexPair)
        val referenceIndex = maxOf(firstIndexFive, firstIndexPair)
        for (i in baseIndex + 1 until numberChArr.size) {
            if (numberChArr[i] == numberChArr[referenceIndex]) {
                numberChArr[referenceIndex] += 1
            }
        }
        val charMap = resolveIntIdx(numberChArr)
        firstIndexFive = charMap[5]?.first ?: -1
        firstIndexPair = arrayOf(
            charMap[2]?.first,
            charMap[4]?.first,
            charMap[6]?.first,
            charMap[8]?.first,
        ).filterNotNull().minOrNull() ?: -1
//        skips++
    }
    return numberChArr
}

fun incrementUnsorted(
    nArr: IntArray,
    resolvedChArr: Map<Int, Pair<Int, Int>>
): IntArray {
    val numberChArr = nArr
    var firstUnsorted = numberChArr
        .asSequence()
        .zipWithNext { a, b -> if (a > b) a else null }
        .firstNotNullOfOrNull { it }

    while (firstUnsorted != null) {
        val unsortedFirstIdx = resolvedChArr[firstUnsorted]?.first ?: numberChArr.size
        for (i in unsortedFirstIdx + 1 until numberChArr.size) {
            numberChArr[i] = firstUnsorted
//            skips++
        }
        firstUnsorted = numberChArr
            .asSequence()
            .zipWithNext { a, b -> if (a > b) a else null }
            .firstNotNullOfOrNull { it }
    }

    return numberChArr
}

fun resolveIntIdx(arr: IntArray): Map<Int, Pair<Int, Int>> {
    val idxMap = mutableMapOf<Int, Pair<Int, Int>>()

    arr.forEachIndexed { idx, item ->
        idxMap.putIfAbsent(item, Pair(idx, idx))
        idxMap.computeIfPresent(item) { _, v -> Pair(v.first, idx) }
    }

    return idxMap
}
