package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader

private var result = false

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    // N : 친구 수     (5<= N <= 2000)
    // M : 친구 관계 수 (1<= N <= 2000)
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val friend = Array(N) {
        ArrayList<Int>()
    }

    repeat(M) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        // a - b 과 친구 관계이면 서로 친구사이이므로 양방향으로 값을 설정한다.
        friend[a].add(b)
        friend[b].add(a)
    }

    for (i in 0 until N) {
        val visit = BooleanArray(N) { false }
        visit[i] = true
        dfs(N, friend, visit, i, 0)

        if (result) {
            break
        }

        result = false
    }

    if (result) {
        println(1)
    } else {
        println(0)
    }
}

private fun dfs(N: Int, friend: Array<ArrayList<Int>>, visit: BooleanArray, start: Int, depth: Int) {
    if (depth == 4) {
        // 조건처럼 5명의 친구관계가 되면 조건을 만족한다.
        result = true
        return
    }

    //println("dfs $start, $depth")

    for (next in friend[start]) {
        if (!visit[next]) {
            visit[next] = true
            dfs(N, friend, visit, next, depth + 1)
            visit[next] = false
        }
    }
}