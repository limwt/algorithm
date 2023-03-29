package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

private var result14501 = Int.MIN_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val T = IntArray(N) { 0 }
    val P = IntArray(N) { 0 }
    repeat(N) {
        val (t, p) = br.readLine().split(" ").map { it.toInt() }
        T[it] = t
        P[it] = p
    }

    dfs(N, T, P, 0, 0)
    println(result14501)
}

private fun dfs(N: Int, T: IntArray, P: IntArray, start: Int, sum: Int) {
    if (start == N) {
        result14501 = Math.max(result14501, sum)
        return
    }

    if (start > N) {
        return
    }

    dfs(N, T, P, start + T[start], sum + P[start])
    dfs(N, T, P, start + 1, sum)
}