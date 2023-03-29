package com.lwt.algorithm.level1

import java.io.BufferedReader
import java.io.InputStreamReader

var result = 0

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val T = br.readLine().toInt()

    repeat(T) {
        val S = br.readLine()
        result = 0
        println("${isPalindrome(S)} $result")
    }
}

fun isPalindrome(s: String): Int {
    return recursion(s, 0, s.length - 1)
}

fun recursion(s: String, start:Int, end: Int): Int {
    result += 1
    return if (start >= end) 1
    else if (s[start] != s[end]) 0
    else recursion(s, start + 1, end - 1)
}