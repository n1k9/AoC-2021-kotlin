import java.lang.Integer.min
import java.lang.Integer.max
import java.lang.Math.abs

data class Point(
    val x: Int,
    val y: Int
)

fun main() {
    fun displayBoard(board: Array<IntArray>) {
        for ( row in board) {
            for (v in row) print(if (v > 0 ) v else '.')
            println()
        }
        println()
    }

    fun drawLine(board: Array<IntArray>, p1: Point, p2: Point): Array<IntArray> {
        val xDir = if (p1.x == p2.x) 0 else if (p1.x < p2.x ) +1 else -1
        val yDir = if (p1.y == p2.y) 0 else if (p1.y < p2.y ) +1 else -1

        val size = if ( xDir != 0 ) abs(p1.x - p2.x) else abs(p1.y - p2.y)
        for ( i in 0..size ) {
            board[p1.y + i*yDir][p1.x + i*xDir] += 1
        }
        return board
    }

    fun countOverlap(board: Array<IntArray>): Int {
        var count = 0
        for ( row in board ) {
            for ( value in row ) {
                if ( value > 1 ) count += 1
            }
        }
        return count
    }

/*    fun part1(input: List<String>): Int {
        var board = Array(1000) { IntArray(1000) {0} }
        for ( s in input ) {
            val line = s.split(" -> ")
            val r1 = line[0].split(",").map { it.toInt() }
            val p1 = Point(x = r1[0], y = r1[1])
            val r2 = line[1].split(",").map { it.toInt() }
            val p2 =  Point(x = r2[0], y = r2[1])

            board = drawLine(board, p1, p2)
        }
        return countOverlap(board)
    }*/

    fun process(input: List<String>, drawAllLine: Boolean, size: Int = 1000): Int {
        var board = Array(size) { IntArray(size) {0} }
        for ( s in input ) {
            val line = s.split(" -> ")
            val r1 = line[0].split(",").map { it.toInt() }
            val p1 = Point(x = r1[0], y = r1[1])
            val r2 = line[1].split(",").map { it.toInt() }
            val p2 = Point(x = r2[0], y = r2[1])

            if (!drawAllLine && p1.x != p2.x && p1.y !=  p2.y)
                continue
            board = drawLine(board, p1, p2)
        }
        if ( size <= 10 ) displayBoard(board)
        return countOverlap(board)
    }

    val testInput = readInput("Day05_test")
//    println(process(testInput, drawAllLine = false, size = 10))
    check(process(testInput, drawAllLine = false, size = 10) == 5)

//    println(process(testInput, drawAllLine = true, size = 10))
    check(process(testInput, drawAllLine = true, size = 10) == 12)

    val input = readInput("Day05_data")
    println("Part 1: ${process(input, drawAllLine = false, size = 1000)}")
    println("Part 2: ${process(input, drawAllLine = true, size = 1000) }")
}
