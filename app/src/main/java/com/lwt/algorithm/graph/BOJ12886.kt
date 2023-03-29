package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (A, B, C) = br.readLine().split(" ").map { it.toInt() }
    println("${bfs(A, B, C)}")
}

private fun bfs(A: Int, B: Int, C: Int): Int {
    val queue = LinkedList<Triple<Int, Int, Int>>()
    queue.add(Triple(A, B, C))
    val visit = Array(1500) { BooleanArray(1500) { false } }

    while (queue.isNotEmpty()) {
        val (cA, cB, cC) = queue.pop()
        //println("current $cA, $cB, $cC")

        if (cA == cB && cB == cC) {
            return 1
        }

        val sum = cA + cB + cC
        // i = 0 -> A, B 선택
        // i = 1 -> A, C 선택
        // i = 2 -> B, C 선택
        for (i in 0..2) {
            var num1 = cA
            var num2 = cB
            when (i) {
                0 -> {
                    num1 = cA
                    num2 = cB
                }
                1 -> {
                    num1 = cA
                    num2 = cC
                }
                2 -> {
                    num1 = cB
                    num2 = cC
                }
            }

            if (num1 > num2) {
                val temp = num2
                num1 -= temp
                num2 += temp
            } else {
                val temp = num1
                num1 += temp
                num2 -= temp
            }

            if (visit[num1][num2]) continue

            //println("next $num1, $num2, $num3")
            visit[num1][num2] = true
            queue.add(Triple(num1, num2, sum - (num1 + num2)))
        }
    }

    return 0
}