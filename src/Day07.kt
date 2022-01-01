import java.util.*
import kotlin.math.absoluteValue

fun main() {
    fun itemWithMaxFrequency(list: List<Int>): Int {
        var max = 0
        var itemMax = 0
        for (item in list.distinct()) {
            val frq = Collections.frequency(list, item)
            if (frq > max) {
                max = frq
                itemMax = item
            }
        }
        return itemMax
    }

    fun fuelConsumption(positions: List<Int>, pos: Int): Int {
        var fuel = 0
        for (c in positions) {
            fuel += (c - pos).absoluteValue
        }
        return fuel
    }

    fun fuelConsumption2(positions: List<Int>, pos: Int): Int {
        var fuel = 0
        for (c in positions) {
            val cost = ((c - pos).absoluteValue + 1) * (c - pos).absoluteValue / 2
            fuel += cost
        }
        return fuel
    }

    fun crubsFuelConsumption(positions: List<Int>, fuel: (List<Int>, Int) -> Int ): Int {
        var best: Int = itemWithMaxFrequency(positions)

        do {
            val m1 = fuel(positions, best)
            val m2 = fuel(positions, best + 1)
            val m3 = fuel(positions, best - 1)

            if (m1 < m2 && m1 < m3)
                break
            else if (m2 < m1)
                best += 1
            else if (m3 < m1)
                best -= 1
        } while (true)
//        println("Part1 Best pos: $best, Fuel: ${fuel(positions, best)}")
        return fuel(positions, best)
    }

    val testInput = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
    check(crubsFuelConsumption(testInput, ::fuelConsumption) == 37)
    check(crubsFuelConsumption(testInput, ::fuelConsumption2) == 168)

    val input = readInput("Day07_data")
    val inputList: List<Int> = input[0].split(",").map { it.toInt() }
    println("Input size: ${inputList.size}")
    println("Part1: Fuel spend: ${crubsFuelConsumption(inputList, ::fuelConsumption)}")
    println("Part2: Fuel spend: ${crubsFuelConsumption(inputList, ::fuelConsumption2)}")
}


