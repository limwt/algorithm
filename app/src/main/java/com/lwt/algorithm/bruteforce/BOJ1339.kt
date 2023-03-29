package com.lwt.algorithm.bruteforce

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val strList = Array(N) { "" }
    repeat(N) {
        strList[it] = br.readLine()
    }

    // 입력된 단어의 알파벳의 자리 수를 구해서 list 에 저장한다.
    val list = ArrayList<Alphabet>()
    strList.forEach { str ->
        var pow = 1

        for (i in str.length - 1 downTo 0 step 1) {
            val s = str[i].toString()
            var contains = false
            var containsIndex = -1

            for ((index, l) in list.withIndex()) {
                if (l.alphabet == s) {
                    contains = true
                    containsIndex = index
                    break
                }
            }

            if (contains) {
                list[containsIndex].pow += pow
            } else {
                list.add(Alphabet(s, pow))
            }

            pow *= 10
        }
    }

    //println("$list")

    // 자리수를 기준으로 내림 차순 정렬 한다.
    list.sortByDescending { it.pow }
    //println("$list")

    // 정렬된 리스트를 기준으로 알파벳에 9부터 큰 숫자를 넣는다.
    var number = 9
    var result = 0

    for (l in list) {
        result += l.pow * number
        number--
    }

    println(result)
}

private data class Alphabet(var alphabet: String, var pow: Int)