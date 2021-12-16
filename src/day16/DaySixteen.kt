package day16

import SolutionInterface
import java.math.BigInteger

class DaySixteen : SolutionInterface(packageName = "day16", testSolutionOne = 0, testSolutionTwo = 0) {
    override fun exerciseOne(input: List<String>): Int {
        hexToBinary(input.first())
        val packets = listOf(0)
        return 0
    }

    override fun exerciseTwo(input: List<String>): Int {
        return 0
    }

    private fun hexToBinary(hex: String) = BigInteger(hex, 16).toString(2)

    private fun decodePacket(s: String): String {
        val version = s.substring(0..2)
        val id = s.substring(3..5)
        return s
    }
}

private fun main() = DaySixteen().run()