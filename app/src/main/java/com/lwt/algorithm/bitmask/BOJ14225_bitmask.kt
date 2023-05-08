package com.lwt.algorithm.bitmask

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val S = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    val result = mutableListOf<List<Int>>()

    // 비트 마스크를 이용하여 부분 수열을 구한다.
    for (i in 0 until (1 shl N)) {
        val list = mutableListOf<Int>()

        for (j in 0 until N) {
            if (i and (1 shl j) != 0) {
                list.add(S[j])
            }
        }

        result.add(list)
    }

    val sum = IntArray(2000001) { 0 }

    for (_result in result) {
        var s = 0

        for (r in _result) {
            s += r
        }

        //println("s = $s")
        sum[s] = 1
    }

    for (i in 1 until sum.size) {
        if (sum[i] == 0) {
            println(i)
            break
        }
    }
}