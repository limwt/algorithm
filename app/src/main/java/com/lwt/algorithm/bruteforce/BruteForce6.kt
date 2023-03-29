package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val T = br.readLine().toInt()

    repeat(T) {
        val (M, N, x, y) = br.readLine().split(" ").map { it.toInt() }
        var answer = -1
        // M, N 의 최소 공배수 구하기
        val LCM = (M * N ) / getGCD(M, N)
        //println(LCM)
        var caing = x

        while (caing <= LCM) {
            if ((caing - x) % M == 0 && (caing - y) % N == 0) {
                answer = caing
                break
            }

            caing += M
        }

        println(answer)
    }
}

fun getGCD(m: Int, n: Int): Int {
    return if (m % n == 0) n
    else getGCD(n, m % n)
}
