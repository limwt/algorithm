package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M, K) = br.readLine().split(" ").map { it.toInt() }
    val map = Array(N + 1) { IntArray(M + 1) { 0 } }

    repeat(N) { index1 ->
        br.readLine().forEachIndexed { index2, c ->
            map[index1 + 1][index2 + 1] = c - '0'
        }
    }

    println("${bfs(N, M, K, map)}")
}

private fun bfs(N: Int, M: Int, K: Int, map: Array<IntArray>): Int {
    val queue = LinkedList<Map16933>()
    // day -> 0 이 낮이고 1 을 밤으로 표현 한다.
    queue.add(Map16933(1, 1, 0, 0, 1))
    val broken = Array(N + 1) { IntArray(M + 1) { Int.MAX_VALUE } }
    broken[1][1] = 0

    while (queue.isNotEmpty()) {
        val (curX, curY, curNight, curBrokenCnt, curDistance) = queue.pop()

        if (curX == N && curY == M) {
            return curDistance
        }

        val dX = intArrayOf(-1, 1, 0, 0)
        val dY = intArrayOf(0, 0, -1, 1)

        for (i in 0..3) {
            val newX = curX + dX[i]
            val newY = curY + dY[i]

            //범위 밖.
            if ((newX < 1 || newX > N) || (newY < 1 || newY > M)) continue
            if (broken[newX][newY] <= curBrokenCnt) continue
            //이미 방문한 영역.
            //if (visit[curBrokenCnt][newX][newY][curNight]) continue

            if (map[newX][newY] == 1) {
                if (curBrokenCnt >= K) continue

                if (curNight == 0) {
                    broken[newX][newY] = curBrokenCnt + 1
                    queue.add(Map16933(newX, newY, 1, curBrokenCnt + 1, curDistance + 1))
                } else {
                    queue.add(Map16933(curX, curY, 0, curBrokenCnt, curDistance + 1))
                }
            } else {
                broken[newX][newY] = curBrokenCnt
                queue.add(Map16933(newX, newY, 1 - curNight, curBrokenCnt, curDistance + 1))
            }
        }
    }

    return -1
}

private data class Map16933(val x: Int, val y: Int, val night: Int, val brokenCnt: Int, val distance: Int)