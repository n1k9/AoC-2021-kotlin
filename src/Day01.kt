fun main() {
    fun part1(input: List<String>): Int {
        var count = 0
        for (i in 1 until input.size) {
            if (input[i].toInt() > input[i-1].toInt()) {
                count += 1
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        var prev = input[2].toInt() + input[1].toInt() + input[0].toInt()
        for (i in 3 until input.size) {
            val current = input[i].toInt() + input[i-1].toInt() + input[i-2].toInt()
            if (current > prev) {
                count += 1
            }
            prev = current
        }
        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01_data")
    println(part1(input))
    println(part2(input))
}
