package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var result = Int.MAX_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M, K) = br.readLine().split(" ").map { it.toInt() }
    val map = Array(N + 1) { IntArray(M + 1) { 0 } }

    repeat(N) { index ->
        br.readLine().forEachIndexed { index2, c ->
            map[index + 1][index2 + 1] = c - '0'
        }
    }

    bfs(N, M, K, map)

    if (result == Int.MAX_VALUE) println(-1) else println(result)
}

private fun bfs(N: Int, M: Int, K: Int, map: Array<IntArray>) {
    val queue = LinkedList<Map>()
    queue.add(Map(1, 1, 0, 1))
    val visit = Array(K + 1) { Array(N + 1) { BooleanArray(M + 1) { false } } }
    visit[0][1][1] = true

    while (queue.isNotEmpty()) {
        val (curX, curY, curBrokenCnt, curDistance) = queue.pop()

        if (curX == N && curY == M) {
            result = Math.min(result, curDistance)
        }

        val dX = intArrayOf(-1, 1, 0, 0)
        val dY = intArrayOf(0, 0, -1, 1)

        for (i in 0..3) {
            val newX = curX + dX[i]
            val newY = curY + dY[i]

            //범위 밖.
            if ((newX < 1 || newX > N) || (newY < 1 || newY > M)) continue
            //이미 방문한 곳.
            if (visit[curBrokenCnt][newX][newY]) continue

            if (map[newX][newY] == 1) {
                if (curBrokenCnt < K) {
                    visit[curBrokenCnt][newX][newY] = true
                    queue.add(Map(newX, newY, curBrokenCnt + 1, curDistance + 1))
                }

                continue
            }

            visit[curBrokenCnt][newX][newY] = true
            queue.add(Map(newX, newY, curBrokenCnt, curDistance + 1))
        }
    }
}

private data class Map(val x: Int, val y: Int, val brokenCnt: Int, val distance: Int)

