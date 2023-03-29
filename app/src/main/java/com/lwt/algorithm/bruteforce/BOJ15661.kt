package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

private var result15661 = Int.MAX_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val team = Array(N) {
        IntArray(N) {
            0
        }
    }

    repeat(N) { index ->
        team[index] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    for (i in 1 until N) {
        val visit = BooleanArray(N) { false }
        dfs(i, team, visit, 0, 0, N)
    }

    println(result15661)
}

private fun dfs(N: Int, team: Array<IntArray>, visit: BooleanArray, start: Int, depth: Int, total: Int) {
    if (depth == N) {
        result15661 = Math.min(result15661, getResult(team, visit, total))
        return
    }

    for (i in start until total) {
        if (!visit[i]) {
            visit[i] = true
            dfs(N, team, visit, i, depth + 1, total)
            visit[i] = false
        }
    }
}

private fun getResult(team: Array<IntArray>, visit: BooleanArray, total: Int): Int {
    var startTeam = 0
    var linkTeam = 0

    visit.forEachIndexed { index, v ->
        //println("test v[$index] : $v")
    }

    for (i in 0 until total) {
        for (j in 0 until total) {
            //println("team[$i][$j] = ${team[i][j]}")
            if (visit[i] && visit[j]) {
                //println("startTeam[$i][$j]")
                startTeam += team[i][j]
            }
            else if (!visit[i] && !visit[j]) {
                //println("linkTeam[$i][$j]")
                linkTeam += team[i][j]
            }
        }
    }

    //println("startTeam $startTeam, linkTeam $linkTeam")
    return Math.abs(startTeam - linkTeam)
}