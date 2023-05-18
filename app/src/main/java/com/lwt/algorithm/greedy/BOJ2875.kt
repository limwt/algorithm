package com.lwt.algorithm.greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M, K) = br.readLine().split(" ").map { it.toInt() }

    var women = N
    var men = M
    var count = 0

    while (women >= 2 && men >= 1 && women + men >= 3 + K) {
        women -= 2
        men -= 1
        count++
    }

    println(count)
}