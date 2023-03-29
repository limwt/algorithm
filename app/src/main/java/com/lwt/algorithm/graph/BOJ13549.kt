package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val distance = IntArray(100001) { Int.MAX_VALUE }

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, K) = br.readLine().split(" ").map { it.toInt() }
    //bfs(N, K)
    dijkstra(N, K)
    println(distance[K])
}

private fun dijkstra(start: Int, target: Int) {
    val queue = PriorityQueue<Play>()
    val visit = BooleanArray(100001) { false }
    queue.add(Play(start, 0))
    distance[start] = 0

    while (queue.isNotEmpty()) {
        val (curPosition, curTime) = queue.poll()!!

        if (visit[curPosition]) continue

        visit[curPosition] = true

        if (curPosition == target) {
            break
        }

        for (i in 0..2) {
            val nextPosition = when (i) {
                0 -> curPosition - 1
                1 -> curPosition + 1
                else -> curPosition * 2
            }

            val nextTime = when (i) {
                0, 1 -> curTime + 1
                else -> curTime
            }

            if (nextPosition < 0 || nextPosition > 100000) continue

            //println("curPosition : $curPosition, curTime : $curTime, $nextPosition, $nextTime, ${distance[nextPosition]}")

            if (nextTime < distance[nextPosition]) {
                distance[nextPosition] = nextTime
            }

            queue.add(Play(nextPosition, distance[nextPosition]))
        }
    }
}

private data class Play(val position: Int, val time: Int) : Comparable<Play> {
    override fun compareTo(other: Play): Int {
        return time - other.time
    }
}