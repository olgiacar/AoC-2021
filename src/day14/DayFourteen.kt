package day14

import SolutionInterface
import java.math.BigInteger

class DayFourteen :
    SolutionInterface(packageName = "day14", testSolutionOne = 1588, testSolutionTwo = 2051339865) {
    override fun exerciseOne(input: List<String>) = getResult(input, 10)

    override fun exerciseTwo(input: List<String>) = getResult(input, 40)

    private fun getResult(input: List<String>, iterations: Int): Int {
        val pairs = input.filter { it.contains("->") }
            .map { it.split(" -> ") }.associate { Pair(it[0], it[1]) }
        val result = computeString(input.first().windowed(2).associateWith { BigInteger.ONE }, pairs, iterations)
        val charMap = getCharMap(result)
        val solution = (charMap.values.maxOf { it } - charMap.values.minOf { it } + BigInteger.ONE) / BigInteger.TWO
        println(solution)
        return solution.toInt()
    }

    private fun getCharMap(result: Map<String, BigInteger>): Map<Char, BigInteger> {
        return mutableMapOf<Char, BigInteger>().withDefault { BigInteger.ZERO }.apply {
            result.forEach { pair ->
                pair.key.forEach { put(it, getValue(it).plus(pair.value)) }
            }
        }
    }

    private fun computeString(
        start: Map<String, BigInteger>,
        pairs: Map<String, String>,
        iterations: Int
    ): Map<String, BigInteger> {
        var current = start
        repeat(iterations) { current = doIteration(current, pairs) }
        return current
    }

    private fun doIteration(current: Map<String, BigInteger>, pairs: Map<String, String>): Map<String, BigInteger> {
        return mutableMapOf<String, BigInteger>().withDefault { BigInteger.ZERO }.apply {
            current.forEach {
                if (it.key in pairs.keys) {
                    val char = pairs[it.key]!!
                    put("${it.key[0]}$char", getValue("${it.key[0]}$char") + it.value)
                    put("$char${it.key[1]}", getValue("$char${it.key[1]}") + it.value)
                } else put(it.key, it.value)
            }
        }
    }
}

private fun main() = DayFourteen().run()