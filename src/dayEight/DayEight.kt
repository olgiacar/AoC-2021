package dayEight

import SolutionInterface

class DayEight : SolutionInterface(packageName = "dayEight", testSolutionOne = 26, testSolutionTwo = 61229) {
    override fun exerciseOne(input: List<String>): Int {
        val lines = input.map {
            val start = it.indexOf("|")
            it.substring(start + 2)
        }.map { it.split(" ") }
        return lines.sumOf { row -> row.filter { isObvious(it) }.size }
    }

    override fun exerciseTwo(input: List<String>): Int {
        val lines = input.map { it.split("|") }.map { Pair(it[0].trim(), it[1].trim()) }
        val numbers = lines.map {
            val digitMap = getDigitMap(it.first.split(" "))
            it.second.split(" ").map { digit -> getCorrectDigit(digit, digitMap) }
                .reduce { acc, s -> "$acc$s" }.toInt()
        }
        return numbers.sumOf { it }
    }

    private fun getCorrectDigit(digit: String, map: Map<String, String>): String {
        map.entries.forEach {
            val str = it.key.toCharArray().sorted().toString()
            val dig = digit.toCharArray().sorted().toString()
            if (str == dig) return it.value
        }
        return ""
    }

    private fun getDigitMap(line: List<String>): Map<String, String> {
        val one = getOne(line)
        val four = getFour(line)
        val seven = getSeven(line)
        val eight = getEight(line)
        val three = getThree(line, one)
        val six = getSix(line, one)
        val nine = getNine(line, four)
        val zero = getZero(line, six, nine)
        val five = getFive(line, three, nine)
        val two = getTwo(line, three, five)
        return mapOf(
            zero to "0",
            one to "1",
            two to "2",
            three to "3",
            four to "4",
            five to "5",
            six to "6",
            seven to "7",
            eight to "8",
            nine to "9"
        )
    }

    private fun getZero(line: List<String>, six: String, nine: String): String {
        return line.filter { it.length == 6 }.first { it != six && it != nine }
    }

    private fun getOne(line: List<String>): String {
        return line.first { it.length == 2 }
    }

    private fun getTwo(line: List<String>, three: String, five: String): String {
        return line.filter { it.length == 5 }.first { it != three && it != five }
    }

    private fun getThree(line: List<String>, one: String): String {
        return line.filter { it.length == 5 }.first { it.contains(one[0]) && it.contains(one[1]) }
    }

    private fun getFour(line: List<String>): String {
        return line.first { it.length == 4 }
    }

    private fun getFive(line: List<String>, three: String, nine: String): String {
        return line.filter { it.length == 5 }.filter { it != three }
            .first {
                nine.contains(it[0]) && nine.contains(it[1]) &&
                        nine.contains(it[2]) && nine.contains(it[3]) && nine.contains(it[4])
            }
    }

    private fun getSix(line: List<String>, one: String): String {
        return line.filter { it.length == 6 }.first { !(it.contains(one[0]) && it.contains(one[1])) }
    }

    private fun getSeven(line: List<String>): String {
        return line.first { it.length == 3 }
    }

    private fun getEight(line: List<String>): String {
        return line.first { it.length == 7 }
    }

    private fun getNine(line: List<String>, four: String): String {
        return line.filter { it.length == 6 }
            .first { it.contains(four[0]) && it.contains(four[1]) && it.contains(four[2]) && it.contains(four[3]) }
    }

    private fun isObvious(digit: String): Boolean {
        return when (digit.length) {
            2, 3, 4, 7 -> true
            else -> false
        }
    }
}

private fun main() = DayEight().run()