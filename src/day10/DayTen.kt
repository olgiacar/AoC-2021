package day10

import SolutionInterface
import java.util.*

class DayTen : SolutionInterface(packageName = "day10", testSolutionOne = 26397, testSolutionTwo = 288957) {
    private val pairs = mapOf(")" to "(", ">" to "<", "}" to "{", "]" to "[")
    private val errorScores = mapOf(")" to 3, ">" to 25137, "}" to 1197, "]" to 57)
    private val points = mapOf("(" to 1, "<" to 4, "{" to 3, "[" to 2)

    override fun exerciseOne(input: List<String>): Int {
        return input.map { line -> line.map { it.toString() } }.mapNotNull { getIllegalBracket(it) }
            .mapNotNull { errorScores[it] }.sum()
    }

    override fun exerciseTwo(input: List<String>): Int {
        val scores = input.asSequence().map { line -> line.map { it.toString() } }
            .filter { getIllegalBracket(it) == null }.map { getOpenBrackets(it) }
            .map { getScore(it) }.sortedBy { it }.toList()
        val solution = scores[scores.size / 2]
        println("Long Solution: $solution")
        return solution.toInt()
    }

    private fun getIllegalBracket(brackets: List<String>): String? {
        val openBrackets = Stack<String>()
        brackets.forEach {
            if (openBrackets.isEmpty()) openBrackets.push(it)
            if (pairs.values.contains(it)) openBrackets.push(it)
            else {
                val lastOpen = openBrackets.peek()
                if (lastOpen == pairs[it]) openBrackets.pop()
                else return it
            }
        }
        return null
    }

    private fun getOpenBrackets(brackets: List<String>): List<String> {
        val openBrackets = Stack<String>()
        brackets.forEach {
            if (openBrackets.isEmpty()) openBrackets.push(it)
            else if (pairs.values.contains(it)) openBrackets.push(it)
            else {
                val lastOpen = openBrackets.peek()
                if (lastOpen == pairs[it]) openBrackets.pop()
            }
        }
        return openBrackets.toList()
    }

    private fun getScore(openBrackets: List<String>): Long {
        var score = 0L
        openBrackets.reversed().forEach {
            score *= 5L
            score += points[it]!!.toLong()
        }
        return score
    }
}

private fun main() = DayTen().run()