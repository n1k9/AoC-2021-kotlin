

fun main() {
    val numSegments = mapOf( 2 to 1,3 to 7, 4 to 4, 7 to 8)
//    val segs = mapOf (
//        "abcefg" to 0,
//        "cf" to 1,
//        "acdef" to 2,
//        "acdfg" to 3,
//        "bcdf" to 4,
//        "abdfg" to 5,
//        "abdefg" to 6,
//        "acf" to 7,
//        "abcdefg" to 8,
//        "abcdfg" to 9
//        )

    fun translateSegment(enc: String): MutableMap<Int, String> {
        val segments: List<String> = enc.split(" ").sortedBy { it.length }
        var number: MutableMap<Int, String> = mutableMapOf(
            1 to segments[0].sortedString(),
            7 to segments[1].sortedString(),
            4 to segments[2].sortedString(),
            8 to segments[9].sortedString(),
        )
        val cfSeg = number[1]!!
        val bdSeg = number[4]!!.filter { it !in cfSeg }
        val egSeg = number[8]!!.filter { it !in bdSeg + number[7].toString() }

        for ( s in segments ) {
            when (s.length) {
                5 -> when {
                    s.filter { it in bdSeg }.length == 2 -> number[5] = s.sortedString()
                    s.filter { it in cfSeg }.length == 2 -> number[3] = s.sortedString()
                    else -> number[2] = s.sortedString()
                }
                6 -> when {
                    s.filter { it in cfSeg + bdSeg }.length == 4 -> number[9] = s.sortedString()
                    s.filter { it in bdSeg + egSeg }.length == 4 -> number[6] = s.sortedString()
                    else -> number[0] = s.sortedString()
                }
            }
        }
//        println(number)
        return number
    }

    fun part1(input: List<String>): Int {
        var count = 0
        for (line in input) {
            val signal: List<String> = line.split(" | ")
            val segments: List<String> = signal[1].split(" ")
            segments.forEach { if ( it.length in numSegments) count += 1 }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var tot: Int = 0
        for (line in input) {
            val (encoder, signal) = line.split(" | ")
            val map: MutableMap<Int, String> = translateSegment(encoder)
            var sum: Int = 0
            for ( d in signal.split(' ') ) {
                map.onEach {
                    if ( it.value == d.sortedString() ) {
                        sum = 10*sum + it.key
                    }
                }
            }
            tot += sum
        }
        return tot
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    val checkInput: List<String> = listOf("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")
    check(part2(checkInput) == 5353)
    check(part2(testInput) == 61229)

    val input = readInput("Day08_data")
    println("Part1: ${part1(input)}")
    println("Part2: ${part2(input)}")
}
