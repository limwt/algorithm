package com.lwt.algorithm.greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val currentLamp = br.readLine()
    val targetLamp = br.readLine()

    if (currentLamp == targetLamp) {
        println(0)
    } else {
        // 첫번째 램프를 켜지 않을때 정보 저장
        val lamp1 = IntArray(N) { 0 }
        var lamp1Count = 0
        // 첫번째 램프를 켰을 때 정보 저장
        val lamp2 = IntArray(N) { 0 }
        var lamp2Count = 0

        for (i in 0 until N) {
            lamp1[i] = currentLamp[i].digitToInt()
            lamp2[i] = currentLamp[i].digitToInt()
        }

        // 첫번째 램프를 켠다.
        switchOn(lamp2, 0, N)
        lamp2Count = 1

        for (i in 1 until N) {
            if (lamp1[i - 1] != targetLamp[i - 1].digitToInt()) {
                switchOn(lamp1, i, N)
                lamp1Count++
            }

            if (lamp2[i - 1] != targetLamp[i - 1].digitToInt()) {
                switchOn(lamp2, i, N)
                lamp2Count++
            }
        }

        for (i in 0 until N) {
            if (lamp1[i] != targetLamp[i].digitToInt()) {
                lamp1Count = Int.MAX_VALUE
                break
            }
        }

        for (i in 0 until N) {
            if (lamp2[i] != targetLamp[i].digitToInt()) {
                lamp2Count = Int.MAX_VALUE
                break
            }
        }

        if (lamp1Count == Int.MAX_VALUE && lamp2Count == Int.MAX_VALUE) println(-1)
        else {
            println("${Math.min(lamp1Count, lamp2Count)}")
        }
    }
}

private fun switchOn(arr: IntArray, index: Int, n: Int) {
    if (index != 0) arr[index - 1] = if (arr[index - 1] == 1) 0 else 1
    arr[index] = if (arr[index] == 1) 0 else 1
    if (index != n - 1) arr[index + 1] = if (arr[index + 1] == 1) 0 else 1
}