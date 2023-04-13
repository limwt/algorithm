package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val number = br.readLine().toInt()

    // 입력으로 들어온 수까지 모든 수를 소수라고 초기화 한다.
    val prime = BooleanArray(number + 1) { true }

    // 입력된 수의 제곱근을 구한다.
    val sqrt = Math.sqrt(number.toDouble()).toInt()

    println("sqrt : $sqrt")

    // 2부터 제곱근 까지 탐색한다.
    for (i in 2..sqrt) {
        // 소수가 아니므로 다음 탐색을 한다.
        if (!prime[i]) continue

        var j = 2
        while (i * j <= number) {
            if (prime[i * j]) prime[i * j] = false
            j++
        }
    }

    println("===== Result =====")
    for (i in 2..number) {
        if (prime[i]) print("$i ")
    }
    println()
}