abstract class SolutionInterface(
    packageName: String,
    private val testSolutionOne: String,
    private val testSolutionTwo: String
) {
    private val inputPath = "$packageName/input"
    private val testPath = "$packageName/test"

    abstract fun exerciseOne(input: List<String>): String
    abstract fun exerciseTwo(input: List<String>): String

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
        check(exerciseOne(lines) == testSolutionOne)
        check(exerciseTwo(lines) == testSolutionTwo)
    }

    private fun readLines(fileName: String): List<String> {
        return readInput(fileName)
    }
}