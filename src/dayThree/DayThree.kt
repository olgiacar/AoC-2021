package dayThree

import SolutionInterface
import kotlin.math.pow


class DayThree : SolutionInterface(fileName = "dayThree/input-03") {
    override fun exerciseOne(input: List<String>): String {
        var gamma = ""
        (0 until input[0].length).forEach { i -> input.mostCommonBit(i).also { gamma = "$gamma$it" } }

        val epsilon = invert(gamma)
        val solutionOne = toDecimal(gamma) * toDecimal(epsilon)
        return solutionOne.toString()
    }

    override fun exerciseTwo(input: List<String>): String {
        var max = input.toList()
        var min = input.toList()
        (0 until input[0].length).forEach { index ->
            if (max.size > 1) {
                val mostCommon = max.mostCommonBit(index)
                max = max.filter { it[index] == mostCommon }
            }
            if (min.size > 1) {
                val leastCommon = min.leastCommonBit(index)
                min = min.filter { it[index] == leastCommon }
            }
        }
        return (toDecimal(max.first()) * toDecimal(min.first())).toString()
    }

    private fun invert(gamma: String) = String(gamma.map { if (it == '1') '0' else '1' }.toCharArray())

    private fun List<String>.mostCommonBit(index: Int): Char {
        val max = this.groupBy { it[index] }.maxByOrNull { it.value.size }
        if (max?.value?.size == this.size / 2) return '1'
        return max?.key!!
    }

    private fun List<String>.leastCommonBit(index: Int): Char {
        val min = this.groupBy { it[index] }.minByOrNull { it.value.size }
        if (min?.value?.size!! * 2 >= this.size) return '0'
        return min.key
    }

    private fun toDecimal(binary: String): Int {
        val length = binary.length - 1
        return binary.map { if (it == '1') 1 else 0 }
            .reduceRightIndexed { index, c, acc ->
                acc + c * 2.0.pow(length - index).toInt()
            }
    }
}

fun main() = DayThree().run()