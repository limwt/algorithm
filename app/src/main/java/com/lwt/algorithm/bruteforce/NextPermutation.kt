package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main (args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val number = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    var index = -1

    // 주어진 수열을 오른쪽에서 부터 탐색하여 number[i] > number[i - 1] 인 구간을 찾는다.
    for (i in (N - 1) downTo 1 step 1) {
        if (number[i] > number[i - 1]) {
            index = i
            break
        }
    }

    if (index == -1) {
        println(index)
    } else {
        // index 를 기준으로 두 개의 순열로 나눈 뒤
        // 왼쪽 순열과 오른쪽 순열을 오른쪽 부터 탐색하여 오른쪽 순열의 값이 왼쪽보다 큰 것을 찾는다.
        for (i in (N - 1) downTo index step 1) {
            if (number[index - 1] < number[i]) {
                val temp = number[index - 1]
                number[index - 1] = number[i]
                number[i] = temp
                break
            }
        }

        val sortList = mutableListOf<Int>()
        for (i in index until N) {
            sortList.add(number[i])
        }

        sortList.sort()

        val result = StringBuilder()

        for (i in 0 until index) {
            result.append("${number[i]} ")
        }

        for (i in 0 until (N - index)) {
            result.append("${sortList[i]} ")
        }

        println(result.toString())
    }
}
