package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var circular = false
private val result = IntArray(3001) {
    -1
}

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val map = Array(N + 1) {
        ArrayList<Int>()
    }

    repeat(N) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        map[a].add(b)
        map[b].add(a)
    }

    for (i in 1..N) {
        val visit = BooleanArray(N + 1) { false }
        circular = false
        dfs(N, map, visit, i, i, 0)
    }

    /*println("circular way >>>")
    for (i in 1..N) {
        print("${result[i] }")
    }
    println()*/


    val visit = BooleanArray(N + 1) {
        false
    }

    val queue = LinkedList<Int>()

    for (i in 1..N) {
        if (result[i] == 0) {
            queue.add(i)
            visit[i] = true
        }
    }

    distance(map, visit, queue)

    for (i in 1..N) {
        print("${result[i]} ")
    }

    println()
}

private fun dfs(N: Int, map: Array<ArrayList<Int>>, visit: BooleanArray, current: Int, target: Int, prev: Int) {
    visit[current] = true

    for (next in map[current]) {
        //println("dfs: $current, $target, $prev, $next, $circular, ${visit[next]}")

        if (!visit[next]) {
            visit[next] = true
            dfs(N, map, visit, next, target, current)
        } else if (next == target && target != prev) {
            circular = true
            result[next] = 0
            return
        }
    }
}

private fun distance(map: Array<ArrayList<Int>>, visit: BooleanArray, queue: LinkedList<Int>) {
    //println("queue > $queue")

    while (queue.isNotEmpty()) {
        val current = queue.pop()
        visit[current] = true
        //println("current > $current")

        for (next in map[current]) {
            if (visit[next]) continue
            //println("next : $next, result[$current] : ${result[current]}")
            queue.add(next)
            visit[next] = true
            result[next] = result[current] + 1
        }
    }
}