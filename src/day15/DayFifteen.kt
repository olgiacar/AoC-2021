package day15

import SolutionInterface
import java.util.*


/**
 * Drawn some inspiration from:
 * https://github.com/ClouddJR/AdventOfCode2021/blob/master/src/main/kotlin/com/clouddjr/advent2021/Day15.kt
 */
class DayFifteen : SolutionInterface(packageName = "day15", testSolutionOne = 40, testSolutionTwo = 315) {
    private val directions = setOf(Pair(-1, 0), Pair(0, -1), Pair(1, 0), Pair(0, 1))

    override fun exerciseOne(input: List<String>) = getPathLength(input)

    override fun exerciseTwo(input: List<String>) = getPathLength(input, true)

    private fun getPathLength(input: List<String>, expand: Boolean = false): Int {
        var risks = input.map { line -> line.map { it.digitToInt() } }
        if (expand) risks = risks.expand()
        val dist = risks.map { it.map { Int.MAX_VALUE }.toMutableList() }.also { it[0][0] = 0 }
        findPath(risks, dist)
        return dist.last().last()
    }

    private fun findPath(
        risks: List<List<Int>>,
        dist: List<MutableList<Int>>,
        visited: MutableSet<Pair<Int, Int>> = mutableSetOf()
    ) {
        val toVisit = PriorityQueue<Pair<Int, Int>> { (a, b), (c, d) -> dist[a][b].compareTo(dist[c][d]) }
            .apply { add(0 to 0) }
        while (visited.size != risks.size * risks.size) {
            val current = toVisit.poll().also { visited.add(it) }
            updateNeighbours(current, risks, dist, visited, toVisit)
        }
    }

    private fun updateNeighbours(
        current: Pair<Int, Int>,
        risks: List<List<Int>>,
        dist: List<MutableList<Int>>,
        visited: MutableSet<Pair<Int, Int>>,
        toVisit: PriorityQueue<Pair<Int, Int>>
    ) {
        getNeighbours(visited, current, risks.indices).forEach {
            val (x, y) = it
            if (dist[x][y] > dist[current.first][current.second] + risks[x][y]) {
                dist[x][y] = dist[current.first][current.second] + risks[x][y]
                toVisit.add(Pair(x, y))
            }
        }
        visited.add(current)
    }

    private fun getNeighbours(v: Set<Pair<Int, Int>>, current: Pair<Int, Int>, range: IntRange): List<Pair<Int, Int>> {
        return directions.filter { current.first + it.first in range && current.second + it.second in range }
            .map { Pair(current.first + it.first, current.second + it.second) }
            .filter { it !in v }
    }

    private fun List<List<Int>>.expand(): List<List<Int>> {
        val expandedRight = map { row -> (1 until 5).fold(row) { acc, step -> acc + row.increasedAndCapped(step) } }
        return (1 until 5).fold(expandedRight) { acc, step -> acc + expandedRight.increased(step) }
    }

    private fun List<List<Int>>.increased(by: Int) = map { row -> row.increasedAndCapped(by) }

    private fun List<Int>.increasedAndCapped(by: Int) = map { level -> (level + by).let { if (it > 9) it - 9 else it } }
}

private fun main() = DayFifteen().run()