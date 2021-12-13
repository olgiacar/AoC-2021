package day1

import SolutionInterface

class DayOne : SolutionInterface(packageName = "day1", testSolutionOne = 7, testSolutionTwo = 5) {

    override fun exerciseOne(input: List<String>): Int {
        return input.map { it.toInt() }
            .zipWithNext { a, b -> if (b > a) 1 else 0 }
            .sum()
    }

    override fun exerciseTwo(input: List<String>): Int {
        return input.asSequence().map { it.toInt() }.windowed(3)
            .map { it.sum() }
            .zipWithNext { a, b -> if (b > a) 1 else 0 }
            .sum()
    }

}

private fun main() = DayOne().run()