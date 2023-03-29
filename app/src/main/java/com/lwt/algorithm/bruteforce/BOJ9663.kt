package com.lwt.algorithm.bruteforce

import java.io.*

private var result = 0

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()

    val visit = BooleanArray(N) { false } // 같은 열에 퀸이 있는 지 확인하는 배열
    val rightVisit = BooleanArray(N * 2) { false } // 놓여 있는 퀸의 오른쪽 대각선 위에 놓을 수 있는 지 확인하는 배열
    val leftVisit = BooleanArray(N * 2) { false } // 놓여 있는 퀸의 왼쪽 대각선 위에 놓을 수 있는 지 확인하는 배열

    dfs(N, 0, visit, rightVisit, leftVisit)
    println(result)
}

private fun dfs(N: Int, row: Int, visit: BooleanArray, rVisit: BooleanArray, lVisit: BooleanArray) {
    if (row == N) {
        result++
        return
    }

    for (i in 0 until N) {
        if (check(N, row, i, visit, rVisit, lVisit)) {
            visit[i] = true
            rVisit[row + i] = true
            lVisit[N + row - i] = true
            dfs(N, row + 1, visit, rVisit, lVisit)
            visit[i] = false
            rVisit[row + i] = false
            lVisit[N + row - i] = false
        }
    }
}

private fun check(N: Int, row: Int, col: Int, visit: BooleanArray, rVisit: BooleanArray, lVisit: BooleanArray): Boolean {
    if (visit[col]) return false
    if (rVisit[row + col]) return false
    if (lVisit[N + row - col]) return false

    return true
}