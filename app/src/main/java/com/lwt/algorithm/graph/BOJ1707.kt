package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader

// 이분 그래프의 정의를 알아야 알고리즘을 접근하기 쉽다.
// 이분 그래프랑 모든 이접한 접점들이 서로 다른 색으로 칠해 지는 경우이다.

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val K = br.readLine().toInt()

    repeat(K) {
        val (V, E) = br.readLine().split(" ").map { it.toInt() }
        // 배열로 선언하면 크기가 20001 X 20001 배열이 되므로 모든 경우를 탐색할 경우 메모리 초과가 발생한다.
        // 그러므로 인접 리스트를 이용하여 저장한다.
        val graph = Array(V + 1) { ArrayList<Int>() }

        repeat(E) {
            val (u, v) = br.readLine().split(" ").map { value -> value.toInt() }
            graph[u].add(v)
            graph[v].add(u)
        }

        val visit = BooleanArray(V + 1) { false }
        val color = IntArray(V + 1) { -1 } // 1또는 2로 색칠한다.

        for (i in 1..V) {
            if (!visit[i]) {
                visit[i] = true
                color[i] = 0
                dfs(graph, visit, i, color)
            }
        }

        // 이분 그래프인지 확인한다.
        // 인접한 접점이 서로 색깔이 다른 지 확인 한다.
        var result = true

        for (i in 1..V) {
            for (point in graph[i]) {
                if (color[i] == color[point]) {
                    result = false
                    break
                }
            }

            if (!result) break
        }

        if (result) println("YES") else println("NO")
    }
}

private fun dfs(graph: Array<ArrayList<Int>>, visit: BooleanArray, start: Int, color: IntArray) {
    for (next in graph[start]) {
        if (!visit[next]) {
            visit[next] = true
            color[next] = (color[start] + 1) % 2
            dfs(graph, visit, start, color)
        }
    }
}