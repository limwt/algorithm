package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

private var tspResult = Int.MAX_VALUE
private var first = 0

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val W = Array(N + 1) {
        IntArray(N + 1) {
            0
        }
    }
    val visit = BooleanArray(N + 1) {
        false
    }

    repeat(N) {
        val arr = br.readLine().split(" ").map { it.toInt() }.toIntArray()

        arr.forEachIndexed { index, a ->
            W[it + 1][index + 1] = a
        }
    }

    visit[0] = true

    for (i in 1..N) {
        first = i
        visit[i] = true
        dfs(N, i, W, visit, 1, 0)
    }


    println(tspResult)
}

private fun dfs(n: Int, start: Int, w: Array<IntArray>, visit: BooleanArray, depth: Int, sum: Int) {
    if (depth >= n && w[start][first] != 0) {
        //println("${w[start][first]}, $tspResult")
        tspResult = Math.min(tspResult, sum + w[start][first])
        return
    }

    for (i in 1..n) {
        if (!visit[i]) {
            //println("start $start, i $i, sum $sum depth $depth")
            visit[i] = true
            if (w[start][i] != 0) {
                dfs(n, i, w, visit, depth + 1, sum + w[start][i])
            }
            visit[i] = false
        }
    }
}
