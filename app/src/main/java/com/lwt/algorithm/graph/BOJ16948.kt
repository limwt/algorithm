package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val (r1, c1, r2, c2) = br.readLine().split(" ").map { it.toInt() }
    println("${bfs(N, Position(r1, c1), Position(r2, c2))}")
}

private fun bfs(N: Int, start: Position, end: Position): Int {
    val queue = LinkedList<DeathNight>()
    queue.add(DeathNight(start, 0))
    val visit = Array(N) { BooleanArray(N) { false } }
    visit[start.r][start.c] = true

    while (queue.isNotEmpty()) {
        val (cPos, cCount) = queue.pop()
        //println("current $cPos, $cCount")

        if (cPos.r == end.r && cPos.c == end.c) {
            return cCount
        }

        //  (r, c)라면, (r-2, c-1), (r-2, c+1), (r, c-2), (r, c+2), (r+2, c-1), (r+2, c+1) 로 이동 가능.
        val dR = intArrayOf(-2, -2,  0, 0,  2,  2)
        val dC = intArrayOf(-1,  1, -2, 2, -1,  1)

        for (i in 0..5) {
            val nR = cPos.r + dR[i]
            val nC = cPos.c + dC[i]

            // 범위 밖
            if ((nR < 0 || nR >= N) || (nC < 0 || nC >= N)) continue
            // 이미 탐색한 영역
            if (visit[nR][nC]) continue

            //println("next ($nR, $nC), $cCount")
            visit[nR][nC] = true
            queue.add(DeathNight(Position(nR, nC), cCount + 1))
        }
    }

    return -1
}

private data class Position(val r: Int, val c: Int)

private data class DeathNight(val pos: Position, val count: Int)