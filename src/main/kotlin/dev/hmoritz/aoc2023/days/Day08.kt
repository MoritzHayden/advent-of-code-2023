/**
 * Advent of Code 2023, Day 8
 * Link: http://adventofcode.com/2023/day/8
 */
package dev.hmoritz.aoc2023.days

import dev.hmoritz.aoc2023.models.Day
import dev.hmoritz.aoc2023.util.Constants.filenames
import dev.hmoritz.aoc2023.util.Utils.readFile
import dev.hmoritz.aoc2023.util.Utils.writeSolutionsToFile

class Day08() : Day {
    private val filename = filenames[7]
    private val input = readFile(filename)

    override fun solve() {
        val solutionPart1 = solvePart1()
        val solutionPart2 = solvePart2()
        writeSolutionsToFile(filename, solutionPart1, solutionPart2)
    }

    private fun solvePart1(): String {
        val instructions = parseInstructions()
        val root = parseMap()
        var currentLocation = "AAA"
        var steps = 0
        while (currentLocation != "ZZZ") {
            steps++
            val instruction = instructions[(steps - 1) % instructions.size]
            currentLocation = move(instruction, currentLocation, root)
        }
        return steps.toString()
    }

    private fun solvePart2(): String {
        // STUB
        return ""
    }

    private fun move(instruction: Char, location: String, map: Map<String, Pair<String, String>>): String {
        return when (instruction) {
            'L' -> map[location]?.first ?: location
            'R' -> map[location]?.second ?: location
            else -> location
        }
    }

    private fun parseInstructions(): CharArray {
        return input[0].toCharArray()
    }

    private fun parseMap(): Map<String, Pair<String, String>> {
        val map = mutableMapOf<String, Pair<String, String>>()
        for (i in 2..<input.size) {
            val splitLine = input[i].split(" = ")
            val root = splitLine[0]
            val children = splitLine[1]
                .replace("(", "")
                .replace(")", "")
                .replace(",", "")
                .split(" ")
            val left = children[0]
            val right = children[1]
            map[root] = Pair(left, right)
        }
        return map
    }
}
