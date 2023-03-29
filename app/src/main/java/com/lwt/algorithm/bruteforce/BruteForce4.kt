package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt() // 이동하려고 하는 채널
    val M = br.readLine().toInt() // 고장난 버튼의 개수
    var broken = mutableListOf<Int>() // 고장난 채널 정보

    if (M > 0) {
        broken = br.readLine().split(" ").map { it.toInt() }.toMutableList()
    }

    var answer = 0

    if (N != 100) {
        var result = -1
        var min = Int.MAX_VALUE

        for (i in 0..999999) {
            val channel = i.toString()
            var ok = true

            for (i in 0..M - 1) {
                if (channel.contains(broken[i].toString())) {
                    ok = false
                    break
                }
            }

            if (ok) {
                if (min > Math.abs(N - i)) {
                    min = Math.abs(N - i)
                    result = i
                }
            }
        }

        //println("result : $result")
        answer = if (result == -1) {
            val result1 = Math.abs(N - 100)
            result1
        } else {
            val result1 = Math.abs(N - 100)
            val result2 = Math.abs(N - result) + result.toString().length
            Math.min(result1, result2)
        }
    }

    println(answer)
}