package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

private var result = Int.MIN_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val paper = Array(N) {
        IntArray(M) {
            0
        }
    }

    repeat(N) { index ->
        paper[index] = br.readLine().map { it.digitToInt() }.toIntArray()
    }

    val visit = Array(N) {
        BooleanArray(M) {
            false
        }
    }
    dfs(N, M, paper, visit, 0, 0)
    println(result)
}

private fun dfs(N: Int, M: Int, paper: Array<IntArray>, visit: Array<BooleanArray>, i: Int, j: Int) {
    println("i $i, j $j")

    /*visit.forEach { v ->
        v.forEach {  _v ->
            print("$_v ")
        }
        println("")
    }*/

    if (i == N) {
        // 가로 합 구하기...
        var total = 0

        for (k in 0 until N) {
            var sum = 0
            for (l in 0 until M) {
                if (visit[k][l]) {
                    sum = sum * 10 + paper[k][l]
                    //println("sum = $sum")
                } else {
                    total += sum
                    sum = 0
                }
            }
            total += sum
        }

        // 세로 합 구하기...
        for (k in 0 until M) {
            var sum = 0
            for (l in 0 until N) {
                if (!visit[l][k]) {
                    sum = sum * 10 + paper[l][k]
                    //println("sum222 = $sum")
                } else {
                    total += sum
                    sum = 0
                }
            }

            total += sum
        }

        result = Math.max(result, total)
        return
    }

    if (j == M) {
        // 다음 행으로 이동...
        dfs(N, M, paper, visit, i + 1, 0)
        return
    }

    if (!visit[i][j]) {
        visit[i][j] = true
        // 가로 탐색...
        dfs(N, M, paper, visit, i, j + 1)
        visit[i][j] = false
        // 세로 탐색...
        dfs(N, M, paper, visit, i, j + 1)
    }
}