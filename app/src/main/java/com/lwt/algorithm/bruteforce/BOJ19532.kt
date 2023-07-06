package com.lwt.algorithm.bruteforce

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val numbers = br.readLine().split(" ").map { it.toInt() }
    val a = numbers[0]
    val b = numbers[1]
    val c = numbers[2]
    val d = numbers[3]
    val e = numbers[4]
    val f = numbers[5]

    for (i in -999..999) {
        for (j in -999..999) {
            if (a * i + b * j == c && d * i + e * j == f) {
                println("$i $j")
                break
            }
        }
    }
}