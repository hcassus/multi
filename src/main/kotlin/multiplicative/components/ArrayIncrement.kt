package multiplicative.components

fun incrementArray(n: IntArray): IntArray {
    val size = n.size
    val lastIdx = size - 1
    n[lastIdx]++
    var lastIncIdx = lastIdx

    val tailInc = if (size > 1) 2 else 0
    val headInc = if (size > 1) 2 else 1

    while (n[lastIncIdx] > 9) {
        n[lastIncIdx] = tailInc
        if (lastIncIdx - 1 >= 0) {
            if (n[lastIncIdx - 1] <= 9) {
                n[lastIncIdx - 1]++
            } else {
                lastIncIdx--
                continue
            }
        } else {
            return intArrayOf(headInc) + IntArray(n.size) { tailInc }
        }
        lastIncIdx--
    }
    return n
}
