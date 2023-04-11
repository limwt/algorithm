package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val wall = Array(8) { IntArray(8) { 0 } }

    repeat(8) { index1 ->
        br.readLine().forEachIndexed { index2, c ->
            //벽이 있는 정보를 저정한다.
            if (c == '#') {
                wall[index1][index2] = 1
            }
        }
    }

    println("${bfs(wall)}")
}

private fun bfs(wall: Array<IntArray>): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(7, 0))

    while (queue.isNotEmpty()) {
        // Wall 의 정보가 1초마다 바뀌기 때문에 각 Wall 의 정보에 대한 BFS 를 수행한다.
        val visit = Array(8) { BooleanArray(8) { false } }

        for (i in 0 until queue.size) {
            val (curX, curY) = queue.pop()
            //println("wall($curX, $curY) => ${wall[curX][curY]}")
            //visit[curX][curY] = true

            // 벽을 만났으므로 다음 Queue 를 수행한다.
            if (wall[curX][curY] == 1) continue

            if (curX == 0 && curY == 7) return 1

            /*println("==============($curX, $curY)=====================")
            wall.forEach { _wall ->
                _wall.forEach { w ->
                    print("$w ")
                }
                println()
            }*/

            val dX = intArrayOf(-1, -1, -1, 0, 1, 1,  1,  0, 0)
            val dY = intArrayOf(-1,  0,  1, 1, 1, 0, -1, -1, 0)

            for (i in 0..8) {
                val newX = curX + dX[i]
                val newY = curY + dY[i]

                //범위 밖
                if ((newX < 0 || newX >= 8) || (newY < 0 || newY >= 8)) continue
                //이미 방문한 곳
                //if (visit[newX][newY]) continue
                //벽으로는 이동 불가
                if (wall[newX][newY] == 1) continue

                if (visit[newX][newY]) continue

                //visit[newX][newY] = true
                visit[newX][newY] = true
                queue.add(Pair(newX, newY))
            }
        }

        // 벽을 이동한다.
        for (i in 6 downTo 0 step 1) {
            for (j in 0..7) {
                wall[i + 1][j] = wall[i][j]
            }
        }

        wall[0] = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0)
    }

    return 0
}

/*
######..
.#.#.###
........
........
........
........
........
........

답:0

######..
.#######
######..
........
........
........
........
........

답:0

########
........
........
........
........
........
........
........

답:0*/
