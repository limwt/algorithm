package com.lwt.algorithm.greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val matrix = Array(N) { IntArray(M) { 0 } }
    val target = Array(N) { IntArray(M) { 0 } }
    repeat(N) { index ->
        matrix[index] = br.readLine().map { it.digitToInt() }.toIntArray()
    }

    repeat(N) { index ->
        target[index] = br.readLine().map { it.digitToInt() }.toIntArray()
    }

    var result = 0

    for (i in 0 until N) {
        if (i + 2 >= N) continue

        for (j in 0 until M) {
            if ((i + 2) < N && (j + 2) < M && matrix[i][j] != target[i][j]) {
                // matrix 와 target 이 서로 다르므로 부분 행렬 3X3의 원소들을 업데이트 한다. (0 -> 1, 1 -> 0)
                for (k in i..i + 2) {
                    for (l in j..j + 2) {
                        matrix[k][l] = Math.abs(matrix[k][l] - 1)
                    }
                }

                result++
            }
        }
    }

    var matched = true

    loop@ for (i in 0 until N) {
        for (j in 0 until M) {
            if (matrix[i][j] != target[i][j]) {
                matched = false
                break@loop
            }
        }
    }

    if (matched) println(result)
    else println(-1)
}