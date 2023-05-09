package com.lwt.algorithm.greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val coin = Array(N) { BooleanArray(N) { false } }

    repeat(N) { index1 ->
        br.readLine().map { it }.forEachIndexed { index2, c ->
            // 뒷면이면 true, 앞면이면 false 로 저장한다.
            coin[index1][index2] = c == 'T'
        }
    }

    var result = Int.MAX_VALUE

    //기준을 행으로 잡고 행을 뒤집는 모든 경우의 수(2^N) 를 구한다.
    //이때 bitmask 를 이용해서 모든 경우의 수(부분집합)를 구하면 시간이 단축된다.
    for (i in 0 until (1 shl N)) {
        var sum = 0

        for (col in 0 until N) {
            var back = 0
            for (j in 0 until N) {
                var current = coin[j][col]

                if (i and (1 shl j) != 0) {
                    current = !coin[j][col]
                }

                if (current) {
                    back++
                }
            }

            sum += Math.min(back, N - back)
        }

        result = Math.min(result, sum)
    }

    println(result)
}