package dayOne

import SolutionInterface

class DayOne : SolutionInterface(fileName = "dayOne/input-01") {

    override fun exerciseOne(input: List<String>): String {
        return input.map { it.toInt() }
            .zipWithNext { a, b -> if (b > a) 1 else 0 }
            .sum().toString()
    }

    override fun exerciseTwo(input: List<String>): String {
        return input.asSequence().map { it.toInt() }.windowed(3)
            .map { it.sum() }
            .zipWithNext { a, b -> if (b > a) 1 else 0 }
            .sum().toString()
    }

}

fun main() = DayOne().run()