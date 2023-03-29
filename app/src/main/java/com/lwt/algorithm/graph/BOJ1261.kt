package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (M, N) = br.readLine().split(" ").map { it.toInt() }
    val maze = Array(N + 1) { IntArray(M + 1) { 0 } }

    repeat(N) { index1 ->
        val input = br.readLine().toCharArray()

        input.forEachIndexed { index2, c ->
            maze[index1 + 1][index2 + 1] = c.digitToInt()
        }
    }

    dijkstra(N, M, maze)
    println()
}

private fun dijkstra(N: Int, M: Int, maze: Array<IntArray>) {
    val queue = PriorityQueue<Wall>()
    queue.add(Wall(1, 1, 0))
    // 이미 방문한 영역인 지 확인하는 배열
    val visit = Array(N + 1) { BooleanArray(M + 1) { false } }
    visit[1][1] = true
    // 벽을 깬 횟수를 저장하는 배열
    val dp = Array(N + 1) { IntArray(M + 1) { Int.MAX_VALUE } }
    dp[1][1] = 0

    while (queue.isNotEmpty()) {
        val (curX, curY, curBroken) = queue.poll()!!
        val changeX = intArrayOf(1, 0, -1, 0)
        val changeY = intArrayOf(0, 1, 0, -1)
        //println("(curX : $curX, curY : $curY, curBroken : $curBroken) ${dp[curX][curY]}")

        if (curX == N && curY == M) {
            println(dp[curX][curY])
            break
        }

        for (i in 0..3) {
            val newX = curX + changeX[i]
            val newY = curY + changeY[i]

            // 범위 밖
            if ((newX < 1 || newX > N) || (newY < 1 || newY > M)) continue
            // 이미 방문한 영역
            if (visit[newX][newY]) continue

            //println("(newX : $newX, newY : $newY) ${dp[newX][newY]}")
            visit[newX][newY] = true

            if (dp[newX][newY] > curBroken + dp[curX][curY]) {
                if (maze[newX][newY] == 1) {
                    dp[newX][newY] = dp[curX][curY] + 1
                } else {
                    dp[newX][newY] = dp[curX][curY]
                }
            }

            queue.add(Wall(newX, newY, dp[newX][newY]))
        }
    }
}

// 벽을 깰지 말지에 따라 가중치가 다르므로 벽을 부순 횟수를 기준으로 정렬하여 우선순위 큐에 저장되도록 한다.
private data class Wall(val x: Int, val y: Int, val broken: Int) : Comparable<Wall> {
    override fun compareTo(other: Wall): Int {
        return broken - other.broken
    }
}