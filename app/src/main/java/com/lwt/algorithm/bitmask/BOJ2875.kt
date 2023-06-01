package com.lwt.algorithm.bitmask

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M, K) = br.readLine().split(" ").map { it.toInt() }

    var team = 0
    var women = N
    var remain = 0

    for (i in 0 until M) {
        if (women - 2 >= 0) {
            women -= 2
            team++
        } else {
            remain = M - i + 1
        }
    }

    println("$team $remain")

    if (remain >= K) {
        println(team)
    } else {
        val k = K - remain
        println("${team - (k / 3) - 1}")
    }
}