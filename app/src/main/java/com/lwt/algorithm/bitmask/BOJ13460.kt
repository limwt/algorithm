package com.lwt.algorithm.bitmask

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var result = Int.MAX_VALUE

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val board = Array(N) { CharArray(M) { '.' } } // 빈칸인 . 으로 초기화한다.

    //보드의 정보를 저장한다.
    //R, B 구슬의 위치를 찾아 저장한다.
    var redX = 0
    var redY = 0
    var blueX = 0
    var blueY = 0
    repeat(N) { index ->
        br.readLine().toCharArray().forEachIndexed { index2, c ->
            board[index][index2] = c

            // 빨간 구슬의 처음 위치 저장
            if (c == 'R') {
                redX = index
                redY = index2
                board[index][index2] = '.'
            }

            // 파란 구슬의 처음 위치 저장
            if (c == 'B') {
                blueX = index
                blueY = index2
                board[index][index2] = '.'
            }
        }
    }
    //println("${bfs(N, M, board, marble)}")
    val marble = Marble(Point(redX, redY), Point(blueX, blueY), 0)
    println("${bfs(N, M, board, marble)}")
}

private fun bfs(N: Int, M: Int, board:Array<CharArray>, marble: Marble): Int {
    val queue = LinkedList<Marble>()
    queue.add(marble)
    val visit = Array(N) { Array(M) { Array(N) { BooleanArray(M) { false } } } }
    visit[marble.red.x][marble.red.y][marble.blue.x][marble.blue.y] = true

    while (queue.isNotEmpty()) {
        val (cRed, cBlue, cCount) = queue.pop()
        //println("current $cRed, $cBlue, $cCount")

        if (cCount == 10) return -1

        for (i in 0..3) {
            val (blueP, blueHole) = moveMarble(board, cBlue, i)
            //println("moveBlue $blueP, $blueHole")
            // 파란 구슬이 구멍에 빠졌으므로 다른 방향을 탐색한다.
            if (blueHole) continue

            val (redP, redHold) = moveMarble(board, cRed, i)
            //println("moveRed $redP, $redHold")
            // 붉은 구슬이 구멍에 빠졌으므로 결과를 출력한다.
            if (redHold) return cCount + 1

            // 두 구슬이 겹쳤으면 위치를 조정한다.
            if (blueP == redP) {
                when (i) {
                    //상
                    0 -> {
                        if (cRed.x > cBlue.x) redP.x++
                        else blueP.x++
                    }
                    //하
                    1 -> {
                        if (cRed.x > cBlue.x) blueP.x--
                        else redP.x--
                    }
                    //좌
                    2 -> {
                        if (cRed.y > cBlue.y) redP.y++
                        else blueP.y++
                    }
                    //우
                    else -> {
                        if (cRed.y > cBlue.y) blueP.y--
                        else redP.y--
                    }
                }
            }

            //범위를 벗어난 경우...
            if ((redP.x < 0 || redP.x >= N) || (redP.y < 0 || redP.y >= M)) continue
            if ((blueP.x < 0 || blueP.x >= N) || (blueP.y < 0 || blueP.y >= M)) continue
            //이미 방문한 경우
            if (visit[redP.x][redP.y][blueP.x][blueP.y]) continue

            visit[redP.x][redP.y][blueP.x][blueP.y] = true
            queue.add(Marble(redP, blueP, cCount + 1))
        }
    }

    return -1
}

private fun moveMarble(board: Array<CharArray>, p: Point, direction: Int): Pair<Point, Boolean> {
    // 상/하/좌/우 기울임
    val dX = intArrayOf(-1, 1, 0, 0)
    val dY = intArrayOf(0, 0, -1, 1)
    var count = 1
    var newX = p.x + dX[direction] * count
    var newY = p.y + dY[direction] * count
    var isHole = false

    // 벽을 만날때 까지 같은 방향으로 기울인다.
    while (board[newX][newY] != '#') {
        if (board[newX][newY] == 'O') {
            isHole = true
            break
        }

        count++
        newX = p.x + dX[direction] * count
        newY = p.y + dY[direction] * count
    }


    return Pair(Point(p.x + dX[direction] * (count - 1), p.y + dY[direction] * (count - 1)), isHole)
}

private data class Point(var x: Int, var y: Int)

private data class Marble(val red: Point, val blue: Point, val count: Int)