package com.lwt.algorithm.greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    // N : 세로
    // M : 가로
    val (N, M) = br.readLine().split(" ").map { it.toInt() }


    val result = when (N) {
        1 -> 1
        2 -> {
            when (M) {
                in 1..2 -> 1
                in 3..4 -> 2
                in 5..6 -> 3
                else -> 4
            }
        }
        else -> {
            when (M) {
                1 -> 1
                2 -> 2
                3 -> 3
                in 4..6 -> 4
                else -> 5 + (M - 7)
            }
        }
    }

    println(result)
}