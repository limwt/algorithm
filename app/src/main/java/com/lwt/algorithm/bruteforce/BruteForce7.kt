package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine()
    var result = 0

    if (N.length == 1) {
        result = N.toInt()
    } else {
        for (i in 1..N.toInt()) {
            if (i in 1..9) {
                result += 1
            } else if (i in 10..99) {
                result += 2
            } else if (i in 100..999) {
                result += 3
            } else if (i in 1000..9999) {
                result += 4
            } else if (i in 10000..99999) {
                result += 5
            } else if (i in 100000 ..999999) {
                result += 6
            } else if (i in 1000000 ..9999999) {
                result += 7
            } else if (i in 10000000 ..99999999) {
                result += 8
            } else {
                result += 9
            }
        }
    }

    println(result)
}