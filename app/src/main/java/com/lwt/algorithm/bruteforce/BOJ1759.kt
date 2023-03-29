package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (L, C) = br.readLine().split(" ").map { it.toInt() }
    val password = mutableListOf<String>()

    br.readLine().split(" ").forEach { s ->
        password.add(s)
    }

    val visit = BooleanArray(C) { false }

    password.sort()

    dfs(L, C, password.toTypedArray(), visit, 0, 0)
}

private fun dfs(L: Int, C: Int, password: Array<String>, visit: BooleanArray, start: Int, depth: Int) {
    if (depth == L) {
        // 최소 모음 한 개 이상, 자음 두 개 이상 인지 확인
        var cntVowels = 0
        var cntConsonants = 0
        val result = StringBuilder()

        for (i in 0 until C) {
            if (visit[i]) {
                if (password[i] == "a" || password[i] == "e" || password[i] == "i" || password[i] == "o" || password[i] == "u") cntVowels++
                else cntConsonants++

                result.append(password[i])
            }
        }

        if (cntVowels >= 1 && cntConsonants >= 2) {
            println(result.toString())
        }

        return
    }

    for (i in start until C) {
        if (!visit[i]) {
            visit[i] = true
            dfs(L, C, password, visit, i, depth + 1)
            visit[i] = false
        }
    }
}