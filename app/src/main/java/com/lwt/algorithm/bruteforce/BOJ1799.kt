package com.lwt.algorithm.bruteforce

import java.io.BufferedReader
import java.io.InputStreamReader

private var result = Int.MIN_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val chess = Array(N) { IntArray(N) { 0 } }

    repeat(N) { index ->
        chess[index] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val visit = Array(N) { BooleanArray(N) { false } }
    val pVisit = BooleanArray(20) { false }
    val nVisit = BooleanArray(20) { false }
    dfs(N, chess, visit, pVisit, nVisit, 0 )
    println(result)
}

private fun dfs(N: Int, chess: Array<IntArray>, visit: Array<BooleanArray>, pVisit: BooleanArray, nVisit: BooleanArray, count: Int) {
    for (i in 0 until N) {
        for (j in 0 until N) {
            // 색칠된 부분으로 비숲을 놓을 수 없다.
            if (chess[i][j] == 0) continue

            // 이미 방문한 지점.
            if (visit[i][j]) continue

            if (check(N, i, j, pVisit, nVisit)) {
                visit[i][j] = true
                pVisit[i + j] = true
                nVisit[N + i - j] = true
                chess[i][j] = count + 1
                result = Math.max(result, chess[i][j])
                println("chess >> chess[$i][$j] = ${chess[i][j]}")
                dfs(N, chess, visit, pVisit, nVisit, chess[i][j])
                visit[i][j] = false
                pVisit[i + j] = false
                nVisit[N + i - j] = false
                chess[i][j] = count
            }
        }
    }
}

private fun check(N: Int, row: Int, col: Int, pVisit: BooleanArray, nVisit: BooleanArray): Boolean {
    if (pVisit[row + col]) return false
    if (nVisit[N + row - col]) return false

    return true
}