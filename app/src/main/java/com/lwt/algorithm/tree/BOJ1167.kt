package com.lwt.algorithm.tree

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

// 트리의 지름에 대한 정의를 알고 있어야 한다.
// 루트 노드(여기선 1로 가정)에서 가장 먼 노드를 찾는다.(u)
// 위에서 찾은 노드(u) 에서 가장 먼 노드를 찾는다.(v)
// u - v 간의 거리를 구한다.

private var result = Int.MIN_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val V = br.readLine().toInt()
    val list = Array(V + 1) { ArrayList<Pair<Int, Int>>()}

    repeat(V) {
        val input = br.readLine().split(" ").map { it.toInt() }

        for (i in 1..input.size - 2 step 2) {
            val vertex = input[i]
            val distance = input[i + 1]

            list[input[0]].add(Pair(vertex, distance))
            //list[vertex].add(Pair(input[0], distance))
        }
    }

    /*for (i in 1..V) {
        println("${list[i]}")
    }*/

    val longestIndex = bfs(V, list, 1)
    //println("longestIndex : $longestIndex")
    bfs(V, list, longestIndex)

    println("$result")
}

private fun bfs(V: Int, list: Array<ArrayList<Pair<Int, Int>>>, start: Int): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(start, 0))
    val visit = BooleanArray(V + 1) { false }
    visit[start] = true
    var maxIndex = 0
    var maxDistance = 0

    while (queue.isNotEmpty()) {
        val (vertex, distance) = queue.pop()

        if (maxDistance < distance) {
            maxIndex = vertex
            maxDistance = distance
        }

        for (next in list[vertex]) {
            if (visit[next.first]) continue

            visit[next.first] = true
            queue.add(Pair(next.first, distance + next.second))
        }
    }

    result = Math.max(result, maxDistance)
    return maxIndex
}
