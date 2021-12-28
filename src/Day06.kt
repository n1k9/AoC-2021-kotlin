fun main() {
//    fun listSize(list:MutableList<Int>): Long {
//        var count:Long = 0
//        for (v in list) count +=1
//        return count
//    }


    fun part1(input: List<Byte>, days: Int, debug:Boolean = false): Long {
        println("Initial state: ${input.joinToString(separator = ",")}")
        print('\n')

        val fiches: MutableList<Byte> =  input.toMutableList()
        for ( d in 1..days ) {
            println("Init $d day")
            for ( i in fiches.indices) {
                fiches[i] = (fiches[i] - 1).toByte()
                if (fiches[i] < 0) {
                    fiches[i] = 6
                    fiches.add(8)
                }
            }
            if ( debug ) {
                print("After $d day${if (d > 1) 's' else ""}: ${fiches.joinToString(separator = ",")}")
                print('\n')
            }
//            println(" > n. fiches ${fiches.size.toLong()}")
        }
        return fiches.size.toLong()
    }

    fun part2(input: List<Byte>, days: Int): Long {
        fun gen(lifeCycleDay: Byte, days: Int): Long {

            if ( days == 0 ) return 1
            if ( lifeCycleDay == 0.toByte() ) return gen(6, days-1) + gen(8, days-1)
            return gen((lifeCycleDay - 1).toByte(), days-1)
        }

        var h: HashMap<Byte, Long> = HashMap<Byte, Long>()
        var count = 0L
        for (i in input.indices) {
            println("fish number $i by ${input.size}")
            if ( h.containsKey(input[i]) )
                count +=  h.getOrDefault(input[i], 0L)
            else {
                val c: Long = gen(input[i], days)
                count += c
                h[input[i]] = c
            }
        }
        return count
    }

    val startTestList: List<Byte> = listOf(3,4,3,1,2)
    check(part1(startTestList, 18, debug = true) == 26L)
    check(part1(startTestList, 80) == 5934L)
    check(part2(startTestList, 80) == 5934L)
//    println(part2(startTestList, 80))
//    check(part2(startTestList, 256) == 26_984_457_539L)
//    println(part2(startTestList, 256))

    val input = readInput("Day06_data").first()
    val inputList: List<Byte> = input.split(",").map { it.toByte() }
    println(part1(inputList, 80))
    println(part2(inputList, 256))
}
