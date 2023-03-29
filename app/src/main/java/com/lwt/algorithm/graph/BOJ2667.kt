package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val complex = Array(N) {
        IntArray(N) {
            0
        }
    }

    repeat(N) {
        br.readLine().forEachIndexed { index, c ->
            complex[it][index] = c.digitToInt()
        }
    }

    val visit = Array(N) {
        BooleanArray(N) {
            false
        }
    }

    val result = mutableListOf<Int>()

    for (i in 0 until N) {
        for (j in 0 until N) {
            if (!visit[i][j] && complex[i][j] != 0) {
                result.add(bfs(N, complex, visit, i, j))
            }
        }
    }

    result.sort()
    println(result.size)

    result.forEach { r ->
        println(r)
    }
}

private fun bfs(N: Int, complex: Array<IntArray>, visit: Array<BooleanArray>, x: Int, y: Int): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(x, y))
    visit[x][y] = true
    var count = 1

    //println("queue : $queue")

    while (queue.isNotEmpty()) {
        // 현재 위치(x, y) 를 상하좌우 방문하여 집이 있는 지 확인 한다.
        val current = queue.pop()
        val currentX = current.first
        val currentY = current.second
        val changeX = intArrayOf(-1, 1, 0, 0)
        val changeY = intArrayOf(0, 0, -1, 1)

        for (i in 0..3) {
            val newX = currentX + changeX[i]
            val newY = currentY + changeY[i]
            //println("newX : $newX, newY : $newY")

            // 범위를 넘어가는 지 확인한다.
            if ((newX < 0 || newX >= N) || (newY < 0 || newY >= N)) continue

            // 이미 방문한 집인지 확인한다.
            if (visit[newX][newY]) continue

            // 집이 있는 지 확인 한다.
            if (complex[newX][newY] == 0) continue

            //println("newX : $newX, newY : $newY, ${complex[newX][newY]}, ${visit[newX][newY]}")
            visit[newX][newY] = true
            queue.add(Pair(newX, newY))
            count++
        }
    }

    return count
}