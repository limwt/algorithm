package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val T = br.readLine().toInt()

    repeat(T) {
        val result = br.readLine().split(" ").map { it.reversed() }

        for (r in result) {
            println("$r ")
        }
    }
}