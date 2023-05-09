package com.lwt.algorithm.lis

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val arr = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    val seq = IntArray(N) { 0 }
    seq[0] = 1

    for (i in 1 until N) {
        val current = arr[i]
        var result = 1

        for (j in (i - 1) downTo 0 step 1) {
            if (current > arr[j]) {
                result = Math.max(result, seq[j] + 1)
            }
        }

        seq[i] = result
    }

    for (s in seq) {
        print("$s ")
    }
    println()
}