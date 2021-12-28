fun main() {
//    fun printList(list: List<Int>) {
//        for (v in list) {
//            print(" $v")
//        }
//    }

    fun part1(input: List<Int>, days: Int, debug:Boolean = false): Int {
        println("Initial state: ${input.joinToString(separator = ",")}")
        print('\n')

        var fiches: MutableList<Int> =  input.toMutableList()
        for ( d in 1..days ) {
            for ( i in fiches.indices) {
                fiches[i] -= 1
                if (fiches[i] < 0) {
                    fiches[i] = 6
                    fiches.add(8)
                }
            }
            if ( debug ) {
                print("After $d day${if (d > 1) 's' else ""}: ${fiches.joinToString(separator = ",")}")
                print('\n')
            }
        }
        return fiches.size
    }

    fun part2(input: List<Int>): Int {
        return input.size
    }

    val startTestList: List<Int> = listOf(3,4,3,1,2)
    check(part1(startTestList, 80) == 5934)
    check(part1(startTestList, 18) == 26)
//    check(part2(startList) == 0)
    val input = readInput("Day06_data").first()
    val inputList: List<Int> = input.split(",").map { it.toInt() }
    println(part1(inputList, 80))
}
