package com.lwt.algorithm.bitmask

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val board = Array(N) { LongArray(N) { 0L } }
    repeat(N) { index ->
        board[index] = br.readLine().split(" ").map { it.toLong() }.toLongArray()
    }

    println("${bfs(N, board)}")
}

private fun bfs(N: Int, board: Array<LongArray>): Long {
    var result = Long.MIN_VALUE
    val queue = LinkedList<Pair<Array<LongArray>, Int>>()
    queue.add(Pair(board, 0))

    while (queue.isNotEmpty()) {
        val (cBoard, cCount) = queue.pop()

        if (cCount == 5) {
            var max = -1L
            cBoard.forEach { _board ->
                _board.forEach {
                    //print("$it ")
                    max = Math.max(max, it)
                }
                //println()
            }

            result = Math.max(result, max)
        } else {
            //상하좌우 이동
            for (i in 0..3) {
                val nBoard = Array(N) { LongArray(N) { 0L } }
                // 현재 보드 상태를 깊은 복사로 카피 한다.
                cBoard.forEachIndexed { index1, _board ->
                    _board.forEachIndexed { index2, b ->
                        nBoard[index1][index2] = b
                    }
                }

                /*println("===== current board =====")
                nBoard.forEach { _board ->
                    _board.forEach {
                        print("$it ")
                    }
                    println()
                }
                println("======================")*/

                val newBoard = moveBlock(N, nBoard, i)
                /*println("===== new board($i) =====")
                newBoard.forEach { _board ->
                    _board.forEach {
                        print("$it ")
                    }
                    println()
                }
                println("======================")*/

                //queue.add(Pair(moveBlock(N, board, i), cCount + 1))
                queue.add(Pair(newBoard, cCount + 1))
            }
        }
    }

    return result
}

private fun moveBlock(N: Int, board: Array<LongArray>, direction: Int): Array<LongArray> {
    /*println("===== current board($direction) =====")
    board.forEach { _board ->
        _board.forEach {
            print("$it ")
        }
        println()
    }
    println("======================")*/

    when (direction) {
        //상
        0 -> {
           for (i in 0 until N) {
               val stack = Stack<Long>()
               var combined = false
               for (j in 0 until N) {
                   // 빈칸은 stack 에 저장하지 않는다.
                   if (board[j][i] == 0L) continue

                   if (stack.isEmpty()) {
                       stack.add(board[j][i])
                   } else {
                       if (!combined && stack.peek() == board[j][i]) {
                           combined = true
                           stack.add(stack.pop() + board[j][i])
                       } else {
                           combined = false
                           stack.add(board[j][i])
                       }
                   }
               }

               stack.reverse()
               for (k in 0 until N) {
                   if (stack.isNotEmpty()) {
                       board[k][i] = stack.pop()
                   } else {
                       board[k][i] = 0
                   }
               }
               stack.clear()
           }
        }
        //하
        1 -> {
            for (i in 0 until N) {
                val stack = Stack<Long>()
                var combined = false

                for (j in (N - 1) downTo 0 step 1) {
                    // 빈칸은 stack 에 저장하지 않는다.
                    if (board[j][i] == 0L) continue

                    if (stack.isEmpty()) {
                        stack.add(board[j][i])
                    } else {
                        if (!combined && stack.peek() == board[j][i]) {
                            combined = true
                            stack.add(stack.pop() + board[j][i])
                        } else {
                            combined = false
                            stack.add(board[j][i])
                        }
                    }
                }

                stack.reverse()
                for (k in (N - 1) downTo 0 step 1) {
                    if (stack.isNotEmpty()) {
                        board[k][i] = stack.pop()
                    } else {
                        board[k][i] = 0
                    }
                }
                stack.clear()
            }
        }
        //좌
        2 -> {
            for (i in 0 until N) {
                val stack = Stack<Long>()
                var combined = false

                for (j in 0 until N) {
                    // 빈칸은 stack 에 저장하지 않는다.
                    if (board[i][j] == 0L) continue

                    if (stack.isEmpty()) {
                        stack.add(board[i][j])
                    } else {
                        if (!combined && stack.peek() == board[i][j]) {
                            combined = true
                            stack.add(stack.pop() + board[i][j])
                        } else {
                            combined = false
                            stack.add(board[i][j])
                        }
                    }
                }

                stack.reverse()
                for (k in 0 until N) {
                    if (stack.isNotEmpty()) {
                        board[i][k] = stack.pop()
                    } else {
                        board[i][k] = 0
                    }
                }
                stack.clear()
            }
        }
        //우
        else -> {
            for (i in 0 until N) {
                val stack = Stack<Long>()
                var combined = false

                for (j in (N - 1) downTo 0 step 1) {
                    // 빈칸은 stack 에 저장하지 않는다.
                    if (board[i][j] == 0L) continue

                    if (stack.isEmpty()) {
                        stack.add(board[i][j])
                    } else {
                        if (!combined && stack.peek() == board[i][j]) {
                            combined = true
                            stack.add(stack.pop() + board[i][j])
                        } else {
                            combined = false
                            stack.add(board[i][j])
                        }
                    }
                }

                stack.reverse()
                for (k in (N - 1) downTo 0 step 1) {
                    if (stack.isNotEmpty()) {
                        board[i][k] = stack.pop()
                    } else {
                        board[i][k] = 0
                    }
                }
                stack.clear()
            }
        }
    }

    return board
}