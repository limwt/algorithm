package com.lwt.algorithm.algo

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val dwarf = mutableListOf<Int>()
    repeat(9) {
        dwarf.add(br.readLine().toInt())
    }

    val sum = dwarf.sum()

    loop@ for (i in 0..7) {
        for (j in (i + 1)..8) {
            val first = dwarf[i]
            val second = dwarf[j]

            if (sum - (first + second) == 100) {
                println("$i, $j, $first, $second")
                dwarf.remove(first)
                dwarf.remove(second)
                dwarf.sort()

                dwarf.forEach { d ->
                    println(d)
                }
                break@loop
            }
        }
    }
}