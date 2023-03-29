package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

private val result = StringBuilder()

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val M = br.readLine().toInt() // 1 <= M <= 3,000,000
    val S = IntArray(21) { 0 }

    repeat(M) {
        val str = br.readLine()
        var cmd = ""
        var x = 0
        //println("str = $str")

        if (str == "all" || str == "empty") {
            cmd = str
        } else {
            cmd = str.split(" ").map { it }[0]
            x = str.split(" ").map { it }[1].toInt()
        }

        calc(cmd, x, S)
    }

    println(result.toString())
}

private fun calc(cmd: String, x: Int, S: IntArray) {
    when (cmd) {
        "add" -> S[x] = (S[x] or x)
        "remove" -> if (S[x] != 0) S[x] = S[x] xor x
        "check" -> if (S[x] == 0) result.append("0\n") else result.append("1\n")
        "toggle" -> S[x] = if (S[x] == 0) x else (S[x] xor x)
        "all" -> {
            for (i in 1..20) {
                S[i] = i
            }
        }
        else -> {
            for (i in 1..20) {
                S[i] = 0
            }
        }
    }
}