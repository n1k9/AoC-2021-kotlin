fun main() {
    fun part1(input: List<String>): Int {
        var pos = 0
        var depth = 0
        for (command in input) {
            val c = command.split(' ')
            when (c[0]) {
                "forward" -> pos += c[1].toInt()
                "up" -> depth -= c[1].toInt()
                "down" -> depth += c[1].toInt()
            }
        }
        return pos * depth
    }

    fun part2(input: List<String>): Int {
        var pos = 0
        var depth = 0
        var aim = 0
        for (comman in input)
        {
            val c = comman.split(' ')
            when (c[0]) {
                "forward" -> {
                    pos += c[1].toInt()
                    depth += c[1].toInt() * aim
                }
                "up" -> aim -= c[1].toInt()
                "down" -> aim += c[1].toInt()
            }
        }
        return pos * depth
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02_data")
    println(part1(input))
    println(part2(input))
}