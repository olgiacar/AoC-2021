fun main() {
    val lines = readInput("input-01").map { it.toInt() }
    val totalPartOne = lines.zipWithNext { a, b -> if (b > a) 1 else 0 }.sum()
    println(totalPartOne)

    val totalPartTwo = lines.windowed(3).map { it.sum() }
        .zipWithNext { a, b -> if (b > a) 1 else 0 }.sum()
    println(totalPartTwo)
}
