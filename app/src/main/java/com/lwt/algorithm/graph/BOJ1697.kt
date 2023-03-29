package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, K) = br.readLine().split(" ").map { it.toInt() }
    println("${bfs(N, K)}")
}

private fun bfs(start: Int, K: Int): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(start, 0))
    val visit = BooleanArray(100001) { false }
    visit[start] = true
    var result = 0

    while (queue.isNotEmpty()) {
        val (curPosition, curTime) = queue.pop()
        //println("curPosition : $curPosition, curTime : $curTime")

        if (curPosition == K) {
            result = curTime
            break
        }

        for (i in 0..2) {
            val next = when (i) {
                0 -> curPosition - 1
                1 -> curPosition + 1
                else -> curPosition * 2
            }

            if (next < 0 || next > 100000) continue
            if (visit[next]) continue

            visit[next] = true
            queue.add(Pair(next, curTime + 1))
        }

    }

    return result
}