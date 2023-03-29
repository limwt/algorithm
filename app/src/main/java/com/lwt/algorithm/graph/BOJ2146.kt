package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var result = Int.MAX_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val map = Array(N) { IntArray(N) { 0 } }
    repeat(N) { index ->
        map[index] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val visit = Array(N) { BooleanArray(N) { false } }
    var landNumber = 0

    // 각 섬을 구분하기 위해서 서로 다른 번호 부여하기
    for (i in 0 until N) {
        for (j in 0 until N) {
            if (!visit[i][j] && map[i][j] != 0) {
                makeLand(N, map, visit, i, j, ++landNumber)
            }
        }
    }

    /*println("Make Land >>>>")
    map.forEach { _map ->
        _map.forEach { m ->
            print("$m ")
        }
        println()
    }
    println()*/

    //각 섬을 기준으로 BFS 를 통해서 다리길이를 구한다.
    for (i in 0 until N) {
        for (j in 0 until N) {
            if (map[i][j] != 0) {
                val _visit = Array(N) { BooleanArray(N) { false } }
                getBridge(N, map, _visit, i, j)
            }
        }
    }

    /*println("Get Bridge >>>>")
    map.forEach { _map ->
        _map.forEach { m ->
            print("$m ")
        }
        println()
    }
    println()*/

    println(result)
}

private fun getBridge(N: Int, map: Array<IntArray>, visit: Array<BooleanArray>, x: Int, y: Int) {
    val queue = LinkedList<Triple<Int, Int, Int>>()
    queue.add(Triple(x, y, 0))
    visit[x][y] = true
    val currentLand = map[x][y]

    while (queue.isNotEmpty()) {
        val (curX, curY, curCnt) = queue.pop()
        val changeX = intArrayOf(-1, 1, 0, 0)
        val changeY = intArrayOf(0, 0, -1, 1)

        for (i in 0..3) {
            val newX = curX + changeX[i]
            val newY = curY + changeY[i]

            // 범위 밖...
            if ((newX < 0 || newX >= N) || (newY < 0 || newY >= N)) continue
            // 이미 방문한 섬...
            if (visit[newX][newY]) continue
            // 같은 섬의 영역...
            if (map[newX][newY] == currentLand) continue

            visit[newX][newY] = true

            if (map[newX][newY] == 0) {
                //println("newX : $newX, newY : $newY, curCnt : $curCnt")
                queue.add(Triple(newX, newY, curCnt + 1))
            } else {
                // 섬과 만났으므로 다리길이 업데이트 하기.
                //println("result : $result, curCnt: $curCnt")
                result = Math.min(result, curCnt)
            }
        }
    }
}

private fun makeLand(N: Int, map: Array<IntArray>, visit: Array<BooleanArray>, x: Int, y: Int, landNumber: Int) {
    val queue = LinkedList<Pair<Int, Int>>()
    visit[x][y] = true
    queue.add(Pair(x, y))
    map[x][y] = landNumber

    while (queue.isNotEmpty()) {
        val (curX, curY) = queue.pop()
        val changeX = intArrayOf(-1, 1, 0, 0)
        val changeY = intArrayOf(0, 0, -1, 1)

        for (i in 0..3) {
            val newX = curX + changeX[i]
            val newY = curY + changeY[i]

            // 범위 밖...
            if ((newX < 0 || newX >= N) || (newY < 0 || newY >= N)) continue
            // 바다 영역...
            if (map[newX][newY] == 0) continue
            // 이미 방문한 섬...
            if (visit[newX][newY]) continue

            visit[newX][newY] = true
            map[newX][newY] = landNumber
            queue.add(Pair(newX, newY))
        }
    }
}