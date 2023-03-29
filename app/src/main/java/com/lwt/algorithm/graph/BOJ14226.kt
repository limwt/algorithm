package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val S = br.readLine().toInt()

    bfs(1, S)
}

private fun bfs(start: Int, target: Int) {
    val queue = LinkedList<Triple<Int, Int, Int>>() // (현재 이모티콘, 클립보트 이모티콘, 소요 시간)
    queue.add(Triple(start, 0, 0))
    val visit = Array(1001) { BooleanArray(1001) { false } }

    while (queue.isNotEmpty()) {
        val (curEmo, clipEmo, time) = queue.pop()

        if (curEmo == target) {
            println(time)
            break
        }

        if ((clipEmo > 1000 || clipEmo < 0) || (curEmo > 1000 || curEmo < 0)) continue

        if (visit[curEmo][clipEmo]) continue

        visit[curEmo][clipEmo] = true

        //println("current : $curEmo, $clipEmo, $time")

        queue.add(Triple(curEmo, curEmo, time + 1))
        queue.add(Triple(curEmo + clipEmo, clipEmo, time + 1))
        queue.add(Triple(curEmo - 1, clipEmo, time + 1))
    }
}