package com.lwt.algorithm.tree

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val list = Array(N + 1) { ArrayList<Int>() }

    repeat(N - 1) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        list[a].add(b)
        list[b].add(a)
    }

    bfs(N, list)
}

private fun bfs(N: Int, list: Array<ArrayList<Int>>) {
    val start = 1
    val visit = BooleanArray(N + 1) { false }
    visit[start] = true
    val queue = LinkedList<Int>()
    queue.add(start)
    val result = IntArray(N + 1) { 0 }

    while (queue.isNotEmpty()) {
        val current = queue.pop()
        //println("current : $current, $queue")

        for (next in list[current]) {
            //println("next : $next")

            if (visit[next]) continue

            visit[next] = true
            result[next] = current
            queue.add(next)
        }
    }

    for (i in 2..N) {
        println("${result[i]}")
    }
}
