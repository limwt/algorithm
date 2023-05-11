package com.lwt.algorithm.bitmask

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val list = mutableListOf<Int>()

    br.readLine().split("-").forEach { str ->
        var sum = 0
        str.split("+").forEach { s ->
            sum += s.toInt()
        }

        list.add(sum)
    }

    var result = list[0]

    for (i in 1 until list.size) {
        result -= list[i]
    }

    println(result)
}