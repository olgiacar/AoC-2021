abstract class SolutionInterface(private val fileName: String) {

    abstract fun exerciseOne(input: List<String>): String
    abstract fun exerciseTwo(input: List<String>): String

    fun run() {
        val lines = readLines(fileName)
        val solutionOne = exerciseOne(lines)
        val solutionTwo = exerciseTwo(lines)
        printSolutions(solutionOne, solutionTwo)
    }

    private fun readLines(fileName: String): List<String> {
        return readInput(fileName)
    }
}