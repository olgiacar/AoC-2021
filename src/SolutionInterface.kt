import java.io.File

abstract class SolutionInterface(
    packageName: String,
    private val testSolutionOne: Int,
    private val testSolutionTwo: Int
) {
    private val inputPath = "$packageName/input"
    private val testPath = "$packageName/test"

    abstract fun exerciseOne(input: List<String>): Int
    abstract fun exerciseTwo(input: List<String>): Int

    fun run() {
        checkTestInput()
        runSolution()
    }

    private fun runSolution() {
        val lines = readLines(inputPath)
        val solutionOne = exerciseOne(lines)
        val solutionTwo = exerciseTwo(lines)
        printSolutions(solutionOne, solutionTwo)
    }

    private fun checkTestInput() {
        val lines = readLines(testPath)
        val one = exerciseOne(lines)
        val two = exerciseTwo(lines)
        println("Expected: $testSolutionOne, got: $one")
        check(one == testSolutionOne)
        println("Expected: $testSolutionTwo, got: $two")
        check(two == testSolutionTwo)
    }

    private fun readLines(fileName: String): List<String> {
        return File("src", "$fileName.txt").readLines()
    }

    private fun printSolutions(one: Int, two: Int) {
        println("Exercise one: $one")
        println("Exercise two: $two")
    }
}