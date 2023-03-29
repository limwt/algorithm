package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val T = br.readLine().toInt()

    repeat(T) {
        val n = br.readLine().toInt()
        val dp = IntArray(12) { 0 }
        dp[1] = 1

        for (i in 2..n) {
            dp[i] = dp[i - 1] + 2 * (i - 2)
        }

        println(dp[n])
    }
}

