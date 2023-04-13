package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var T = br.readLine().toInt()
    val result = StringBuilder()

    while (T > 0) {
        val (start, target) = br.readLine().split(" ").map { it.toString() }
        val count = bfs(start, target)
        result.append("${if (count == -1) "Impossible" else count}\n")
        T--
    }

    println(result.toString())
}

private fun bfs(start: String, target: String): Int {
    val queue = LinkedList<Prime>()
    val visit = BooleanArray(10000) { false }
    queue.add(Prime(start, 0))
    visit[start.toInt()] = true

    while (queue.isNotEmpty()) {
        val current = queue.pop()

        if (current.number == target) return current.count

        for (i in 0..3) {
            for (j in 0..9) {
                val num = current.number[i] - '0'
                val nextNumber = when (i) {
                    0 -> "$j${current.number.substring(1, current.number.length)}"
                    1 -> "${current.number.substring(0, 1)}$j${current.number.substring(2, current.number.length)}"
                    2 -> "${current.number.substring(0, 2)}$j${current.number.substring(3, current.number.length)}"
                    else -> "${current.number.substring(0, current.number.length - 1)}$j"
                }

                // 4자리 숫자가 아니다.
                if (nextNumber.toInt() / 1000 == 0) continue
                // 현재 숫자와 같다.
                if (j == num) continue
                // 이미 탐색한 숫자.
                if (visit[nextNumber.toInt()]) continue
                // 소수가 아니다.
                if (!isPrime(nextNumber.toInt())) continue

                visit[nextNumber.toInt()] = true
                queue.add(Prime(nextNumber, current.count + 1))
            }
        }
    }

    return -1
}

private fun isPrime(number: Int): Boolean {
    for (i in 2 until number) {
        if (number % i == 0) return false
    }

    return true
}

private data class Prime(val number: String, val count: Int)