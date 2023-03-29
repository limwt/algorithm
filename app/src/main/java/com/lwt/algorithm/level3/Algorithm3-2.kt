package com.lwt.algorithm.level3

import java.util.*

/**
 * 주어진 숫자에서 k 개 만큼 숫자를 제거 했을 때 나타날 수 있는 최대 값 구하기
 *
 * 탐욕 알고리즘 혹은 Stack 으로 풀수 있음
 *
 * Input : 4177252841
 * Output : 775841
 */

fun main(args: Array<String>) {
    println(solution("4177252841", 4))
    println(solution2("4177252841", 4))
}

// Greedy 알고리즘을 이용한 풀이
fun solution(number: String, k: Int): String {
    val answer = StringBuilder()
    var idx = 0

    for (i in 0 until number.length - k) {
        var max = Int.MIN_VALUE
        for (j in idx..(i+k)) {
            val num = number[j].digitToInt()
            if (max < num) {
                max = num
                idx = j + 1
            }
        }

        answer.append(max)
        //println("answer $answer")
    }

    return answer.toString()
}

// Stack 을 이용한 풀이
fun solution2(number: String, k: Int): String {
    var answer = ""
    val stack = Stack<Int>()
    var count = k

    number.forEach {
        val num = it.digitToInt()

        if (count > 0) {
            if (stack.isEmpty()) {
                stack.add(num)
            } else {
                if (stack.isNotEmpty()) {
                    //val top = stack.peek()
                    //println("top : $top, num : $num, $count")

                    while (stack.isNotEmpty() && stack.peek() < num) {
                        if (count == 0) break

                        stack.pop()
                        count--
                    }

                    stack.add(num)
                    //println("stack: $stack")
                }
            }
        } else if (count == 0) {
            stack.add(num)
        }
    }

    stack.reverse()

    //println("stack: $stack")
    while (stack.isNotEmpty()) {
        if (answer.length == number.length - k) break
        answer += stack.pop()
    }

    return answer
}








































/*

    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val E = br.readLine().toInt()
    val computer = Array(N + 1) {
        IntArray(N + 1)
    }
    val visit = mutableListOf<Int>()

    repeat(E) {
        val(start, end) = br.readLine().split(" ").map { it.toInt() }
        computer[start][end] = 1
        computer[end][start] = 1
    }

    bfs(computer, 1, visit)
    println("${visit.size - 1}")
}

private fun bfs(arr: Array<IntArray>, start: Int, visit: MutableList<Int>) {
    val queue = LinkedList<Int>()
    queue.add(start)
    visit.add(1)

    while (!queue.isEmpty()) {
        val next = queue.poll()

        for (i in 1 until arr.size) {
            if (arr[next][i] == 1 && !visit.contains(i)) {
                visit.add(i)
                queue.add(i)
            }
        }
    }
}*/
