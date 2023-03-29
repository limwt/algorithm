package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

private var answer = Int.MIN_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val paper = Array(N) { IntArray(M) { 0 } }

    repeat(N) { idx ->
        paper[idx] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    // --- 모양의 블럭을 제외하면 dfs 를 이용해서 상하좌우를 통해서 탐색이 가능하다.
    //  |
    val visit = Array(N) { BooleanArray(M) { false } }

    for (i in 0 until N) {
        for (j in 0 until M) {
            visit[i][j] = true
            dfs(N, M, i, j, paper, visit, paper[i][j], 1)
            visit[i][j] = false
            // dfs 로 탐색되지 않는 블럭의 최대 값 확인
            //ㅜ 모양
            if (i + 1 < N && j + 2 < M) shape1(i, j, paper)
            //ㅏ 모양
            if (i + 2 < N && j + 1 < M) shape2(i, j, paper)
            //ㅗ 모양
            if (i - 1 >= 0 && j + 2 < M) shape3(i, j, paper)
            //ㅓ 모양
            if (i - 1 >= 0 && i + 1 < N && j + 1 < M) shape4(i, j, paper)
        }
    }

    println(answer)
}

private fun shape1(x: Int, y: Int, paper: Array<IntArray>) {
    answer = Math.max(answer, paper[x][y] + paper[x][y + 1] + paper[x][y + 2] + paper[x + 1][y + 1])
}

private fun shape2(x: Int, y: Int, paper: Array<IntArray>) {
    answer = Math.max(answer, paper[x][y] + paper[x + 1][y] + paper[x + 2][y] + paper[x + 1][y + 1])
}

private fun shape3(x: Int, y: Int, paper: Array<IntArray>) {
    answer = Math.max(answer, paper[x][y] + paper[x][y + 1] + paper[x][y + 2] + paper[x - 1][y + 1])
}

private fun shape4(x: Int, y: Int, paper: Array<IntArray>) {
    answer = Math.max(answer, paper[x][y] + paper[x][y + 1] + paper[x - 1][y + 1] + paper[x + 1][y + 1])
}

private fun dfs(n: Int, m: Int, x: Int, y: Int, paper: Array<IntArray>, visit: Array<BooleanArray>, sum: Int, count: Int) {
    // 탈출 조건 : count 가 4가 되면 테트로미노 블럭이 된 상태이다.
    if (count == 4) {
        answer = Math.max(answer, sum)
        return
    }

    // 상하좌우 탐색을 위한 배열
    val changeX = intArrayOf(-1, 1, 0, 0)
    val changeY = intArrayOf(0, 0, -1, 1)
    // 시작되는 위치에서 부터 상하좌우 탐색
    for (i in 0..3) {
        val newX = x + changeX[i]
        val newY = y + changeY[i]

        if (newX < 0 || newX >= n) continue
        if (newY < 0 || newY >= m) continue
        if (visit[newX][newY]) continue

        visit[newX][newY] = true
        dfs(n, m, newX, newY, paper, visit, sum + paper[newX][newY], count + 1)
        visit[newX][newY] = false
    }
}

