package com.lwt.algorithm.bruteforce

import java.io.BufferedReader
import java.io.InputStreamReader

private val result = StringBuilder()
private var completed = false

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var T = 0

    while (true) {
        val N = br.readLine().toInt()

        if (N == 0) break

        result.append("Puzzle ${++T}\n")

        val sudominoku = Array(9) { IntArray(9) { 0 } }
        val domino = Array(10) { IntArray(10) { 0 } }
        completed = false

        repeat(N) {
            val (num1, num1Position, num2, num2Position) = br.readLine().split(" ").map { it }
            domino[num1.toInt()][num2.toInt()] = 1 // 도미노가 놓여져 있을 때 1로 업데이트 한다.
            domino[num2.toInt()][num1.toInt()] = 1 // 도미노가 회전을 할 수 있으므로 양방향으로 저장한다.

            val (x1, y1) = num1Position.map { it }
            sudominoku[x1 - 'A'][y1.digitToInt() - 1] = num1.toInt()
            val (x2, y2) = num2Position.map { it }
            sudominoku[x2 - 'A'][y2.digitToInt() - 1] = num2.toInt()
        }

        val numPositionList = br.readLine().split(" ").map { it }

        for ((index, position) in numPositionList.withIndex()) {
            val (x, y) = position.map { it }
            sudominoku[x - 'A'][y.digitToInt() - 1] = index + 1
        }

        /*println("===== Debug Print =====")
        sudominoku.forEach { _sudomiku ->
            _sudomiku.forEach {
                print("$it ")
            }
            println()
        }*/

        dfs(sudominoku, domino, 0)
    }

    println(result.toString())
}

private fun dfs(sudominoku: Array<IntArray>, domino: Array<IntArray>, depth: Int) {
    if (completed) {
        return
    }

    if (!completed && depth == 81) {
        completed = true
        sudominoku.forEach { _sudomiku ->
            _sudomiku.forEach {
                result.append(it)
                //print("$it")
            }
            result.append("\n")
            //println()
        }

        return
    }

    val row = depth / 9
    val col = depth % 9

    if (sudominoku[row][col] == 0) {
        // 도미노를 아래와 오른쪽에 대해서 각각 완전 탐색한다.
        val dX = intArrayOf(1, 0)
        val dY = intArrayOf(0, 1)

        for (i in 0..1) {
            val newRow = row + dX[i]
            val newCol = col + dY[i]

            // 1 ~ 9 중 도미노에 들어갈 수 있는 숫자를 확인한다.
            for (j in 1..9) {
                for (k in 1..9) {
                    // 같은 행/같은 열에 숫자가 없고
                    // 사용하지 않은 도미노 인지 확인한다.
                    // 도미노는 2개의 숫자가 인접해 있으므로 j, k 두 개의 수를 확인 한다.

                    // 같은 숫자이므로 다음 탐색을 한다.
                    if (j == k) continue

                    // 범위 밖이므로 다음 탐색을 한다.
                    if ((newRow < 0 || newRow >= 9 ) || (newCol < 0 || newCol >= 9)) continue

                    // 이미 숫자가 채워져 있으므로 다음 탐색을 한다.
                    if (sudominoku[newRow][newCol] != 0) continue

                    // 이미 사용한 도미노 이므로 다음 탐색을 한다.
                    if (domino[j][k] == 1) continue

                    if (check(sudominoku, row, col, j) && check(sudominoku, newRow, newCol, k)) {
                        sudominoku[row][col] = j
                        sudominoku[newRow][newCol] = k
                        domino[j][k] = 1
                        domino[k][j] = 1
                        dfs(sudominoku, domino, depth + 1)
                        sudominoku[row][col] = 0
                        sudominoku[newRow][newCol] = 0
                        domino[j][k] = 0
                        domino[k][j] = 0
                    }
                }
            }
        }
    } else {
        // 이미 sudominoku 에 숫자가 채워져 있으므로 다음 탐색을 한다.
        dfs(sudominoku, domino, depth + 1)
    }
}

private fun check(sudominoku: Array<IntArray>, row: Int, col: Int, value: Int): Boolean {
    for (i in 0..8) {
        // 같은 행에 동일한 숫자가 있는 지 확인 한다.
        if (sudominoku[row][i] == value) return false
        // 같은 열에 동일한 숫자가 있는 지 확인 한다.
        if (sudominoku[i][col] == value) return false
    }

    // 3X3에 같은 숫자가 있는 지 확인 한다.
    val newX = (row / 3) * 3
    val newY = (col / 3) * 3
    for (i in newX until (newX + 3)) {
        for (j in newY until (newY + 3)) {
            if (sudominoku[i][j] == value) return false
        }
    }

    return true
}