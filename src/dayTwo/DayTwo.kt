package dayTwo

import SolutionInterface

class DayTwo : SolutionInterface(fileName = "dayTwo/input-02") {
    override fun exerciseOne(input: List<String>): String {
        val (horizontalDistance, depth) = mapToPairs(input).reduce { a, b ->
            Pair(
                a.first + b.first,
                a.second + b.second
            )
        }
        return ((horizontalDistance * depth).toString())
    }

    override fun exerciseTwo(input: List<String>): String {
        var solution = Triple(0, 0, 0)
        mapToPairs(input).forEach {
            solution = Triple(
                solution.first + it.first,
                solution.second + (it.first * solution.third),
                solution.third + it.second
            )
        }
        return ((solution.first * solution.second).toString())
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

fun main() = DayTwo().run()