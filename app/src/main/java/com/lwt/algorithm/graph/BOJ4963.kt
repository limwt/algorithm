package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val result = StringBuilder()

    while(true) {
        val (w, h) = br.readLine().split(" ").map { it.toInt() }

        if (w == 0 && h == 0) break

        //println("w : $w, h : $h")

        val map = Array(h) {
            IntArray(w) {
                0
            }
        }

        repeat(h) { index ->
            map[index] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        val visit = Array(h) {
            BooleanArray(w) {
                false
            }
        }

        var count = 0

        for (i in 0 until h) {
            for (j in 0 until w) {
                if (!visit[i][j] && map[i][j] == 1) {
                    count++
                    bfs(w, h, map, visit, i, j)
                }
            }
        }

        result.append("$count\n")
    }

    println(result)
}

private fun bfs(w: Int, h: Int, map: Array<IntArray>, visit: Array<BooleanArray>, x: Int, y: Int) {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(x, y))
    visit[x][y] = true
    //println("x : $x, y : $y")

    while (queue.isNotEmpty()) {
        val changeX = intArrayOf(-1, -1, -1, 0, 1, 1,  1,  0)
        val changeY = intArrayOf(-1,  0,  1, 1, 1, 0, -1, -1)
        val current = queue.pop()
        val currentX = current.first
        val currentY = current.second

        for (i in 0..7) {
            val newX = currentX + changeX[i]
            val newY = currentY + changeY[i]

            if ((newX < 0 || newX >= h) || (newY < 0 || newY >= w)) continue
            if (visit[newX][newY]) continue
            if (map[newX][newY] == 0) continue

            queue.add(Pair(newX, newY))
            visit[newX][newY] = true
        }
    }
}