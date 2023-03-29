package com.lwt.algorithm.bruteforce

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val board = Array(N) { "" }

    repeat(N) {
        board[it] = br.readLine()
    }

    // 두 동전의 위치를 찾는다.
    val coin = mutableListOf<Coin>()


    board.forEachIndexed { index1, _board ->
        _board.forEachIndexed { index2, b ->
            if (b == 'o') coin.add(Coin(index1, index2))
        }
    }

    // 동전1, 2에 대한 방문 여부를 각각 확인해야 하므로 3차 배열을 선언한다.
    val visit = Array(N) { Array(M) { Array(N) { BooleanArray(M) { false } } } }
    println("${bfs(N, M, board, coin, visit)}")
}

private fun bfs(N: Int, M: Int, board: Array<String>, coin: MutableList<Coin>, visit: Array<Array<Array<BooleanArray>>>): Int {
    val queue = LinkedList<Pair<MutableList<Coin>, Int>>()
    queue.add(Pair(coin, 0))
    visit[coin[0].x][coin[0].y][coin[1].x][coin[1].y] = true

    while (queue.isNotEmpty()) {
        val (curCoin, curCount) = queue.pop()
        //println("current $curCoin, $curCount")

        // 버튼 누른 횟수가 10회 이상이면 탐색을 중단 한다.
        if (curCount >= 10) break

        //왼쪽, 오른쪽, 위, 아래 동전 이동
        val changeX = intArrayOf(0, 0, -1, 1)
        val changeY = intArrayOf(-1, 1, 0, 0)

        for (i in 0..3) {
            var newX1 = curCoin[0].x + changeX[i]
            var newY1 = curCoin[0].y + changeY[i]
            var newX2 = curCoin[1].x + changeX[i]
            var newY2 = curCoin[1].y + changeY[i]

            //println("($newX1, $newY1), ($newX2, $newY2)")
            // 두 동전 중 하나가 밖으로 나갔는 지 확인 한다.
            var checkedCount = 0

            if ((newX1 < 0 || newX1 >= N) || (newY1 < 0 || newY1 >= M)) checkedCount++
            if ((newX2 < 0 || newX2 >= N) || (newY2 < 0 || newY2 >= M)) checkedCount++

            // 하나의 동전이 밖으로 나갔으므로 횟수를 출력한다.
            if (checkedCount == 1) {
                return curCount + 1
            }

            // 두 동전이 같이 떨어진 경우이므로 다음 탐색을 한다.
            if (checkedCount == 2) continue


            // 두 동전이 이미 방문한 경로면 다음 탐색을 한다.
            if (visit[newX1][newY1][newX2][newY2]) continue

            // 벽을 만나는 동전은 이동하지 않고 그 자리를 유지한다.
            if (board[newX1][newY1] == '#') {
                newX1 = curCoin[0].x
                newY1 = curCoin[0].y
            }
            if (board[newX2][newY2] == '#') {
                newX2 = curCoin[1].x
                newY2 = curCoin[1].y
            }

            visit[newX1][newY1][newX2][newY2] = true
            val list = mutableListOf(Coin(newX1, newY1), Coin(newX2, newY2))
            queue.add(Pair(list, curCount + 1))
        }
    }


    return -1
}

private data class Coin(val x: Int, val y: Int)