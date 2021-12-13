package day13

import SolutionInterface

class DayThirteen : SolutionInterface(packageName = "day13", testSolutionOne = 17, testSolutionTwo = 0) {
    override fun exerciseOne(input: List<String>): Int {
        val dots = getDots(input)
        val folds = getFolds(input)
        return if (folds.first().first) foldX(dots, folds.first().second).size
        else foldY(dots, folds.first().second).size
    }

    override fun exerciseTwo(input: List<String>): Int {
        var dots = getDots(input)
        val folds = getFolds(input)
        folds.forEach {
            dots = if (it.first) foldX(dots, it.second) else foldY(dots, it.second)
        }
        printCode(dots)
        return 0
    }

    private fun getDots(input: List<String>) = input.filter { it.contains(",") }
        .map { it.split(",") }.map { Pair(it[0].toInt(), it[1].toInt()) }.toSet()

    private fun getFolds(input: List<String>) = input.filter { it.contains("fold") }.map { it.split(" ") }
        .map { it[2].split("=") }.map { Pair(it[0] == "x", it[1].toInt()) }

    private fun foldX(dots: Set<Pair<Int, Int>>, x: Int) = dots.map {
        if (it.first < x) it
        else Pair(2 * x - it.first, it.second)
    }.toSet()

    private fun foldY(dots: Set<Pair<Int, Int>>, y: Int) = dots.map {
        if (it.second < y) it
        else Pair(it.first, 2 * y - it.second)
    }.toSet()

    private fun printCode(dots: Set<Pair<Int, Int>>) {
        val width = dots.toMap().keys.maxOf { it } + 1
        val height = dots.map { it.second }.maxOf { it } + 1
        repeat(height) { y ->
            var line = ""
            repeat(width) { x ->
                line = "$line${if (dotExists(dots, x, y)) "#" else " "}"
            }
            println(line)
        }
    }

    private fun dotExists(dots: Set<Pair<Int, Int>>, x: Int, y: Int) = Pair(x, y) in dots
}

private fun main() = DayThirteen().run()