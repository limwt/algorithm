package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

private var result = 0

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val T = br.readLine().toInt()

    repeat(T) {
        val n = br.readLine().toInt()
        val arr = intArrayOf(1, 2, 3)
        result = 0
        dfs(arr, 0, n)
        println(result)
    }
}

private fun dfs(arr: IntArray, sum: Int, n: Int) {
    if (sum == n) {
        result++
        return
    }

    //println("$sum, $n $result")

    for (i in 0..2) {
        if (sum + arr[i] <= n) {
            dfs(arr, sum + arr[i], n)
        }
    }
}