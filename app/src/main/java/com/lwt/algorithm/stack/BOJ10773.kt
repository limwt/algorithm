package com.lwt.algorithm.stack

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Stack

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val K = br.readLine().toInt()

    val stack = Stack<Int>()

    repeat(K) {
        val num = br.readLine().toInt()

        if (num != 0) {
            stack.add(num)
        } else {
            stack.pop()
        }
    }

    println("${stack.sum()}")
}