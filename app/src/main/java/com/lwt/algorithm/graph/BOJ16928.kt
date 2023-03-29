package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var result = Int.MAX_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val ladder = IntArray(101) { 0 }
    val snake = IntArray(101) { 0 }

    // 사다리 정보 저장
    repeat(N) {
        val (x, y) = br.readLine().split(" ").map { it.toInt() }
        ladder[x] = y
    }

    // 뱀 정보 저장
    repeat(M) {
        val (u, v) = br.readLine().split(" ").map { it.toInt() }
        snake[u] = v
    }

    bfs(ladder, snake)
    println(result)
}

private fun bfs(ladder: IntArray, snake: IntArray) {
    val queue = LinkedList<Pair<Int, Int>>() // 0: 현재 위치, 1: 주사위 굴린 횟수
    queue.add(Pair(1, 0))
    val visit = BooleanArray(101) { false }
    // 첫번째 위치는 방문했으므로 true 설정한다.
    visit[1] = true

    while (queue.isNotEmpty()) {
        val (cPos, cCount) = queue.pop()
        //println("current $cPos, $cCount")

        if (cPos == 100) {
            result = Math.min(result, cCount)
        } else {
            for (i in 1..6) {
                val nPos = cPos + i

                if (nPos < 1 || nPos > 100) continue

                // 이미 방문한 위치이므로 다음 탐색을 한다.
                if (visit[nPos]) continue

                if (ladder[nPos] != 0) {
                    visit[nPos] = true
                    visit[ladder[nPos]] = true
                    queue.add(Pair(ladder[nPos], cCount + 1))
                } else if (snake[nPos] != 0) {
                    visit[nPos] = true
                    visit[snake[nPos]] = true
                    queue.add(Pair(snake[nPos], cCount + 1))
                } else {
                    visit[nPos] = true
                    queue.add(Pair(nPos, cCount + 1))
                }
            }
        }
    }
}