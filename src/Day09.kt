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

fun Heightmap.basinsExtension(r: Int, c: Int, basin: MutableList<Point> = mutableListOf()): Int {
    if (0 <= r && r < this.heightmap.size && 0 <= c && c < this.heightmap[r].length) {
        val v = this.get(r, c)
        val point = Point(r, c)
        if (v < 9 && point !in basin) {
            basin.add(point)
            if (Point(r - 1, c) !in basin)
                this.basinsExtension(r - 1, c, basin)
            if (Point(r + 1, c) !in basin)
                this.basinsExtension(r + 1, c, basin)
            if (Point(r, c - 1) !in basin)
                this.basinsExtension(r, c - 1, basin)
            if (Point(r, c + 1) !in basin)
                this.basinsExtension(r, c + 1, basin)
        }
    }
    return basin.size
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
    var basins: MutableList<Int> = mutableListOf()
    for (r in 0 until input.rowSize()) {
        for (c in 0 until input.colSize()) {
            if (input.lowerCrossNeighbors(r, c)) {
                basins.add(input.basinsExtension(r, c))
            }
        }
    }
    basins.sort()
    basins.reverse()
    return basins[0] * basins[1] * basins[2]
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
    check(part2(testInput) == 1134)

    val input = Heightmap(readInput("Day09_data"))
    println("Part1: ${part1(input)}")
    println("Part2: ${part2(input)}")
}
