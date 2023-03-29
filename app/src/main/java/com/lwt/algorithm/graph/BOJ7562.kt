package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val T = br.readLine().toInt()

    repeat(T) {
        val I = br.readLine().toInt()
        val chess = Array(I) {
            IntArray(I) {
                0
            }
        }

        val visit = Array(I) {
            BooleanArray(I) {
                false
            }
        }

        val (currentX, currentY) = br.readLine().split(" ").map { it.toInt() }
        val (targetX, targetY) = br.readLine().split(" ").map { it.toInt() }

        bfs(I, chess, visit, currentX, currentY, targetX, targetY)
        println("${chess[targetX][targetY]}")
    }
}

private fun bfs(I: Int, chess: Array<IntArray>, visit: Array<BooleanArray>, x: Int, y: Int, targetX: Int, targetY: Int) {
    val queue = LinkedList<Chess>()
    queue.add(Chess(x, y, 0))
    visit[x][y] = true

    while (queue.isNotEmpty()) {
        val current = queue.pop()
        val currentX = current.x
        val currentY = current.y
        val currentCount = current.count
        val changeX = intArrayOf(-2, -2, -1, 1, 2,  2,  1, -1)
        val changeY = intArrayOf(-1,  1,  2, 2, 1, -1, -2, -2)

        if (currentX == targetX && currentY == targetY) {
            break
        }

        for (i in 0..7) {
            val newX = currentX + changeX[i]
            val newY = currentY + changeY[i]

            // 범위 밖으로 이동된 경우
            if ((newX < 0 || newX >= I) || (newY < 0 || newY >= I)) continue
            // 이미 방문한 경로
            if (visit[newX][newY]) continue

            chess[newX][newY] = currentCount + 1
            queue.add(Chess(newX, newY, chess[newX][newY]))
            visit[newX][newY] = true
        }
    }
}

private data class Chess(val x: Int, val y: Int, val count: Int)