package com.lwt.algorithm.bruteforce

import java.io.BufferedReader
import java.io.InputStreamReader

private var result = Int.MIN_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (R, C) = br.readLine().split(" ").map { it.toInt() }
    val board = Array(R) { CharArray(C) { '0' } }
    repeat(R) {
        board[it] = br.readLine().toCharArray()
    }

    val visit = BooleanArray('Z' - 'A' + 1) { false }
    visit[board[0][0] - 'A'] = true
    dfs(board, visit, 0, 0, 1, R, C)
    println(result)
}

private fun dfs(board: Array<CharArray>, visit: BooleanArray, x: Int, y: Int, count: Int, R: Int, C: Int) {
    val dX = intArrayOf(-1, 1, 0, 0)
    val dY = intArrayOf(0, 0, -1, 1)
    result = Math.max(result, count)

    for (i in 0..3) {
        val nX = x + dX[i]
        val nY = y + dY[i]

        if ((nX < 0 || nX >= R) || (nY < 0 || nY >= C)) continue
        if (visit[board[nX][nY] - 'A']) continue

        visit[board[nX][nY] - 'A'] = true
        dfs(board, visit, nX, nY, count + 1, R, C)
        visit[board[nX][nY] - 'A'] = false
    }
}