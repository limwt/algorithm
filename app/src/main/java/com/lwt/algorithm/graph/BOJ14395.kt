package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (s, t) = br.readLine().split(" ").map { it.toInt() }

    if (s == t) {
        println(0)
    } else {
        println(bfs(s, t))
    }
}

private fun bfs(s: Int, t: Int): String {
    val queue = LinkedList<Pair<Long, String>>()
    queue.add(Pair(s.toLong(), ""))
    val check = mutableListOf<Long>()

    while (queue.isNotEmpty()) {
        val current = queue.pop()

        if (current.first == t.toLong()) return current.second

        for (i in 0..3) {
            if (i == 3 && current.first == 0L) continue

            val nextNumber = when (i) {
                0 -> current.first * current.first
                1 -> current.first + current.first
                2 -> 0
                else -> 1
            }

            if (check.contains(nextNumber)) continue

            check.add(nextNumber)
            queue.add(Pair(nextNumber, "${current.second}${getOp(i)}"))
        }
    }


    return "-1"
}

private fun getOp(index: Int): String {
    return when (index) {
        0 -> "*"
        1 -> "+"
        2 -> "-"
        else -> "/"
    }
}