package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader

private var result = "No"

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val game = Array(N) {
        CharArray(M) {
            'A'
        }
    }

    repeat(N) {
        game[it] = br.readLine().toCharArray()
    }

    var startChar = '0'
    var findCycle = false

    for (i in 0 until N) {
        for (j in 0 until M) {
            val visit = Array(N) {
                BooleanArray(M) {
                    false
                }
            }

            startChar = game[i][j]
            println("startChar : $startChar")
            visit[i][j] = true
            dfs(N, M, game, visit, startChar, i, j, i, j, 1)

            if (result == "Yes") {
                findCycle = true
                break
            }
        }

        if (findCycle) break
    }

    println(result)
}

private fun dfs(N: Int, M: Int, game: Array<CharArray>, visit: Array<BooleanArray>, ch: Char, x: Int, y: Int, startX: Int, startY: Int, depth: Int) {
    // 상, 하, 좌, 우 탐색
    val changeX = intArrayOf(-1, 1, 0, 0)
    val changeY = intArrayOf(0, 0, -1, 1)


    for (i in 0..3) {
        val newX = x + changeX[i]
        val newY = y + changeY[i]
        println("startX: $startX, startY: $startY, x: $x, y: $y, newX: $newX, newY: $newY, depth: $depth")

        if (newX == startX && newY == startY && depth >= 4) {
            result = "Yes"
            break
        }

        //범위 밖 영역
        if ((newX < 0 || newX >= N) || (newY < 0 || newY >= M)) continue
        //입력으로 들어온 ch 와 다른 색
        if (ch != game[newX][newY]) continue
        //이미 방문한 곳
        if (visit[newX][newY]) continue

        visit[newX][newY] = true
        dfs(N, M, game, visit, ch, newX, newY, startX, startY, depth + 1)
    }
}