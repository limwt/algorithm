package com.lwt.algorithm.bruteforce

import java.io.BufferedReader
import java.io.InputStreamReader

private var max = Int.MIN_VALUE
private var min = Int.MAX_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val number = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    val operator = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    dfs(N, number, operator, 1, number[0])
    println(max)
    println(min)
}

private fun dfs(N: Int, number: IntArray, operator: IntArray, depth: Int, sum: Int) {
    if (depth == N) {
        min = Math.min(min, sum)
        max = Math.max(max, sum)
        return
    }

    for (i in 0..3) {
        if (operator[i] > 0) {
            operator[i] -= 1
            //println("operator[$i]:${operator[i]}, sum:$sum")
            when (i) {
                0 -> dfs(N, number, operator, depth + 1, sum + number[depth])
                1 -> dfs(N, number, operator, depth + 1, sum - number[depth])
                2 -> dfs(N, number, operator, depth + 1, sum * number[depth])
                3 -> dfs(N, number, operator, depth + 1, sum / number[depth])
            }

            operator[i] += 1
        }
    }
}