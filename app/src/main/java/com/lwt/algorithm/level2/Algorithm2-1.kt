package com.lwt.algorithm.level2

/**
 * 십진수 12를 2진수를 변환한뒤 변환한 수를 역순으로 재배열 하고 재배열된 수를 10진수로 반환하라.
 *
 * Input : 12
 * Output : 3
 * ex) 십진수12 -> 2진수(1100) -> 앞뒤반전(0011) -> 10진수 값 구하기(3)
 */

fun main(args: Array<String>) {
    println(getDecimal(12))
}

private fun getDecimal(number: Int): Int {
    return number.toString(2).reversed().toInt(2)
}