package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (E, S, M) = br.readLine().split(" ").map { it.toInt() }

    var year = 1

    while (true) {


        if ((year - E) % 15 == 0 && (year - S) % 28 == 0 && (year - M) % 19 == 0) {
            break
        }

        year++
    }

    println(year)
}