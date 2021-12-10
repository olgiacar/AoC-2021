package dayNine

import SolutionInterface

class DayNine : SolutionInterface(packageName = "dayNine", testSolutionOne = 15, testSolutionTwo = 1134) {
    override fun exerciseOne(input: List<String>): Int {
        val inputGrid = toArray(input)
        return getLowPoints(inputGrid).sumOf { row -> row.sumOf { inputGrid[it.first][it.second] + 1 } }
    }

    override fun exerciseTwo(input: List<String>): Int {
        val inputGrid = toArray(input)
        val lowPoints = getLowPoints(inputGrid)
        val basinSizes = mutableListOf<Int>()
        val visited = inputGrid.map { it.map { false }.toMutableList() }
        lowPoints.forEach { it.forEach { point -> basinSizes.add(getBasinSize(point, inputGrid, visited)) } }
        return basinSizes.sortedByDescending { it }.subList(0, 3).reduce { acc, i -> i * acc }
    }

    private fun toArray(input: List<String>): List<List<Int>> = input.map { line -> line.map { it.toString().toInt() } }

    private fun getLowPoints(grid: List<List<Int>>): List<List<Pair<Int, Int>>> {
        val width = grid.first().size
        val height = grid.size
        val lowPoints = grid.mapIndexed { row, it ->
            it.mapIndexedNotNull { column, value ->
                var isLow = true
                if (row > 0) isLow = isLow && value < grid[row - 1][column] // up
                if (column > 0) isLow = isLow && value < grid[row][column - 1] // left
                if (row < height - 1) isLow = isLow && value < grid[row + 1][column] // down
                if (column < width - 1) isLow = isLow && value < grid[row][column + 1] // right
                if (isLow) Pair(row, column) else null
            }
        }
        return lowPoints
    }

    private fun getBasinSize(start: Pair<Int, Int>, grid: List<List<Int>>, visited: List<MutableList<Boolean>>): Int {
        val count = mutableSetOf<Pair<Int, Int>>()
        getBasinSize(start.first, start.second, grid, count, visited)
        val fields = count.sortedBy { it.first }
        return fields.size
    }

    private fun getBasinSize(
        row: Int,
        column: Int,
        grid: List<List<Int>>,
        basinSize: MutableSet<Pair<Int, Int>>,
        visited: List<MutableList<Boolean>>,
    ) {
        if (visited[row][column]) return
        visited[row][column] = true
        if (grid[row][column] == 9) return
        basinSize.add(Pair(row, column))

        if (row > 0 && grid[row - 1][column] - 1 == grid[row][column])
            getBasinSize(row - 1, column, grid, basinSize, visited)
        if (column > 0 && grid[row][column - 1] - 1 == grid[row][column])
            getBasinSize(row, column - 1, grid, basinSize, visited)
        if (row < grid.size - 1 && grid[row + 1][column] - 1 == grid[row][column])
            getBasinSize(row + 1, column, grid, basinSize, visited)
        if (column < grid.first().size - 1 && grid[row][column + 1] - 1 == grid[row][column])
            getBasinSize(row, column + 1, grid, basinSize, visited)
    }
}

private fun main() = DayNine().run()