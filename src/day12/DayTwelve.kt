package day12

import SolutionInterface

class DayTwelve : SolutionInterface(packageName = "day12", testSolutionOne = 10, testSolutionTwo = 36) {
    override fun exerciseOne(input: List<String>): Int {
        val paths = mutableSetOf<String>()
        findPaths(getConnections(input), paths = paths)
        return paths.size
    }

    override fun exerciseTwo(input: List<String>): Int {
        val paths = mutableSetOf<String>()
        findPaths2(getConnections(input), paths = paths)
        return paths.size
    }

    private fun getConnections(input: List<String>): Map<String, List<String>> {
        val connections = input.map { it.split("-") }.map { Pair(it[0], it[1]) }
        val caves = mutableSetOf<String>()
        connections.forEach { caves.add(it.first); caves.add(it.second) }
        return caves.associateWith { getConnections(connections, it) }
    }

    private fun getConnections(connections: List<Pair<String, String>>, cave: String): List<String> {
        return connections.filter { it.first == cave || it.second == cave }
            .map { if (it.first == cave) it.second else it.first }
    }

    private fun findPaths(
        connections: Map<String, List<String>>,
        currentCave: String = "start",
        currentPath: String = "start",
        visited: Set<String> = setOf(),
        paths: MutableSet<String> = mutableSetOf()
    ) {
        if (currentCave in visited) return
        if (currentCave != "end") {
            connections[currentCave]?.forEach {
                val vis = mutableSetOf<String>()
                if (currentCave == currentCave.lowercase()) vis.add(currentCave)
                vis.addAll(visited)
                findPaths(connections, it, "$currentPath,$it", vis, paths)
            }
        } else paths.add(currentPath)
    }

    private fun findPaths2(
        connections: Map<String, List<String>>,
        currentCave: String = "start",
        currentPath: String = "start",
        visited: Set<String> = setOf(),
        paths: MutableSet<String> = mutableSetOf(),
        doubleVisited: Boolean = false
    ) {
        if (currentCave in visited) return
        if (currentCave != "end") {
            connections[currentCave]?.forEach {
                val vis = mutableSetOf<String>()
                if (currentCave == currentCave.lowercase()) vis.add(currentCave)
                vis.addAll(visited)
                findPaths2(connections, it, "$currentPath,$it", vis, paths, doubleVisited)
                if (!doubleVisited && currentCave != "start") {
                    findPaths2(connections, it, "$currentPath,$it", visited, paths, true)
                }
            }
        } else paths.add(currentPath)
    }
}

private fun main() = DayTwelve().run()