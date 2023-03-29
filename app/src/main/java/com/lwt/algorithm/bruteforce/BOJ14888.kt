package com.lwt.algorithm.bruteforce

import java.io.BufferedReader
import java.io.InputStreamReader

private var max = Int.MIN_VALUE
private var min = Int.MAX_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val number = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    val op = mutableListOf<String>()

    br.readLine().split(" ").map { it.toInt() }.forEachIndexed { index, count ->
        for (i in 0 until count) {
            op.add(getOperator(index))
        }
    }

    //println("$op")

    val visit = BooleanArray(op.size) { false }
    val resultOp = mutableListOf<String>()
    dfs(N, number, op, visit, 0, resultOp)
    println(max)
    println(min)
}

private fun dfs(N: Int, number: IntArray, op: MutableList<String>, visit: BooleanArray, depth: Int, result: MutableList<String>) {
    if (depth == N - 1) {
        // 최대, 최소 저장하기
        //println("result : $result")
        var sum = number[0]

        for (i in 0 until result.size) {
            when (result[i]) {
                "+" -> sum += number[i + 1]
                "-" -> sum -= number[i + 1]
                "*" -> sum *= number[i + 1]
                "/" -> sum /= number[i + 1]
            }
        }

        min = Math.min(min, sum)
        max = Math.max(max, sum)
        return
    }

    for (i in 0 until op.size) {
        if (visit[i]) continue

        visit[i] = true
        result.add(op[i])
        dfs(N, number, op, visit, depth + 1, result)
        result.removeLast()
        visit[i] = false
    }
}

private fun getOperator(index: Int): String {
    return when (index) {
        0 -> "+"
        1 -> "-"
        2 -> "*"
        else -> "/"
    }
}