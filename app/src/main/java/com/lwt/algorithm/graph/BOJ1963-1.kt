package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var T = br.readLine().toInt()

    //에라토스테네스의 체를 이용하여 소수를 구한다.
    val prime = BooleanArray(10000) { true }
    val sqrt = Math.sqrt(9999.0).toInt()

    for (i in 2..sqrt) {
        // 소수가 아니다.
        if (!prime[i]) continue

        //소수인 숫자의 배수를 탐색하여 primeCheck 에서 제거한다.
        var j = 2

        while (i * j <= 9999) {
            if (prime[i * j]) prime[i * j] = false
            j++
        }
    }

    val result = StringBuilder()

    while (T > 0) {
        val (start, target) = br.readLine().split(" ").map { it.toInt() }
        val count = bfs(start, target, prime)
        result.append("${if (count == -1) "Impossible" else count}\n")
        T--
    }

    println(result.toString())
}

private fun bfs(start: Int, target: Int, prime: BooleanArray): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(start, 0))
    val visit = BooleanArray(10000) { false }
    visit[start] = true

    while (queue.isNotEmpty()) {
        val current = queue.pop()

        if (current.first == target) return current.second

        for (i in 0..3) {
            for (j in 0..9) {
                // 천의 자리 숫자는 0으로 될 수 없다.
                if (i == 0 && j == 0) continue

                // 숫자 변경
                val newNumber = getNumber(current.first, i, j)

                //소수인지 확인
                if (!prime[newNumber]) continue
                //이미 탐색한 숫자인 지 확인
                if (visit[newNumber]) continue

                visit[newNumber] = true
                queue.add(Pair(newNumber, current.second + 1))
            }
        }
    }

    return -1
}

private fun getNumber(num: Int, index: Int, target: Int): Int {
    val sb = StringBuilder()
    sb.append(num.toString())
    sb.setCharAt(index,  target.digitToChar())
    return sb.toString().toInt()
}
