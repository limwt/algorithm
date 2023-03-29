package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var result = 0

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val maze = Array(N + 1) {
        IntArray(M + 1) {
            0
        }
    }

    repeat(N) { index ->
        br.readLine().forEachIndexed { idx, c ->
            maze[index + 1][idx + 1] = c.digitToInt()
        }
    }

    val visit = Array(N + 1) {
        BooleanArray(M + 1) {
            false
        }
    }

    println("${bfs(N, M, maze, visit)}")
    //dfs(N, M, 1, 1, maze, visit, 1)
    //println(result)
}

private fun dfs(N: Int, M: Int, x: Int, y: Int, maze: Array<IntArray>, visit: Array<BooleanArray>, depth: Int) {
    if (x == N && y == M) {
        result = depth
        return
    }

    //println("depth : $depth ($x, $y) ")

    val changeX = intArrayOf(-1, 1, 0, 0)
    val changeY = intArrayOf(0, 0, -1, 1)

    for (i in 0..3) {
        val newX = x + changeX[i]
        val newY = y + changeY[i]

        if ((newX < 1 || newX > N) || (newY < 1 || newY > M)) continue
        if (visit[newX][newY]) continue
        if (maze[newX][newY] == 0) continue

        visit[newX][newY] = true
        dfs(N, M, newX, newY, maze, visit, depth + 1)
    }
}




private fun bfs(N: Int, M: Int, maze: Array<IntArray>, visit: Array<BooleanArray>): Int {
    val queue = LinkedList<Maze>()
    queue.add(Maze(1, 1, 1))
    visit[1][1] = true
    var result = 0

    while (queue.isNotEmpty()) {
        val current = queue.pop()
        val currentX = current.x
        val currentY = current.y
        val count = current.count

        //println("current : $current")

        // 미로가 (N, M) 까지 도착 함
        if (currentX == N && currentY == M) {
            result = count
            break
        }

        val changeX = intArrayOf(-1, 1, 0, 0)
        val changeY = intArrayOf(0, 0, -1, 1)

        for (i in 0..3) {
            val newX = currentX + changeX[i]
            val newY = currentY + changeY[i]

            if ((newX < 1 || newX > N) || (newY < 1 || newY > M)) continue
            if (visit[newX][newY]) continue
            if (maze[newX][newY] == 0) continue

            visit[newX][newY] = true
            queue.add(Maze(newX, newY, count + 1))
        }
    }

    return result
}

private data class Maze(val x: Int, val y: Int, val count: Int)