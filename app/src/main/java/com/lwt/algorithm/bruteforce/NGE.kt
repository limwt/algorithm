package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val arr = br.readLine().split(" ").map { it.toInt() }.toMutableList()
    val stack = Stack<Int>()
    // 초기 값으로 0번째 인덱스를 stack 에 저장한다.

    for (i in 0 until N) {
        //println("stack = $stack")
        while (stack.isNotEmpty() && arr[i] > arr[stack.peek()]) {
            arr[stack.pop()] = arr[i]
        }

        stack.add(i)
    }

    while (stack.isNotEmpty()) {
        val idx = stack.pop()
        arr[idx] = - 1
    }

    val sb = StringBuilder()
    arr.forEach { a ->
        sb.append("$a ")
    }

    println(sb.toString())
}