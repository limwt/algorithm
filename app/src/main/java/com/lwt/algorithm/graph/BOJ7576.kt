package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var result = 0

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (M, N) = br.readLine().split(" ").map { it.toInt() }
    val tomato = Array(N) {
        IntArray(M) {
            0
        }
    }

    repeat(N) { n ->
        tomato[n] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val ripenList = LinkedList<LinkedList<Pair<Int, Int>>>()
    val queue = LinkedList<Pair<Int, Int>>()

    for (i in 0 until N) {
        for (j in 0 until M) {
            if (tomato[i][j] == 1) {
                queue.add(Pair(i, j))
            }
        }
    }

    ripenList.add(queue)
    bfs(N, M, tomato, ripenList)

    var notCompleted = false

    for (i in 0 until N) {
        for (j in 0 until M) {
            if (tomato[i][j] == 0) {
                notCompleted = true
                break
            }
        }

        if (notCompleted) break
    }

    if (notCompleted) {
        println(-1)
    } else {
        println(result - 1)
    }
}

private fun bfs(N: Int, M: Int, tomato: Array<IntArray>, ripenList: LinkedList<LinkedList<Pair<Int, Int>>>) {
    while (ripenList.isNotEmpty()) {
        val ripenQueue = ripenList.pop()
        result++
        val newRipenQueue = LinkedList<Pair<Int, Int>>()

        while (ripenQueue.isNotEmpty()) {
            //println("ripenQueue $ripenQueue")
            val current = ripenQueue.pop()
            val currentX = current.first
            val currentY = current.second
            val changeX = intArrayOf(-1, 1, 0, 0)
            val changeY = intArrayOf(0, 0, -1, 1)

            for (i in 0..3) {
                val newX = currentX + changeX[i]
                val newY = currentY + changeY[i]

                // 범위를 벗어 난 경우
                if ((newX < 0 || newX >= N) || (newY < 0 || newY >= M)) continue
                // 토마토가 없는 칸
                if (tomato[newX][newY] == -1) continue
                // 이미 익은 토마토
                if (tomato[newX][newY] == 1) continue

                tomato[newX][newY] = 1
                newRipenQueue.add(Pair(newX, newY))
            }
        }

        if (newRipenQueue.isNotEmpty()) {
            ripenList.add(newRipenQueue)
        }
    }
}