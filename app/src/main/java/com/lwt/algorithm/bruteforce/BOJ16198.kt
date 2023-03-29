package com.lwt.algorithm.bruteforce

import java.io.BufferedReader
import java.io.InputStreamReader

private var result = Int.MIN_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val W = br.readLine().split(" ").map { it.toInt() }.toMutableList()

    dfs(N, W, 0)
    println(result)
}

private fun dfs(N: Int, W: MutableList<Int>, sum: Int) {
    //println("W size = ${W.size}")
    // 처음과 마지막 구슬만 남았으므로 에너지 양을 계산한다.
    if (W.size == 2) {
        result = Math.max(result, sum)
        return
    }

    for (i in 1 until W.size - 1) {
        //println("W[$i] = ${W[i]}")
        val marble = W[i]
        W.removeAt(i)
        val energy = W[i - 1] * W[i]
        //println("sum = $sum, $energy, $marble, $i")
        dfs(N, W, sum + energy)
        W.add(i, marble)
    }
}