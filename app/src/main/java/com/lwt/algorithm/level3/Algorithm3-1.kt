package com.lwt.algorithm.level3

import java.util.*

/**
 * 주어진 문자 s를 왼쪽으로 x (0 ≤ x < (s의 길이)) 칸만큼 회전시켰을 때 s가 올바른 괄호 문자열이 되게 하는 x의 개수를 return 하도록 solution 함수를 완성해주세요.
 * 올바른 괄호 문자열
 *     1. [], (), {}
 *     2. [A], (B), {C}
 *     3. [()], {()}
 *     4. [[]][ 마지막에 대괄호가 쌍을 이루지 않기 때문에 올바른 괄호 문자가 아니다.
 *
 * Input : "[](){}"
 * Output : 3
 */


fun main(args: Array<String>) {
    println(solution("[](){}"))
}

fun solution(s: String): Int {
    var answer: Int = 0
    val sList = mutableListOf<Char>()

    s.forEach { c ->
        sList.add(c)
    }

    for (i in s.indices) {
        if (i != 0) {
            // i 만큼 이동
            sList.add(sList.removeAt(0))
        }

        if (isRight(sList)) answer++
    }

    return answer
}

private fun isRight(input: MutableList<Char>): Boolean {
    val stack = Stack<Char>()
    //println("======input : ${String(input.toCharArray())}")

    input.forEach { i ->
        if (stack.isEmpty()) {
            stack.add(i)
        } else {
            val top = stack.peek()

            when {
                (i == ']' && top == '[') ||
                        (i == '}' && top == '{') ||
                        (i == ')' && top == '(') -> stack.pop()
                else -> stack.add(i)
            }
        }

        //println("stack : $stack")
    }
    return stack.isEmpty()
}
