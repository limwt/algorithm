package com.lwt.algorithm.bruteforce

import java.io.BufferedReader
import java.io.InputStreamReader

private var result = Int.MAX_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val team = Array(N) { IntArray(N) { 0 } }

    repeat(N) { index ->
        team[index] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    // 두 팀으로 나누는 경우를 확인하는 DFS 를 수행한다.
    val visit = BooleanArray(N) { false }
    val startTeam = mutableListOf<Int>()
    val linkTeam = mutableListOf<Int>()
    dfs(N, team, visit, 0, 0, startTeam, linkTeam)
    println(result)
}

private fun dfs(N: Int, team: Array<IntArray>, visit: BooleanArray, curTeam: Int, depth: Int, startTeam: MutableList<Int>, linkTeam: MutableList<Int>) {
    if (depth == N / 2) {
        // 스타트와 링크 팀을 구하고 두 팀의 능력의 차이를 구한다.
        for (i in 0 until N) {
            if (startTeam.contains(i)) continue
            linkTeam.add(i)
        }

        //println("$startTeam, $linkTeam")
        val start = getTeamResult(team, startTeam)
        val link = getTeamResult(team, linkTeam)
        result = Math.min(result, Math.abs(start - link))
        linkTeam.clear()
        return
    }

    for (i in curTeam until N) {
        if (visit[i]) continue

        visit[i] = true
        startTeam.add(i)
        dfs(N, team, visit, i, depth + 1, startTeam, linkTeam)
        startTeam.removeLast()
        visit[i] = false
    }
}

private fun getTeamResult(team: Array<IntArray>, curTeam: MutableList<Int>): Int {
    var sum = 0
    for (i in 0 until curTeam.size - 1) {
        for (j in i until curTeam.size) {
            sum += team[curTeam[i]][curTeam[j]] + team[curTeam[j]][curTeam[i]]
        }
    }

    //println("sum = $sum")
    return sum
}
