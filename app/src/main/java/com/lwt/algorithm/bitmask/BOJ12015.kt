package com.lwt.algorithm.bitmask

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val A = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    val stack = Stack<Int>()
    var result = Int.MIN_VALUE

    for (a in A) {
        if (stack.isEmpty()) stack.add(a)
        else {
            val top = stack.peek()
            if (top < a) {
                stack.add(a)
            } else if (top > a) {
                if (stack.size == 1) {
                    stack.pop()
                    stack.add(a)
                } else {
                    if (stack[stack.size - 2] < a) {
                        stack.pop()
                        stack.add(a)
                    }
                }
            }
        }
    }

    println(stack.size)
}