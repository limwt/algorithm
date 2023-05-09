package com.lwt.algorithm.greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val P = mutableListOf<Int>()
    br.readLine().split(" ").map { it.toInt() }.forEach {
        P.add(it)
    }

    // 시간을 기준으로 오름차순 정렬
    P.sort()

    var result = 0

    for (i in 0 until N) {
        var sum = 0
        for (j in 0..i) {
            sum += P[j]
        }

        result += sum
    }

    println(result)
}