package daySeven

import SolutionInterface
import kotlin.math.absoluteValue
import kotlin.math.min

class DaySeven : SolutionInterface(packageName = "daySeven", testSolutionOne = 37, testSolutionTwo = 168) {
    override fun exerciseOne(input: List<String>): Int {
        val positions = input.first().split(",").map { it.toInt() }
        return getMinDistance(positions) { it }
    }

    override fun exerciseTwo(input: List<String>): Int {
        val positions = input.first().split(",").map { it.toInt() }
        return getMinDistance(positions) { (it * (it + 1)) / 2 }
    }

    private fun getMinDistance(positions: List<Int>, fuelFunction: (Int) -> Int): Int {
        val min = positions.minOf { it }
        val max = positions.maxOf { it }
        var minDistance = calculateDistanceFromStartingPoint(positions, min, fuelFunction)
        ((min + 1)..max).forEach {
            minDistance = min(minDistance, calculateDistanceFromStartingPoint(positions, it, fuelFunction))
        }
        return minDistance
    }

    private fun calculateDistanceFromStartingPoint(
        positions: List<Int>,
        startingPoint: Int,
        fuelFunc: (Int) -> Int
    ): Int {
        return positions.sumOf { fuelFunc((startingPoint - it).absoluteValue) }
    }
}

private fun main() = DaySeven().run()