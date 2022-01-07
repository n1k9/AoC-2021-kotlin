data class Heightmap(val heightmap: List<String>)

fun Heightmap.get(r: Int, c: Int): Byte = this.heightmap[r][c].toString().toByte()

fun Heightmap.lowerCrossNeighbors(r: Int, c: Int): Boolean {
    val p = this.get(r, c)
    val n = if (r - 1 >= 0) this.get(r - 1, c) else 9
    val s = if (r + 1 < this.heightmap.size) this.get(r + 1, c) else 9
    val w = if (c - 1 >= 0) this.get(r, c - 1) else 9
    val e = if (c + 1 < this.heightmap[r].length) this.get(r, c + 1) else 9
    return p < n && p < w && p < s && p < e
}

fun Heightmap.rowSize(): Int = this.heightmap.size
fun Heightmap.colSize(row: Int = 0): Int = this.heightmap[row].length

fun part1(input: Heightmap): Int {
    var riskLevel = 0
    for (r in 0 until input.rowSize()) {
        for (c in 0 until input.colSize()) {
            if (input.lowerCrossNeighbors(r, c)) riskLevel += input.get(r, c) + 1
        }
    }
    return riskLevel
}

fun part2(input: Heightmap): Int {
    return 0
}

fun main() {
    val testInput = Heightmap(
        listOf(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678"
        )
    )

    check(part1(testInput) == 15)
//    check(part2(testInput) == 0)

    val input = Heightmap(readInput("Day09_data"))
    println("Part1: ${part1(input)}")
//    println(part2(input))
}
