package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt() // 피 연산자 개수
    val input = br.readLine()
    val stack = Stack<Char>() // 입력을 stack 에 저장한다.
    val opStack = Stack<Char>() // 입력에서 pop 하여 나온 결과가 연산자일 경우 stack 에 저장하는 용도로 쓴다.
    val number = mutableListOf<Int>()

    repeat(N) {
        number.add(br.readLine().toInt())
    }

    input.forEach { i ->
        stack.add(i)
    }

    var index = N - 1 // 피연산자 위치 인덱스
    var result = 1

    while (stack.isNotEmpty()) {
        val ch = stack.pop()

        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            opStack.add(ch)
        } else {
            if (index == N - 1) {
                result = number[index]
            } else {
                if (opStack.isNotEmpty()) {
                    val op = opStack.pop()
                    result = when (op) {
                        '+' -> number[index] + result
                        '-' -> number[index] - result
                        '*' -> number[index] * result
                        else -> number[index] / result
                    }
                }
            }

            index--
        }
    }
}