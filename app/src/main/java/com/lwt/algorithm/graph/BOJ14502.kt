package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var result = Int.MIN_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val map = Array(N) { IntArray(M) { 0 } }
    repeat(N) { idx ->
        map[idx] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    // 바이러스 위치를 저장한다.
    val virus = Array(N) { BooleanArray(M) { false } }

    for (i in 0 until N) {
        for (j in 0 until M) {
            if (map[i][j] == 2) {
                virus[i][j] = true
            }
        }
    }

    setWall(N, M, map, 0, virus)
    println(result)
}

private fun setWall(N: Int, M: Int, map: Array<IntArray>, depth: Int, virus: Array<BooleanArray>) {
    if (depth == 3) {
        // 안전한 지역 찾는 findSafeZone 수행
        val newMap = Array(N) { IntArray(M) { 0 } }

        map.forEachIndexed { i, _map ->
            _map.forEachIndexed { j, m ->
                newMap[i][j] = m
            }
        }


        for (i in 0 until N) {
            for (j in 0 until M) {
                // 첫 바이러스가 있는 위치를 찾는다.
                if (virus[i][j]) {
                    findSafeZone(N, M, newMap, i, j)
                }
            }
        }

        result = Math.max(result, getSafeZone(N, M, newMap))
        return
    }

    for (i in 0 until N) {
        for (j in 0 until M) {
            if (map[i][j] == 0) {
                map[i][j] = 1
                setWall(N, M, map, depth + 1, virus)
                map[i][j] = 0
            }
        }
    }

}

private fun findSafeZone(N: Int, M: Int, map: Array<IntArray>, x: Int, y: Int) {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(x, y))

    while (queue.isNotEmpty()) {
        val (cX, cY) = queue.pop()
        val dX = intArrayOf(-1, 1,  0, 0)
        val dY = intArrayOf( 0, 0, -1, 1)

        for (i in 0..3) {
            val nX = cX + dX[i]
            val nY = cY + dY[i]

            if ((nX < 0 || nX >= N) || (nY < 0 || nY >= M)) continue
            if (map[nX][nY] == 1) continue

            if (map[nX][nY] == 2) continue

            map[nX][nY] = 2
            queue.add(Pair(nX, nY))
        }
    }
}

private fun getSafeZone(N: Int, M: Int, map: Array<IntArray>): Int {
    var count = 0

    for (i in 0 until N) {
        for (j in 0 until M) {
            if (map[i][j] == 0) count++
        }
    }

    return count
}