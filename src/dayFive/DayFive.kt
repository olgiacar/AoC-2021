package dayFive

import SolutionInterface
import java.lang.Integer.max
import kotlin.math.absoluteValue

class DayFive : SolutionInterface(packageName = "dayFive", testSolutionOne = "5", testSolutionTwo = "12") {

    override fun exerciseOne(input: List<String>): String {
        val lines = convertInput(input)
            .filter { it.first.first == it.second.first || it.first.second == it.second.second }
        val lineCountArray = getLineCountArray(lines)
        setLineCountArray(lines, lineCountArray)
        return countValuesLargerThanOne(lineCountArray).toString()
    }

    override fun exerciseTwo(input: List<String>): String {
        val lines = convertInput(input)
        val lineCount = getLineCountArray(lines)
        setLineCountArray(lines, lineCount)
        return countValuesLargerThanOne(lineCount).toString()
    }

    private fun countValuesLargerThanOne(array: Array<Array<Int>>): Int {
        var count = 0
        array.forEach { row -> row.forEach { if (it > 1) count++ } }
        return count
    }

    private fun setLineCountArray(lines: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>, lineCount: Array<Array<Int>>) {
        lines.forEach {
            val xDifference = it.second.first - it.first.first
            val yDifference = it.second.second - it.first.second
            val maxDifference = max(xDifference.absoluteValue, yDifference.absoluteValue)
            val x = if (xDifference == 0) 0 else xDifference / xDifference.absoluteValue
            val y = if (yDifference == 0) 0 else yDifference / yDifference.absoluteValue
            (0..maxDifference).forEach { step ->
                lineCount[it.first.first + step * x][it.first.second + step * y]++
            }
        }
    }

    private fun convertInput(input: List<String>): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> {
        return input.map { it.split("( -> )|(,)".toRegex()).map { num -> num.toInt() } }
            .map { Pair(Pair(it[0], it[1]), Pair(it[2], it[3])) }
    }

    private fun getLineCountArray(lines: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>): Array<Array<Int>> {
        val maxX = lines.maxOf { row -> max(row.first.first, row.second.first) }
        val maxY = lines.maxOf { row -> max(row.first.second, row.second.second) }
        return Array(maxX + 1) { Array(maxY + 1) { 0 } }
    }
}

fun main() = DayFive().run()