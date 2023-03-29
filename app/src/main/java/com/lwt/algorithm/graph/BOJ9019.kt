package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val result = StringBuilder()

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var T = br.readLine().toInt()

    while (T > 0) {
        val (A, B) = br.readLine().split(" ").map { it.toInt() }
        bfs(A, B)
        T--
    }

    println(result.toString())
}

private fun bfs(A: Int, B: Int) {
    val queue = LinkedList<Pair<Int, String>>() // 현재 숫자, 현재까지 수행한 명령어
    queue.add(Pair(A, ""))
    val visit = BooleanArray(10001) { false } // 이미 나온 숫자인지를 확인한다.
    visit[A] = true

    while (queue.isNotEmpty()) {
        val (cNum, cCmd) = queue.pop()

        if (cNum == B) {
            result.append("$cCmd\n")
            break
        }

        // 0:D, 1:S, 2:L, 3:R
        for (i in 0..3) {
            val newNum = changeNumber(cNum, i)

            // 이미 탐색한 숫자.
            if (visit[newNum]) continue

            visit[newNum] = true
            queue.add(Pair(newNum, cCmd + getCmd(i)))
        }
    }
}

private fun getCmd(idx: Int): String {
    return when(idx) {
        0 -> "D"
        1 -> "S"
        2 -> "L"
        else -> "R"
    }
}

private fun changeNumber(num: Int, cmd: Int): Int {
    return when (cmd) {
        //D
        0 -> (num * 2) % 10000
        //S
        1-> if (num == 0) 9999 else num - 1
        //L
        2-> (num % 1000) * 10 + (num / 1000)
        //R
        else -> (num / 10) + (num % 10) * 1000
    }
}