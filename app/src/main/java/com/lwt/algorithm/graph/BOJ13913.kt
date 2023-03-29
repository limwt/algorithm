package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, K) = br.readLine().split(" ").map { it.toInt() }

    bfs(N, K)
}

private fun bfs(N: Int, K: Int) {
    val queue = LinkedList<Pair<Int, Int>>()
    val visit = IntArray(100001) { -1 }
    queue.add(Pair(N, 0))

    while (queue.isNotEmpty()) {
        val (curPosition, curTime) = queue.pop()

        if (curPosition == K) {
            println(curTime)

            val result = mutableListOf<Int>()
            var nextK = K
            result.add(nextK)

            if (nextK != N) {
                while (true) {
                    //println("$nextK, ${visit[nextK]}")
                    result.add(visit[nextK])
                    nextK = visit[nextK]

                    if (nextK == N) break
                }
            }

            val sb = StringBuilder()

            for (i in result.size - 1 downTo 0 step 1) {
                sb.append("${result[i]} ")
            }
            println(sb.toString())

            return
        }

        for (i in 0..2) {
            val nextPosition = when (i) {
                0 -> curPosition - 1
                1 -> curPosition + 1
                else -> curPosition * 2
            }

            if (nextPosition < 0 || nextPosition > 100000) continue
            if (visit[nextPosition] != -1) continue

            //println("curPosition : $curPosition, nextPosition : $nextPosition")
            visit[nextPosition] = curPosition
            queue.add(Pair(nextPosition, curTime + 1))
        }
    }
}