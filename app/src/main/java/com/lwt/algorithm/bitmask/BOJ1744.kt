package com.lwt.algorithm.bitmask

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()

    val sequence = IntArray(N) { 0 }

    repeat(N) {
        val num = br.readLine().toInt()
        sequence[it] = num
    }

    // 내림 차순으로 정렬 한다.
    sequence.sortDescending()

    /*for (seq in sequence) {
        print("$seq ")
    }
    println()*/

    // 양수를 계산 한다.
    var index = 0
    var result = 0

    while (index < N && sequence[index] > 0) {
        if (index == N - 1) {
            result += sequence[index]
            index++
        } else {
            if (sequence[index] * sequence[index + 1] > sequence[index] + sequence[index + 1]) {
                result += sequence[index] * sequence[index + 1]
                index += 2
            } else {
                result += sequence[index]
                index += 1
            }
        }
    }

    // 음수를 계산한다. 음수를 마지막 index 부터 두 수를 묶는다.
    index = N - 1

    while (index >= 0 && sequence[index] < 0) {
        if (index == 0) {
            result += sequence[index]
            index--
        } else {
            if (sequence[index] * sequence[index - 1] > sequence[index] + sequence[index - 1]) {
                result += sequence[index] * sequence[index - 1]
                index -= 2
            } else {
                result += sequence[index]
                index -= 1
            }
        }
    }

    println(result)
}