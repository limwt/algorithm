package com.lwt.algorithm.tree

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var result = Int.MIN_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val list = Array(n + 1) { ArrayList<Pair<Int, Int>>() }

    repeat(n - 1) {
        val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
        list[a].add(Pair(b, c))
        list[b].add(Pair(a, c))
    }

    // 루트 노드 1에서 가장 거리가 먼 노드를 찾는다.
    val longestIndex = bfs(n, list, 1)
    //println(longestIndex)
    //println(">>>>>>>>>>>>>>>")
    bfs(n, list, longestIndex)

    println(result)
}

private fun bfs(n: Int, list: Array<ArrayList<Pair<Int, Int>>>, start: Int): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(start, 0))
    val visit = BooleanArray(n + 1) { false }
    visit[start] = true
    var longestNode = 0

    while (queue.isNotEmpty()) {
        //println("queue : $queue, result : $result")
        val (vertex, distance) = queue.pop()

        if (result < distance) {
            result = distance
            longestNode = vertex
        }

        for (next in list[vertex]) {
            if (visit[next.first]) continue

            visit[next.first] = true
            queue.add(Pair(next.first, next.second + distance))
        }
    }

    return longestNode
}