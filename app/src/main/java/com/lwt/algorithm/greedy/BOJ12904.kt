package com.lwt.algorithm.greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val S = br.readLine()
    val T = br.readLine()
    var word = T

    while (true) {
        if (S == word) {
            println(1)
            break
        }

        if (word.length == 1) {
            println(0)
            break
        }

        val index = word.length - 1

        if (word[index] == 'A') {
            word = word.substring(0, index)
        } else if (word[index] == 'B') {
            word = word.substring(0, index)
            word = word.reversed()
        }
    }
}