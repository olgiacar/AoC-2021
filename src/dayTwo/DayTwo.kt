package dayTwo

import SolutionInterface

class DayTwo : SolutionInterface(packageName = "dayTwo", testSolutionOne = 150, testSolutionTwo = 900) {
    override fun exerciseOne(input: List<String>): Int {
        val (horizontalDistance, depth) = mapToPairs(input).reduce { a, b ->
            Pair(
                a.first + b.first,
                a.second + b.second
            )
        }
        return ((horizontalDistance * depth))
    }

    override fun exerciseTwo(input: List<String>): Int {
        var solution = Triple(0, 0, 0)
        mapToPairs(input).forEach {
            solution = Triple(
                solution.first + it.first,
                solution.second + (it.first * solution.third),
                solution.third + it.second
            )
        }
        return ((solution.first * solution.second))
    }

    private fun mapToPairs(input: List<String>): List<Pair<Int, Int>> {
        return input.map {
            val (direction, distance) = it.split(" ")
            Pair(direction, distance.toInt())
        }.map {
            when (it.first) {
                "forward" -> Pair(it.second, 0)
                "down" -> Pair(0, it.second)
                "up" -> Pair(0, -it.second)
                else -> Pair(0, 0)
            }
        }
    }

}

private fun main() = DayTwo().run()