package com.lwt.algorithm.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (W, H) = br.readLine().split(" ").map { it.toInt() }
    val map = Array(H) { CharArray(W) { '.'} }
    repeat(H) { index ->
        map[index] = br.readLine().toCharArray()
    }

    // 두 개의 C 를 찾아 시작 지점과 종료 지점을 저정한다.
    val laserArea = mutableListOf<Pair<Int, Int>>()

    for (i in 0 until H) {
        for (j in 0 until W) {
            if (map[i][j] == 'C') {
                laserArea.add(Pair(i, j))
            }
        }
    }

    println("${bfs(H, W, map, laserArea)}")
}

private fun bfs(H: Int, W: Int, map: Array<CharArray>, laserArea: MutableList<Pair<Int, Int>>): Int {
    var result = Int.MAX_VALUE
    val queue = LinkedList<Laser>()
    queue.add(Laser(laserArea[0].first, laserArea[0].second, -1, -1))
    // 현재위치의 각 방향에 대한 거울 수를 저장한다.
    val mirrorCount = Array(H) { Array(W) { IntArray(4) { Int.MAX_VALUE } } }

    while (queue.isNotEmpty()) {
        val current = queue.pop()
        val dX = intArrayOf(-1, 1,  0, 0)
        val dY = intArrayOf( 0, 0, -1, 1)

        if (current.x == laserArea[1].first && current.y == laserArea[1].second) {
            result = Math.min(result, current.mirrorCount)
        } else {
            //println("current $current")

            for (i in 0..3) {
                val nX = current.x + dX[i]
                val nY = current.y + dY[i]

                //범위 밖
                if ((nX < 0 || nX >= H) || (nY < 0 || nY >= W)) continue
                //벽으로 된 영역
                if (map[nX][nY] == '*') continue

                if (current.direction == 0 && i == 1) continue
                if (current.direction == 1 && i == 0) continue
                if (current.direction == 2 && i == 3) continue
                if (current.direction == 3 && i == 2) continue

                val nextMirrorCount = if (current.direction == i) current.mirrorCount else current.mirrorCount + 1
                //println("next >> ($nX, $nY), ${current.direction}, $i / $nextMirrorCount, ${mirrorCount[nX][nY][i]}")

                if (mirrorCount[nX][nY][i] > nextMirrorCount) {
                    mirrorCount[nX][nY][i] = nextMirrorCount
                    queue.add(Laser(nX, nY, nextMirrorCount, i))
                }
            }
        }
    }

    return result
}

private data class Laser(
    // 현재 빛의 위치 X 좌표
    val x: Int,
    // 현재 빛의 위치 Y 좌표
    val y: Int,
    // 현재까지 세운 거울의 수
    val mirrorCount: Int,
    // 빛이 어느 방향으로 가는 지 확인
    // 0: 상, 1: 하, 2: 좌, 3: 우
    val direction: Int
)