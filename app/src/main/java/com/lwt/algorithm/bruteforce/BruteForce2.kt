package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    // 캔디 색깔 종류 : C, P, Z, Y
    val candy = Array(N) { CharArray(N) {
            'C' // C 로 배열 초기화
        }
    }

    repeat(N) {
        // 입력된 캔디 색깔 저장.
        candy[it] = br.readLine().toCharArray()
    }

    var answer = Int.MIN_VALUE

    // 인접된 서로 다른 색깔을 찾는다
    for (i in 0 until N) {
        for (j in 0 until N) {
            val di = intArrayOf(-1, 1, 0, 0) // 상하좌우
            val dj = intArrayOf(0, 0, -1, 1) // 상하좌우

            for (k in 0..3) {
                val newI = di[k] + i
                val newJ = dj[k] + j

                if (newI in 0 until N && newJ in 0 until N) {
                    var temp = candy[i][j]
                    candy[i][j] = candy[newI][newJ]
                    candy[newI][newJ] = temp

                    // 서로 교환한 사탕 중 왼쪽에 위치한 사탕의 행/열의 최대로 먹을 수 있는 캔디 개수를 구한다.
                    val result = search(candy, N, i, j)

                    if (answer < result) {
                        answer = result
                    }

                    temp = candy[i][j]
                    candy[i][j] = candy[newI][newJ]
                    candy[newI][newJ] = temp
                }
            }
        }
    }

    println(answer)
}

private fun search(candy: Array<CharArray>, n: Int, i: Int, j: Int): Int {
    var eatNum = 1
    var result1 = Int.MIN_VALUE
    var result2 = Int.MIN_VALUE

    // 행의 최대 값을 구한다.
    for (k in 1 until n) {
        if (candy[i][k - 1] == candy[i][k]) {
            eatNum++
            result1 = Math.max(result1, eatNum)
        } else {
            eatNum = 1
        }
    }

    eatNum = 1

    // 열의 최대 값을 구한다.
    for (k in 1 until n) {
        if (candy[k - 1][j] == candy[k][j]) {
            eatNum++
            result2 = Math.max(result2, eatNum)
        } else {
            eatNum = 1
        }
    }

    return Math.max(result1, result2)
}