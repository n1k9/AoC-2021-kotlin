import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)


fun String.sortedString(): String = this.toCharArray().sorted().joinToString("")


data class CoordMap(val map: List<String>)

fun CoordMap.get(r: Int, c: Int): Byte = this.map[r][c].toString().toByte()
fun CoordMap.rowSize(): Int = this.map.size
fun CoordMap.colSize(row: Int = 0): Int = this.map[row].length