package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val picture = Array(N) { CharArray(N) { 'R' } }
    repeat(N) { index ->
        picture[index] = br.readLine().toCharArray()
    }


    val result = StringBuilder()
    var count = 0
    val visit = Array(N) { BooleanArray(N) { false } }

    for (i in 0 until N) {
        for (j in 0 until N) {
            if (!visit[i][j]) {
                count++
                visit[i][j] = true
                bfs(N, picture, visit, i, j, picture[i][j])
            }
        }
    }

    result.append("$count ")
    count = 0
    // visit 배열 초기화
    repeat(N) {
        Arrays.fill(visit[it], false)
    }

    for (i in 0 until N) {
        for (j in 0 until N) {
            if (picture[i][j] == 'R' || picture[i][j] == 'G') {
                picture[i][j] = 'X'
            }
        }
    }

    for (i in 0 until N) {
        for (j in 0 until N) {
            if (!visit[i][j]) {
                count++
                visit[i][j] = true
                bfs(N, picture, visit, i, j, picture[i][j])
            }
        }
    }
    result.append("$count")

    println(result.toString())
}

private fun bfs(N: Int, picture: Array<CharArray>, visit: Array<BooleanArray>, x: Int, y: Int, target: Char) {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(x, y))

    while (queue.isNotEmpty()) {
        val current = queue.pop()

        val dX = intArrayOf(-1, 1,  0, 0)
        val dY = intArrayOf( 0, 0, -1, 1)

        for (i in 0..3) {
            val nX = current.first + dX[i]
            val nY = current.second + dY[i]

            //범위 밖
            if((nX < 0 || nX >= N) || (nY < 0 || nY >= N)) continue
            //다른 색깔의 그림
            if (target != picture[nX][nY]) continue
            //이미 방문한 곳
            if (visit[nX][nY]) continue

            visit[nX][nY] = true
            queue.add(Pair(nX, nY))
        }
    }
}
