package dayFour

import SolutionInterface
import daySeven.DaySeven

class DayFour : SolutionInterface(packageName = "dayFour", testSolutionOne = 4512, testSolutionTwo = 1924) {
    override fun exerciseOne(input: List<String>): Int {
        val numbers = input.first().split(",").map { it.toInt() }
        val boards = getBoards(input.subList(2, input.size))
        val iterator = numbers.iterator()
        var next: Int
        do {
            next = iterator.next()
            makeTurn(next, boards)
        } while (iterator.hasNext() && checkWinner(boards.map { it.second }) == null)
        val winner = boards.indices.first { isWinner(boards[it].second) }
        val score = getScore(boards[winner].first, boards[winner].second, next)
        return score
    }

    override fun exerciseTwo(input: List<String>): Int {
        val numbers = input.first().split(",").map { it.toInt() }
        var boards = getBoards(input.subList(2, input.size))
        val iterator = numbers.iterator()
        var next: Int
        do {
            next = iterator.next()
            makeTurn(next, boards)
            if (boards.size > 1) boards = boards.filter { !isWinner(it.second) }
        } while (iterator.hasNext() && checkWinner(boards.map { it.second }) == null)
        val lastWinner = boards.first()
        val score = getScore(lastWinner.first, lastWinner.second, next)
        return score
    }

    private fun getScore(intBoard: List<List<Int>>, booleanBoard: List<MutableList<Boolean>>, lastNumber: Int): Int {
        var total = 0
        booleanBoard.indices.forEach { row ->
            booleanBoard[row].indices.forEach { column ->
                if (booleanBoard[row][column].not()) {
                    total += intBoard[row][column]
                }
            }
        }
        return total * lastNumber
    }

    private fun makeTurn(number: Int, boards: List<Pair<List<List<Int>>, List<MutableList<Boolean>>>>) {
        boards.forEach { board ->
            val rowIndex = board.first.indices.firstOrNull { board.first[it].contains(number) }
            if (rowIndex != null) {
                val columnIndex = board.first[rowIndex].indexOf(number)
                board.second[rowIndex][columnIndex] = true
            }
        }
    }

    private fun checkWinner(boards: List<List<List<Boolean>>>): Int? =
        boards.indices.firstOrNull { isWinner(boards[it]) }


    private fun isWinner(board: List<List<Boolean>>): Boolean {
        val rowWinner = board.firstOrNull { it.reduce { acc, b -> acc && b } }
        val columnWinner = board.indices.map { index -> board.map { it[index] } }
            .firstOrNull { it.reduce { acc, b -> acc && b } }
        return rowWinner != null || columnWinner != null
    }

    private fun getBoards(input: List<String>): List<Pair<List<List<Int>>, List<MutableList<Boolean>>>> {
        val stringBoards = getStringBoards(input)
        val intBoards = stringBoards.map { board ->
            board.map { row ->
                row.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
            }
        }
        val booleanBoards = intBoards.map { board -> board.map { it.map { false }.toMutableList() } }
        return intBoards zip booleanBoards
    }

    private fun getStringBoards(input: List<String>): MutableList<List<String>> {
        val iterator = input.iterator()
        val boards = mutableListOf<List<String>>()
        while (iterator.hasNext()) {
            val board = mutableListOf<String>()
            do {
                val line = iterator.next()
                if (line.isNotBlank()) board.add(line)
            } while (line.isNotBlank() && iterator.hasNext())
            boards.add(board)
        }
        return boards
    }
}

private fun main() = DayFour().run()