package com.lwt.algorithm.bitmask

import java.io.BufferedReader
import java.io.InputStreamReader

private var result = Int.MIN_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, K) = br.readLine().split(" " ).map { it.toInt() }

    if (K < 5) {
        println(0)
    } else {
        // 주어진 문자열을 a ~ z boolean 배열에 저장한다.
        val wordList = Array(N) { "" }
        val visit = BooleanArray(26) { false }
        // a, n, t, i ,c 는 무조건 포함되기 때문에 visit 배열을 true로 설정한다.
        visit['a' - 'a'] = true
        visit['n' - 'a'] = true
        visit['t' - 'a'] = true
        visit['i' - 'a'] = true
        visit['c' - 'a'] = true

        repeat(N) {
            // 입력으로 주어지는 단어를 저장한다.
            wordList[it] = br.readLine()
        }

        dfs(N, K, wordList, visit, 0, 0)
        println(result)
    }
}

private fun dfs(N: Int, K: Int, wordList: Array<String>, visit: BooleanArray, start: Int, depth: Int) {
    if (depth == K - 5) {
        // 단어의 시작이 anta 로 시작하고 끝이 tica 로 끝나므로
        // 단어를 4부터 word.length - 5까지 탐색하여
        // visit 가 false 가 있는 단어가 있는 지 확인 한다.
        // false 가 없을 경우 학생이 읽을 수 있는 단어이다.
        var ret = 0

        for (i in 0 until N) {
            val word = wordList[i]
            var canRead = true

            for (j in 4..word.length - 5) {
                if (!visit[word[j] - 'a']) {
                    canRead = false
                    break
                }
            }

            if (canRead) ret++
        }

        result = Math.max(result, ret)
        return
    }

    for (i in start until 26) {
        if (visit[i]) continue

        visit[i] = true
        dfs(N, K, wordList, visit, i, depth + 1)
        visit[i] = false
    }
}