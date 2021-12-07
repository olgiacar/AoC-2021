package daySix

import SolutionInterface
import java.math.BigInteger

class DaySix :
    SolutionInterface(packageName = "daySix", testSolutionOne = 5934, testSolutionTwo = 26984457539.toInt()) {
    override fun exerciseOne(input: List<String>): Int {
        val fish = input.first().split(",").map { it.toInt() }
        val temp = getFishAfterDays(mutableListOf(fish), 40)
        return getFishAfterDays(temp, 40).sumOf { it.size }
    }

    override fun exerciseTwo(input: List<String>): Int {
        val fish = input.first().split(",").map { it.toInt() }
        val fishes = getFishAfterDays(mutableListOf(fish), 128)
        var count = BigInteger.ZERO
        (0..8).forEach {
            val countForValue = getFishAfterDays(mutableListOf(listOf(it)), 128).sumOf { a -> a.size }
            val amount = fishes.sumOf { a -> a.count { fish -> fish == it } }
            val bigCount = BigInteger.valueOf(countForValue.toLong())
            val bigAmount = BigInteger.valueOf(amount.toLong())
            count = count.add(bigAmount.multiply(bigCount))
        }
        println("Exercise Two (BigInt): $count")
        return count.toInt()
    }

    private fun getFishAfterDays(input: MutableList<List<Int>>, x: Int): MutableList<List<Int>> {
        var fishes = input
        repeat(x) {
            val newFishCount = fishes.sumOf { it.count { fish -> fish == 0 } }
            fishes = fishes.map { it.map { fish -> if (fish == 0) 6 else fish - 1 } }.toMutableList()
            val newFishes = mutableListOf<Int>()
            repeat(newFishCount) { newFishes.add(8) }
            fishes.add(newFishes)
        }
        return fishes
    }

}

private fun main() = DaySix().run()