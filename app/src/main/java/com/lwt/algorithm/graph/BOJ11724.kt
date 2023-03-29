package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val graph = Array(N + 1) { ArrayList<Int>() }

    repeat(M) {
        val (u, v) = br.readLine().split(" ").map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }

    val visit = BooleanArray(N + 1) { false }
    var count = 0

    for (i in 1..N) {
        //println("i = $i")
        if (!visit[i]) {
            visit[i] = true
            dfs(N, graph, visit, i)
            count++
        }
    }

    println(count)
}

private fun dfs(N: Int, graph: Array<ArrayList<Int>>, visit: BooleanArray, start: Int) {
    for (next in graph[start]) {
        //println("next = $next, ${visit[next]}")
        if (!visit[next]) {
            visit[next] = true
            dfs(N, graph, visit, next)
        }
    }
}