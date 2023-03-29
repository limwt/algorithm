package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))

    while (true) {
        val input = br.readLine()

        if (input == "0") break

        val inputArr = input.split(" ").map { it.toInt() }.toIntArray()
        val k = inputArr[0]
        val lotte = IntArray(k) { 0 }

        for (i in 1 until inputArr.size) {
            lotte[i - 1] = inputArr[i]
        }

        val visit = BooleanArray(k) { false }

        dfs(k, lotte, visit, 0, 0)
        println("")
    }
}

private fun dfs(k: Int, lotte: IntArray, visit: BooleanArray, start: Int, depth: Int) {
    if (depth == 6) {
        val sb = StringBuilder()
        for (i in 0 until k) {
            if (visit[i]) {
                sb.append("${lotte[i]} ")
            }
        }
        println(sb.toString())
        return
    }

    for (i in start until k) {
        if (!visit[i]) {
            visit[i] = true
            dfs(k, lotte, visit, i, depth + 1)
            visit[i] = false
        }
    }
}
