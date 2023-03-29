package com.lwt.algorithm.bruteforce

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sudoku = Array(9) { IntArray(9) }
    repeat(9) { index ->
        sudoku[index] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    dfs(sudoku, 0, 0)
}

private fun dfs(sudoku: Array<IntArray>, row: Int, col: Int) {
    //var r = row

    if (col == 9) {
        dfs(sudoku, row + 1, 0)
        return
    }

    if (row == 9) {
        // 결과 출력
        val sb = StringBuilder()

        for (i in 0..8) {
            for (j in 0..8) {
                sb.append("${sudoku[i][j]} ")
            }
            sb.append("\n")
        }

        println(sb.toString())
        exitProcess(0)
    }

    if (sudoku[row][col] == 0) {
        // 넣을 수 있는 Value 를 찾는다.
        for (i in 1..9) {
            if (check(sudoku, row, col, i)) {
                sudoku[row][col] = i
                dfs(sudoku, row, col + 1)
                sudoku[row][col] = 0
            }
        }
    } else {
        dfs(sudoku, row, col + 1)
    }
}

private fun check(sudoku: Array<IntArray>, row: Int, col: Int, number: Int): Boolean {
    // 같은 열에 number 와 같은게 있는 지 확인 한다.
    for (i in 0..8) {
        if (sudoku[row][i] == number) return false
    }

    // 같은 향에 number 와 같은게 있는 지 확인 한다.
    for (i in 0..8) {
        if (sudoku[i][col] == number) return false
    }

    // 주어진 row, col 이 속한 3X3 작은 사각형을 탐색하여 같은 숫자가 있는 지 확인한다.
    val r = (row / 3) * 3
    val c = (col / 3) * 3
    for (i in r..r +2) {
        for (j in c..c + 2) {
            if (sudoku[i][j] == number) return false
        }
    }

    return true
}