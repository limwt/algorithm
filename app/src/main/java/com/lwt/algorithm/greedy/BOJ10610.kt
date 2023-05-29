package com.lwt.algorithm.greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine()

    if (N.contains("0")) {
        val number = N.toCharArray()
        number.sortDescending()

        if (number[number.size - 1].digitToInt() != 0) {
            println(-1)
        } else {
            var sum = 0
            for (i in 0 until number.size - 1) {
                sum += number[i].digitToInt()
            }

            if (sum % 3 == 0) println(String(number))
            else println(-1)
        }
    } else {
        println(-1)
    }
}