package com.lwt.algorithm.greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, K) = br.readLine().split(" ").map { it.toInt() }

    // 문자열을 A 로 채운다.
    val S = CharArray(N) { 'A' }
    var matched = false

    // 문자열을 뒤부터 탐색한다.
    for (i in N - 1 downTo 0 step 1) {
        var sum = 0
        // i번째 문자를 'B'로 바꾼다.
        S[i] = 'B'
        // 조건을 만족하는 개수를 찾는다.
        for (j in 0 until N - 1) {
            var count = 0

            if (S[j] == 'B') continue

            for (k in j + 1 until N) {
                if (S[k] == 'B') count++
            }

            sum += count
        }

        //println("S = ${String(S)} sum = $sum")

        if (sum > K) {
            S[i] = 'A'
        }

        if (sum == K) {
            matched = true
            break
        }
    }

    if (matched) println(String(S))
    else println(-1)
}