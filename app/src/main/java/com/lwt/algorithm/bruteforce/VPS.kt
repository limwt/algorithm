package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val input = br.readLine()
    val stack = Stack<Char>()
    var result = 0

    input.forEachIndexed { i, c ->
        if (c == '(') {
            stack.add(c)
        } else {
            // 레이져...
            if (input[i - 1] == '(') {
                stack.pop()
                result += stack.size
            } else {
                stack.pop()
                result++
            }
        }
    }

    println(result)
}