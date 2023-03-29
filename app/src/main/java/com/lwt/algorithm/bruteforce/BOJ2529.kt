package com.lwt.algorithm.bruteforce

import java.io.BufferedReader
import java.io.InputStreamReader

private var min = Long.MAX_VALUE
private var max = Long.MIN_VALUE
private var resultMax = ""
private var resultMin = ""

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val k = br.readLine().toInt()
    val inequalitySign = br.readLine().split(" ").map { it }

    for (i in 0..9) {
        val visit = BooleanArray(10) { false }
        visit[i] = true
        val result = mutableListOf<Int>()
        result.add(i)
        dfs(k, visit, result, i, 0, inequalitySign)
    }

    println(resultMax)
    println(resultMin)
}

private fun dfs(k: Int, visit: BooleanArray, result: MutableList<Int>, num: Int, depth: Int, inequalitySign: List<String>) {
    if (depth == k) {
        val sb = StringBuilder()
        result.forEach { r ->
            sb.append(r)
        }

        //println(sb.toString())

        if (min > sb.toString().toLong()) {
            min = sb.toString().toLong()
            resultMin = sb.toString()
        }

        if (max < sb.toString().toLong()) {
            max = sb.toString().toLong()
            resultMax = sb.toString()
        }

        //println("$min, $max, $resultMin, $resultMax")
        return
    }

    for (i in 0..9) {
        if (visit[i]) continue

        if (inequalitySign[depth] == ">") {
            if (num > i) {
                visit[i] = true
                result.add(i)
                dfs(k, visit, result, i, depth + 1, inequalitySign)
                result.removeLast()
                visit[i] = false
            }
        } else if (inequalitySign[depth] == "<") {
            if (num < i) {
                visit[i] = true
                result.add(i)
                dfs(k, visit, result, i, depth + 1, inequalitySign)
                result.removeLast()
                visit[i] = false
            }
        }
    }
}
