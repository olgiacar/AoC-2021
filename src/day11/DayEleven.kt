package day11

import SolutionInterface

class DayEleven : SolutionInterface(packageName = "day11", testSolutionOne = 1656, testSolutionTwo = 195) {
    private val surroundings = listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)
    override fun exerciseOne(input: List<String>): Int {
        val grid = input.map { it.map { value -> value.toString().toInt() }.toMutableList() }
        var sum = 0
        repeat(100) {
            sum += makeStep(grid)
        }
        return sum
    }

    override fun exerciseTwo(input: List<String>): Int {
        val grid = input.map { it.map { value -> value.toString().toInt() }.toMutableList() }
        var step = 0
        do {
            makeStep(grid)
            step++
        } while (!allLitSimultaneously(grid))
        return step
    }

    private fun makeStep(grid: List<MutableList<Int>>): Int {
        var count = 0
        val lit = grid.map { it.map { false }.toMutableList() }
        grid.forEachIndexed { r, row -> row.forEachIndexed { c, _ -> grid[r][c]++ } }
        while (!allLit(grid, lit)) {
            grid.forEachIndexed { i, row ->
                row.forEachIndexed { j, value ->
                    if (value > 9 && !lit[i][j]) {
                        increaseSurrounding(i, j, grid)
                        lit[i][j] = true
                        count++
                    }
                }
            }
        }
        grid.forEachIndexed { r, row -> row.forEachIndexed { c, _ -> if (grid[r][c] > 9) grid[r][c] = 0 } }
        return count
    }

    private fun increaseSurrounding(row: Int, column: Int, grid: List<MutableList<Int>>) {
        surroundings.forEach {
            if (row + it.first in 0..9 && column + it.second in 0..9) grid[row + it.first][column + it.second]++
        }
    }

    private fun allLit(grid: List<List<Int>>, lit: List<MutableList<Boolean>>): Boolean {
        grid.forEachIndexed { i, row ->
            row.forEachIndexed { j, value ->
                if (value > 9 && !lit[i][j]) return false
            }
        }
        return true
    }

    private fun allLitSimultaneously(grid: List<List<Int>>) = grid.sumOf { it.sum() } == 0
}

private fun main() = DayEleven().run()