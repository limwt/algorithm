package com.lwt.algorithm.greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, K) = br.readLine().split(" ").map { it.toInt() }
    val A = IntArray(N + 1) { 0 }

    repeat(N) { index ->
        A[index + 1] = br.readLine().toInt()
    }

    var result = 0
    var weight = 0
    var target = K


    for (i in N downTo 1 step 1) {
        // 동전의 가치가 결과 값보다 크다
        if (A[i] > K) continue
        else if (A[i] == K) {
            result += 1
            break
        }
        else {
            val mok = target / A[i]
            target %= A[i]
            result += mok
        }
    }

    println(result)
}